package com.devshowcase.api.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException exception
    ) {
        Map<String, Object> body = new HashMap<>();

        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        Map<String, Object> body = new HashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");
        body.put("message", "Dados inválidos");
        body.put("errors", errors);
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(
            DuplicateResourceException exception
    ) {
        Map<String, Object> body = new HashMap<>();

        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", exception.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }
}