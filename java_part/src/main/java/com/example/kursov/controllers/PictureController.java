package com.example.kursov.controllers;

import com.example.kursov.models.Picture;
import com.example.kursov.services.PictureServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//Контроллер обрабатывает HTTP запросы, связанные с отправкой рисунка
import org.springframework.http.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PictureController {

    public final PictureServiceImplementation pictureServiceImplementation;

    @ResponseBody
    @PostMapping("/predict")
    ResponseEntity<Map<String, Object>> predictPicture(
            @RequestBody Picture picture
    ) {
        System.out.println("В контроллере /predict: " + picture);
        return pictureServiceImplementation.predictPicture(picture);
    }

    @ResponseBody
    @PostMapping("/save")
    ResponseEntity<Map<String, Object>> predictAndSavePicture(
            @RequestBody Picture picture
    ) {
        System.out.println("В контроллере /save: " + picture);
        pictureServiceImplementation.savePicture(picture);
        return ResponseEntity.ok().build();
    }
}