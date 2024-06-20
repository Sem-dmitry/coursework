package com.example.kursov.controllers;

import com.example.kursov.dto.TargetStatistic;
import com.example.kursov.services.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @GetMapping("/stat")
    ResponseEntity<List<TargetStatistic>> getStaticInfo() {
        return adminService.getStaticInfo();
    }
}
