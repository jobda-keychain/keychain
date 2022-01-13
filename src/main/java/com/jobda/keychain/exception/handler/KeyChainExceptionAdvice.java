package com.jobda.keychain.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class KeyChainExceptionAdvice {

    @ExceptionHandler(KeyChainException.class)
    protected ResponseEntity<ErrorResponse> handleKeyChainException(final KeyChainException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getStatus(), e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException() {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation exception"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException() {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Null pointer exception"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Method argument type is mismatch"), HttpStatus.BAD_REQUEST);
    }

}
