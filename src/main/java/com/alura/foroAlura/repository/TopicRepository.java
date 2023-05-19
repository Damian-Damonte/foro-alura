package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Transactional(readOnly = true)
    Optional<Topic> findByTitleAndMessageAndCourseId(String title, String message, Long id);
}
