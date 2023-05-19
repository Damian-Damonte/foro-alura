package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
