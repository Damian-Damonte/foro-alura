package com.alura.foroAlura.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryResponse(
        @NotNull(message = "Category ID is required")
        Long id,
        @NotBlank(message = "Category name is required")
        @Size(max = 100, message = "Category name cannot be longer than 100 characters")
        String name
) {
}
