package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.mapper.CourseMapper;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseController(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>>getAllCourses() {
        List<CourseResponse> coursesResponse = courseRepository.findAll()
                .stream().map(courseMapper::courseToCourseResponse).toList();

        return ResponseEntity.ok(coursesResponse);
    }

    @PostMapping()
    public void saveCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseMapper.courseRequestToCourse(courseRequest);
        Course courseSave = courseRepository.save(course);

    }
}
