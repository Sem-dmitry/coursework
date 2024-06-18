package com.example.kursov.repositories;

import com.example.kursov.dto.TargetStatistic;
import com.example.kursov.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("SELECT new com.example.kursov.dto.TargetStatistic(p.target, COUNT(p)) " +
            "FROM Picture p " +
            "GROUP BY p.target " +
            "ORDER BY p.target ASC")
    List<TargetStatistic> findTargetStatistic();
}
