package com.alura.foroAlura.dto.answer;

import com.alura.foroAlura.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnswerRequest(
        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message cannot be longer than 2000 characters")
        String message,
        @NotNull(message = "Topic id required") @Valid
        OnlyId topic
) {
}
