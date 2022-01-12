package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class UnableLoginException extends KeyChainException {
    public static final UnableLoginException EXCEPTION = new UnableLoginException();

    protected UnableLoginException() {
        super(401, "Unable Login Exception");
    }
}
