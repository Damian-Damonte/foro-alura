package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
