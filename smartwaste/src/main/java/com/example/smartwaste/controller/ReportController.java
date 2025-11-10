package com.example.smartwaste.controller;

import com.example.smartwaste.model.Report;
import com.example.smartwaste.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports") // Base URL for all report-related APIs
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    // You would also need a FileStorageService to save the image
    // We'll skip that for now to keep it simple

    /**
     * API Endpoint for creating a new report.
     * Flutter app will send a POST request here.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createReport(
            @RequestParam("userId") Integer userId,
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        try {
            // 1. Create a new Report object
            Report newReport = new Report();
            newReport.setUserId(userId);
            newReport.setLatitude(latitude);
            newReport.setLongitude(longitude);
            newReport.setDescription(description);

            // 2. Handle the photo (if it exists)
            if (photo != null && !photo.isEmpty()) {
                // For a real project, you'd save this file to a folder or cloud storage
                // and set the URL. For this demo, we'll just save the file name.
                newReport.setImageUrl(photo.getOriginalFilename());
                System.out.println("Received photo: " + photo.getOriginalFilename());
            }

            // 3. Save the report details to the database
            reportRepository.save(newReport);

            // 4. Send a success response back to the app
            return ResponseEntity.ok("Report submitted successfully!");

        } catch (Exception e) {
            // Send an error response back to the app
            return ResponseEntity.status(500).body("Error creating report: " + e.getMessage());
        }
    }

    /**
     * API Endpoint for getting reports for a specific user.
     */
    @GetMapping("/my-reports/{userId}")
    public ResponseEntity<List<Report>> getMyReports(@PathVariable Integer userId) {
        try {
            List<Report> reports = reportRepository.findByUserId(userId);
            return ResponseEntity.ok(reports); // Send the list of reports as JSON
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}