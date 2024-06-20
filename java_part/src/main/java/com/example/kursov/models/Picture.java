package com.example.kursov.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @Column(name = "picture_id")
    @SequenceGenerator(sequenceName = "user_id_seq", name = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Column(name = "pixels")
    private int[] matrix;

    @Column(name = "target")
    private int target;
}
