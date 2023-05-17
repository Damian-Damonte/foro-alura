package com.alura.foroAlura.exception;

import com.alura.foroAlura.dto.exception.BadRequest;
import com.alura.foroAlura.dto.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequest> badRequestExceptionHandler(BadRequestException e) {
        return ResponseEntity.badRequest().body(new BadRequest().addError(e.getMessage()));

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFound> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequest> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BadRequest response = new BadRequest();
        e.getBindingResult().getFieldErrors().forEach(error -> response.addError(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
    }
}
