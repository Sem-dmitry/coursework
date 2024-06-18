package com.example.kursov.services;

import com.example.kursov.dto.TargetStatistic;
import com.example.kursov.models.TSStatuses;
import com.example.kursov.models.TrainingSession;
import com.example.kursov.models.User;
import com.example.kursov.repositories.PictureRepository;
import com.example.kursov.repositories.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final PictureRepository pictureRepository;
    private final TrainingSessionRepository trainingSessionRepository;

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    @Override
    public ResponseEntity<List<TargetStatistic>> getStaticInfo() {
        return ResponseEntity.ok(pictureRepository.findTargetStatistic());
    }

    @Override
    public ResponseEntity<Void> trainModel() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(headers);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        TrainingSession trainingSession = trainingSessionRepository.save(
                new TrainingSession()
                        .setId(null)
                        .setUser(user)
                        .setStatus(TSStatuses.IN_PROCESS)
                        .setStartTime(LocalDateTime.now())
                        .setEndTime(null)
        );
        restTemplate.postForEntity(flaskApiUrl + "/train", request, Void.class);
        trainingSession.setEndTime(LocalDateTime.now()).setStatus(TSStatuses.END);
        return ResponseEntity.ok().build();
    }
}
