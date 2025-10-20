package com.system.laboratory_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleEnumConversionError(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", "Invalid value for parameter: " + ex.getName());
        response.put("expectedType", ex.getRequiredType() != null ? ex.getRequiredType() : String.class);
        response.put("invalidValue", ex.getValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.toString());
    }
}
