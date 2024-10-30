package com.wora.api_rest_survey_it.config;

import com.wora.api_rest_survey_it.DTO.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String message = "Validation errors: " + errors.toString();
        ErrorDTO errorResponse = new ErrorDTO(LocalDateTime.now(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorDTO> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        List<String> errors = ex.getAllValidationResults()
                .stream()
                .flatMap(result -> result.getResolvableErrors().stream())
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        String message = String.join(", ", errors);
        ErrorDTO errorResponse = new ErrorDTO(LocalDateTime.now(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }

        String message = String.join(", ", errors);
        ErrorDTO errorResponse = new ErrorDTO(LocalDateTime.now(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex) {
        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            message = "An unexpected error occurred";
        }
        ErrorDTO errorResponse = new ErrorDTO(LocalDateTime.now(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}