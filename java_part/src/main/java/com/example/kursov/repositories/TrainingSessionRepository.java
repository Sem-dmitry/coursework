package com.example.kursov.repositories;

import com.example.kursov.models.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TrainingSessionRepository extends CrudRepository<TrainingSession, Long> {
}
