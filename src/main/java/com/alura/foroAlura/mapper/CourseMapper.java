package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.model.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {
    CourseResponse courseToCourseResponse(Course course);
}
