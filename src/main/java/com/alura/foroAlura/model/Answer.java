package com.alura.foroAlura.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Answer")
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 2000)
    private String message;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private boolean solution;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
    private Topic topic;
}
