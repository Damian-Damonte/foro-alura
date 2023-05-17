package com.alura.foroAlura.dto.course;

import com.alura.foroAlura.dto.common.OnlyId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CourseRequest (
        @NotBlank(message = "Category name is required")
        @Size(max = 100, message = "Category name cannot be longer than 100 characters")
        String name,
        @NotEmpty(message = "The course must have at least one category")
        List<OnlyId> categories
){
}
