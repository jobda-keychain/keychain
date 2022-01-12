package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends KeyChainException {
    private static final int status = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String message) {
        super(status, message);
    }
}