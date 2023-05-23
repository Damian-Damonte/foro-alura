package com.alura.foroAlura.dto.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class Unauthorized {
    private int status = HttpStatus.UNAUTHORIZED.value();
    private ZonedDateTime timestamp = ZonedDateTime.now();
}
