package com.example.smartwaste.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @Column(name = "user_id")
    private Integer userId;

    private Double latitude;
    private Double longitude;
    private String description;
    private String imageUrl;

    @Enumerated(EnumType.STRING) // Stores the ENUM as text
    private Status status;

    private Timestamp createdAt;

    // Enum for Status
    public enum Status {
        Pending,
        InProgress,
        Resolved
    }

    // Set default values before saving a new report
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        status = Status.Pending;
    }

    // --- GETTERS AND SETTERS (These were missing) ---

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}