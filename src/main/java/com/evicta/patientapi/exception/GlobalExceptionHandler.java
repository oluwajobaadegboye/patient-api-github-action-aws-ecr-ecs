package com.evicta.patientapi.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("error", "ValidationFailed");
        body.put("fields", ex.getBindingResult().getFieldErrors().stream().map(f -> Map.of(
                "field", f.getField(),
                "message", f.getDefaultMessage()
        )).toList());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGeneric(Exception ex) {
        return ResponseEntity.status(500).body(Map.of("error", "InternalError"));
    }
}
