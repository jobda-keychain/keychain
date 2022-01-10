package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class ServiceTypeInvalidException extends KeyChainException {
    public static final ServiceTypeInvalidException EXCEPTION = new ServiceTypeInvalidException();

    protected ServiceTypeInvalidException() {
        super(400, "Service type is invalid");
    }
}
