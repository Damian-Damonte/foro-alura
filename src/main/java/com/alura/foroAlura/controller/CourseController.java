package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.mapper.CourseMapper;
import com.alura.foroAlura.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseMapper.courseToCourseResponse(courseService.getCourseById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>>getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping()
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role"
    )
    public ResponseEntity<CourseResponse> saveCourse(@RequestBody @Valid CourseRequest courseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(courseRequest));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role"
    )
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role",
            description = "When deleting the course, all its topics and replies will be deleted as well"
    )
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
