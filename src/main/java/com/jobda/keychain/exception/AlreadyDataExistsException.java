package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;
import org.springframework.http.HttpStatus;

public class AlreadyDataExistsException extends KeyChainException {
    private static final int status = HttpStatus.CONFLICT.value();

    public AlreadyDataExistsException(String message) {
            super(status, message);
    }
}
