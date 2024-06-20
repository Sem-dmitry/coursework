package com.example.kursov.services;

import com.example.kursov.dto.TargetStatistic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<List<TargetStatistic>> getStaticInfo();
}
