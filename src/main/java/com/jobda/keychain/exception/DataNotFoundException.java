package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;
import org.springframework.http.HttpStatus;

public class DataNotFoundException extends KeyChainException {
    private static final int status = HttpStatus.NOT_FOUND.value();

    public DataNotFoundException(String message) {
        super(status, message);
    }
}
