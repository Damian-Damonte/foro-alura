package com.alura.foroAlura.mapper;

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
            expression = "java(categoriesIdsToCategories(courseRequest.categoriesIds()))")
    Course courseRequestToCourse(CourseRequest courseRequest);
//    CourseResponse courseToCourseResponse(Course course);

    default List<Category> categoriesIdsToCategories(List<Long> categoriasIds) {
        return categoriasIds.stream()
//                simplificar retornando direcatemente al category usando el builder
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .toList();
    }
}
