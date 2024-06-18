package com.example.kursov.services;

import com.example.kursov.models.Picture;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PictureService {
    ResponseEntity<Map<String, Object>> predictPicture(Picture picture);

    void savePicture(Picture picture);
}
