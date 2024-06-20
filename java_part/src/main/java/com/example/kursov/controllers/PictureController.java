package com.example.kursov.controllers;

import com.example.kursov.models.Picture;
import com.example.kursov.services.PictureServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pictures")
public class PictureController {

    public final PictureServiceImplementation pictureServiceImplementation;

    @PostMapping("/predict")
    ResponseEntity<Map<String, Object>> predictPicture(
            @RequestBody Picture picture
    ) {
        System.out.println("В контроллере /predict: " + picture);
        return pictureServiceImplementation.predictPicture(picture);
    }

    @PostMapping("/save")
    ResponseEntity<Map<String, Object>> predictAndSavePicture(
            @RequestBody Picture picture
    ) {
        System.out.println("В контроллере /save: " + picture);
        pictureServiceImplementation.savePicture(picture);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<List<Picture>> getAllPictures() {
        return ResponseEntity.ok(pictureServiceImplementation.getAllPicture());
    }

    @GetMapping("/{id}")
    ResponseEntity<Picture> getPictureById(@PathVariable("id") Long id) {
        return pictureServiceImplementation.getPictureById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updatePictureById(Picture picture, @PathVariable("id") Long id) {
        pictureServiceImplementation.updatePicture(picture, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePictureById(@PathVariable("id") Long id) {
        pictureServiceImplementation.deletePicture(id);
        return ResponseEntity.ok().build();
    }
}