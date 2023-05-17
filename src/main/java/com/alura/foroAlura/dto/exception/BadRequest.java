package com.alura.foroAlura.dto.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BadRequest {
    private int status = HttpStatus.BAD_REQUEST.value();
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors = new ArrayList<>();

    public BadRequest addError(String error) {
        this.errors.add(error);
        return this;
    }
}
