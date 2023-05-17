package com.alura.foroAlura.dto.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class NotFound {
    private String message;
    private int status = HttpStatus.NOT_FOUND.value();
    private ZonedDateTime timestamp = ZonedDateTime.now();
    public NotFound(String message) {
        this.message = message;
    }
}
