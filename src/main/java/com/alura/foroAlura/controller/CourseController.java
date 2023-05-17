package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.mapper.CourseMapper;
import com.alura.foroAlura.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    public CourseController(CourseMapper courseMapper, CourseService courseService) {
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseMapper.courseToCourseResponse(courseService.getCourseById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>>getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> saveCourse(@RequestBody @Valid CourseRequest courseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(courseRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
