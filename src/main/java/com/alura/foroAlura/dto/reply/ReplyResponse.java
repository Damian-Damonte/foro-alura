package com.alura.foroAlura.dto.reply;

import com.alura.foroAlura.dto.common.OnlyId;
import com.alura.foroAlura.dto.user.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ReplyResponse(
        @NotNull(message = "Reply ID is required")
        Long id,
        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message cannot be longer than 2000 characters")
        String message,
        @NotNull(message = "Reply creation date is required")
        LocalDateTime creationDate,
        @NotNull(message = "You must indicate whether the reply is the solution")
        boolean solution,
        @NotNull(message = "Topic course id required") @Valid
        OnlyId topic,
        @NotNull(message = "User required")
        UserResponse user
) {
}
