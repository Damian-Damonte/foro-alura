package com.alura.foroAlura.dto.topic;

import com.alura.foroAlura.dto.user.UserResponse;
import com.alura.foroAlura.model.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record TopicResponse(
        @NotNull(message = "Course ID is required")
        Long id,
        @NotBlank(message = "Topic title is required")
        @Size(max = 100, message = "Topic title cannot be longer than 100 characters")
        String title,
        @NotBlank(message = "Topic message is required")
        @Size(max = 100, message = "Topic message cannot be longer than 2000 characters")
        String message,
        @NotNull(message = "Topic creation date is required")
        LocalDateTime creationDate,
        @NotNull(message = "Topic status date is required")
        Topic.TopicStatus status,
        @NotNull(message = "Topic course is required")
        CourseDTO course,
        List<ReplyDTO> replies,
        @NotNull(message = "Topic user ID is required")
        UserResponse user
) {
        public record CourseDTO(
                Long id,
                String name
        ){}

        public record ReplyDTO(
                Long id,
                String message,
                LocalDateTime creationDate,
                boolean solution
        ){}
}
