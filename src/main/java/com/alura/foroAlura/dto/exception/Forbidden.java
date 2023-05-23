package com.alura.foroAlura.dto.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class Forbidden {
    private String message;
    private int status = HttpStatus.FORBIDDEN.value();
    private ZonedDateTime timestamp = ZonedDateTime.now();
    public Forbidden(String message) {
        this.message = message;
    }
}
