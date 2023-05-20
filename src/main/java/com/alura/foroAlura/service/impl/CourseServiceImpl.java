package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.CourseMapper;
import com.alura.foroAlura.model.Category;
import com.alura.foroAlura.model.Course;
import com.alura.foroAlura.repository.CourseRepository;
import com.alura.foroAlura.service.CategoryService;
import com.alura.foroAlura.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CategoryService categoryService;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.categoryService = categoryService;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " not found"));
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::courseToCourseResponse).toList();
    }

    @Override
    @Transactional
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        String courseName = courseRequest.name();
        if(courseRepository.findByName(courseName).isPresent())
            throw new BadRequestException("There is already a course with the name '" + courseName + "'");
        List<Category> categories = getCategories(courseRequest.categories());
        Course course = courseRepository.save(Course.builder()
                        .name(courseRequest.name())
                        .categories(categories)
                        .build());

        return courseMapper.courseToCourseResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        String courseName = courseRequest.name();
        Course course = getCourseById(id);
        Course courseByName = courseRepository.findByName(courseName).orElse(null);
        if(courseByName != null && !(Objects.equals(courseByName.getId(), id)))
            throw new BadRequestException("There is already a course with the name '" + courseName + "'");
        List<Category> categories = getCategories(courseRequest.categories());

        course.setName(courseName);
        course.setCategories(categories);

        return courseMapper.courseToCourseResponse(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }

    private List<Category> getCategories(List<OnlyId> onlyIdCategories) {
        return onlyIdCategories.stream().map(
                onlyId -> categoryService.getCategoryById(onlyId.id())).toList();
    }
}
