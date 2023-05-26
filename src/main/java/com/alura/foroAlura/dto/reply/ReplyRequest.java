package com.alura.foroAlura.dto.reply;

import com.alura.foroAlura.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReplyRequest(
        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message cannot be longer than 2000 characters")
        String message,
        @NotNull(message = "Topic id required") @Valid
        OnlyId topic
) {
}
