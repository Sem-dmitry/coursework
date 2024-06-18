package com.example.kursov.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "training_sessions")
public class TrainingSession {
    @Id
    @Column(name = "training_session_id")
    @SequenceGenerator(sequenceName = "training_session_id_seq", name = "training_session_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_session_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated
    private TSStatuses status;
}
