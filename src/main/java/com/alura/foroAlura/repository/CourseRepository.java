package com.alura.foroAlura.repository;


import com.alura.foroAlura.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Transactional(readOnly = true)
    Optional<Course> findByName(String name);
}
