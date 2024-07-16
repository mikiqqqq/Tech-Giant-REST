package com.spring_boot.webshop_app.advice;

import com.spring_boot.webshop_app.exception.EmailAlreadyTakenException;
import com.spring_boot.webshop_app.exception.RecordNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getMessage());
        return new ResponseEntity<>(
                details, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(
            RecordNotFoundException ex
    ) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public final ResponseEntity<Object> handleEmailAlreadyTakenException(
            EmailAlreadyTakenException ex
    ) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }
}