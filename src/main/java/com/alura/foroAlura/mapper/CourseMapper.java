package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.course.CourseRequest;
import com.alura.foroAlura.dto.course.CourseResponse;
import com.alura.foroAlura.model.Category;
import com.alura.foroAlura.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {
    @Mapping(
            target = "categories",
            expression = "java(onlyIdToCategories(courseRequest.categories()))")
    Course courseRequestToCourse(CourseRequest courseRequest);
    CourseResponse courseToCourseResponse(Course course);

    default List<Category> onlyIdToCategories(List<OnlyId> ids) {
        return ids.stream().map(onlyId -> Category.builder().id(onlyId.id()).build()).toList();
    }
}
