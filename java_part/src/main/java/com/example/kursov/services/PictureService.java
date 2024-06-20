package com.example.kursov.services;

import com.example.kursov.models.Picture;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PictureService {
    ResponseEntity<Map<String, Object>> predictPicture(Picture picture);

    void savePicture(Picture picture);

    List<Picture> getAllPicture();

    Optional<Picture> getPictureById(Long id);

    void updatePicture(Picture picture, Long id);

    void deletePicture(Long id);
}
