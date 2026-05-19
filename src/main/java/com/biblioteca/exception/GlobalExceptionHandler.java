package com.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", String.valueOf(System.currentTimeMillis()));

        // Determinar el código HTTP basado en el mensaje
        if (ex.getMessage() != null && ex.getMessage().contains("no encontrado")) {
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } else if (ex.getMessage() != null && ex.getMessage().contains("no se encuentra DISPONIBLE")) {
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error interno del servidor");
        errorResponse.put("details", ex.getMessage());
        errorResponse.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
