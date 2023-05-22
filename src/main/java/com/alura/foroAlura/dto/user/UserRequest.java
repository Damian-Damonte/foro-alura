package com.alura.foroAlura.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "User name is required")
        @Size(max = 100, message = "User name cannot be longer than 100 characters")
        String name,
        @NotBlank(message = "User email is required")
        @Size(max = 100, message = "User email cannot be longer than 100 characters")
        String email,
        @NotBlank(message = "User password is required")
        @Size(max = 100, message = "User password cannot be longer than 100 characters")
        String password
) {
}
