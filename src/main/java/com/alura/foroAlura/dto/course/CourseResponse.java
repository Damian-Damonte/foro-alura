package com.alura.foroAlura.dto.course;

import com.alura.foroAlura.dto.category.CategoryResponse;

import java.util.List;

public record CourseResponse(
        Long id,
        String name,
        List<CategoryResponse> categories
) {
}
