package nic.epsdd.biddermanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Global exception handler for managing exceptions across the application
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Handle validation errors (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> responseError = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> responseError.put(error.getField(), error.getDefaultMessage()));
        log.warn("Validation errors: {}", responseError);
        return ResponseEntity.badRequest().body(responseError);
    }

    // Handle custom BidderException for user-related errors
    @ExceptionHandler(BidderException.class)
    public ResponseEntity<Map<String, String>> handleBidderException(BidderException e) {
        Map<String, String> responseErrors = new HashMap<>();
        responseErrors.put("error", e.getMessage());
        log.error("Bidder exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrors);
    }

    // Handle any other uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception e) {
        Map<String, String> responseErrors = new HashMap<>();
        responseErrors.put("error", "An unexpected error occurred. Please try again later." + e.getMessage());
        log.error("Unexpected error occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrors);
    }
}
