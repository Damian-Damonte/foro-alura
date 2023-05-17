package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.model.Course;

import java.util.List;

public interface CourseService {
    Course getCourseById(Long id);
    List<CourseResponse> getAllCourses();
    CourseResponse saveCourse(CourseRequest courseRequest);
    CourseResponse updateCourse(Long id, CourseRequest courseRequest);
    void deleteCourse(Long id);
}
