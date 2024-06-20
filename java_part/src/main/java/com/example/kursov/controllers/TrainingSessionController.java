package com.example.kursov.controllers;

import com.example.kursov.models.TrainingSession;
import com.example.kursov.services.TrainingSessionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/train")
public class TrainingSessionController {
    private final TrainingSessionServiceImpl trainingSessionService;
    @PostMapping
    ResponseEntity<Void> trainModel() {
        return trainingSessionService.trainModel();
    }

    @GetMapping
    ResponseEntity<List<TrainingSession>> getAllTrainingSession() {
        return ResponseEntity.ok(trainingSessionService.getAllTrainingSession());
    }

    @GetMapping("/{id}")
    ResponseEntity<TrainingSession> getTrainingSessionById(@PathVariable("id") Long id) {
        return trainingSessionService.getTrainingSessionById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
