package com.descenedigital.exception;

import com.descenedigital.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
