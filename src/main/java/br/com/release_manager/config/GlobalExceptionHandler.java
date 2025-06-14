package br.com.release_manager.config;

import br.com.release_manager.core.exception.ReleaseCreateException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReleaseCreateException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ReleaseCreateException ex, HttpServletRequest request) {
        Map<String, Object> body = extractErrorInfo(ex, request);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    private static Map<String, Object> extractErrorInfo(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("date", LocalDateTime.now());
        body.put("error", ex.getMessage());
        body.put("path", request.getRequestURI());
        body.put("method", request.getMethod());
        return body;
    }
}
