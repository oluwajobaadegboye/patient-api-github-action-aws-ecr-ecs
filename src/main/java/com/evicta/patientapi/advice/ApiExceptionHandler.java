package com.evicta.patientapi.advice;

import com.evicta.patientapi.exception.DuplicateResourceException;
import com.evicta.patientapi.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePatientNotFound(PatientNotFoundException ex) {
        Map<String, Object> body = Map.of(
            "error", "Patient Not Found",
            "message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
        Map<String, Object> body = Map.of(
            "error", "Duplicate Resource",
            "message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(body);
    }
}
