package com.jobda.keychain.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    protected ResponseEntity<ErrorResponse> handleValidationException(final ValidationException e) {
        System.out.println(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        System.out.println(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException(final NullPointerException e) {
        System.out.println("helo " + e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        System.out.println(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConflictException(final DataIntegrityViolationException e) {
        System.out.println(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(409, e.getLocalizedMessage()), HttpStatus.CONFLICT);
    }

}
