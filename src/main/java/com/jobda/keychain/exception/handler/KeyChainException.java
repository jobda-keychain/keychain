package com.jobda.keychain.exception.handler;

import lombok.Getter;

@Getter
public class KeyChainException extends RuntimeException {

    private final int status;
    private final String message;

    protected KeyChainException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
