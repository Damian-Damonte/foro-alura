package com.alura.foroAlura.dto.user;

import com.alura.foroAlura.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserResponse(
        @NotNull(message = "User id is required")
        Long id,
        @NotBlank(message = "User name is required")
        @Size(max = 100, message = "User name cannot be longer than 100 characters")
        String name,
        @NotBlank(message = "User email is required")
        @Size(max = 100, message = "User email cannot be longer than 100 characters")
        String email,
        @NotBlank(message = "User role is required")
        User.Role role
) {
}
