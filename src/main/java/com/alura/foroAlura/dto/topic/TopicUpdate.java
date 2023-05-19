package com.alura.foroAlura.dto.topic;

import com.alura.foroAlura.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TopicUpdate(
        @NotBlank(message = "Topic title is required")
        @Size(max = 100, message = "Topic title cannot be longer than 100 characters")
        String title,
        @NotBlank(message = "Topic message is required")
        @Size(max = 100, message = "Topic message cannot be longer than 2000 characters")
        String message,
        @NotBlank(message = "Topic status is required")
        @Pattern(regexp = "UNANSWERED|UNRESOLVED|SOLVED|CLOSED",
                message = "The topic status must be UNANSWERED, UNANSWERED, SOLVED, or CLOSED")
        String status,
        @NotNull(message = "Topic course id required") @Valid
        OnlyId course
) {
}
