package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
