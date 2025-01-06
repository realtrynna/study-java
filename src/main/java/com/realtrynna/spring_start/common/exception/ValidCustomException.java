package com.realtrynna.spring_start.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidCustomException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidCustomException(MethodArgumentNotValidException ex) {
        Map<String, String> errorDetails = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorDetails.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
