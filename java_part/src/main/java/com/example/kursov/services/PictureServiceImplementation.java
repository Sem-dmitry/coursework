package com.example.kursov.services;

import com.example.kursov.models.Picture;
import com.example.kursov.repositories.PictureRepository;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PictureServiceImplementation implements PictureService{
    private static final Logger logger = LoggerFactory.getLogger(Picture.class);
    private final PictureRepository pictureRepository;
    @Value("${flask.api.url}")
    private String flaskApiUrl;
    @Override
    public ResponseEntity<Map<String, Object>> predictPicture(
            Picture picture
    ) {
        System.out.println("В сервисе предсказания: " + picture);
        // Отправка матрицы на Flask API для получения предсказания
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = new HashMap<>();
        logger.info("Received payload: {}", payload);
        payload.put("matrix", picture.getMatrix());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    flaskApiUrl + "/predict",
                    request,
                    Map.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                picture.setTarget((int) response.getBody().get("prediction"));
                System.out.println("Prediction: " + response.getBody());
                return ResponseEntity.ok(response.getBody());
            } else {
                logger.error("Error response from Flask API: {}", response.getStatusCode());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            logger.error("Exception while calling Flask API", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAllPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> getPictureById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void updatePicture(Picture updatePicture, Long id) {
        Optional<Picture> picture = pictureRepository.findById(id);
        if (picture.isPresent()) {
            Picture savedPicture = picture.get();
            savedPicture.setMatrix(updatePicture.getMatrix());
            savedPicture.setTarget(updatePicture.getTarget());
            pictureRepository.save(savedPicture);
        }
    }

    @Override
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
