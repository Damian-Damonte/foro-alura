package com.alura.foroAlura.dto.answer;

import com.alura.foroAlura.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AnswerResponse(
        @NotNull(message = "Answer ID is required")
        Long id,
        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message cannot be longer than 2000 characters")
        String message,
        @NotNull(message = "Answer creation date is required")
        LocalDateTime creationDate,
        @NotNull(message = "You must indicate whether the answer is the solution")
        boolean solution,
        @NotNull(message = "Topic course id required") @Valid
        OnlyId topic
) {
}
