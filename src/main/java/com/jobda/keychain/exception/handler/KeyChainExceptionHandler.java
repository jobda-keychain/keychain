package com.jobda.keychain.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class KeyChainExceptionHandler {

    @ExceptionHandler(KeyChainException.class)
    protected ResponseEntity<ErrorResponse> handleKeyChainException(final KeyChainException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getStatus(), e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException() {
        return new ResponseEntity<>(new ErrorResponse(400, "Validation exception"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException() {
        return new ResponseEntity<>(new ErrorResponse(400, "Null pointer exception"), HttpStatus.BAD_REQUEST);
    }

}
