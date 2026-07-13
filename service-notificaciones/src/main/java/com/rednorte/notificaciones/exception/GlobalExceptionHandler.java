package com.rednorte.notificaciones.exception;
import org.springframework.http.*; import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validacion(MethodArgumentNotValidException ex) {
        Map<String,String> e = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> e.put(((FieldError)err).getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(e);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> runtime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
