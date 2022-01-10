package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class AlreadyHasEnvironmentException extends KeyChainException {
    public static final AlreadyHasEnvironmentException EXCEPTION = new AlreadyHasEnvironmentException();

    protected AlreadyHasEnvironmentException() {
        super(409, "Service already has environment name");
    }
}
