package com.alura.foroAlura.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "User email is required")
        String email,
        @NotBlank(message = "User password is required")
        String password
) {
}
