package com.example.kursov.services;

import com.example.kursov.dto.TargetStatistic;
import com.example.kursov.repositories.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final PictureRepository pictureRepository;

    @Override
    public ResponseEntity<List<TargetStatistic>> getStaticInfo() {
        return ResponseEntity.ok(pictureRepository.findTargetStatistic());
    }
}
