package com.example.smartwaste.repository;

import com.example.smartwaste.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    
    // Spring Data JPA automatically creates a query to find reports by user ID
    List<Report> findByUserId(Integer userId);
}