package com.example.kursov.controllers;

import com.example.kursov.dto.TargetStatistic;
import com.example.kursov.services.AdminServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @ResponseBody
    @GetMapping("/stat")
    ResponseEntity<List<TargetStatistic>> getStaticInfo() {
        return adminService.getStaticInfo();
    }

    @ResponseBody
    @PostMapping("/train")
    ResponseEntity<Void> trainModel() {
        return adminService.trainModel();
    }
}
