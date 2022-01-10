package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class EnvironmentNotFoundException extends KeyChainException {
    public static final EnvironmentNotFoundException EXCEPTION = new EnvironmentNotFoundException();

    protected EnvironmentNotFoundException() {
        super(404, "Environment Not Found");
    }
}
