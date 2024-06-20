package com.example.kursov.services;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionService {
    ResponseEntity<Void> trainModel();

    List<com.example.kursov.models.TrainingSession> getAllTrainingSession();

    Optional<com.example.kursov.models.TrainingSession> getTrainingSessionById(Long id);
}
