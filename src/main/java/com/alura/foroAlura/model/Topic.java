package com.alura.foroAlura.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 2000)
    private String message;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    public enum TopicStatus {
        UNANSWERED,
        UNRESOLVED,
        SOLVED,
        CLOSED;
    }
}
