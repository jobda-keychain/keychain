package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class DataNotFoundException extends KeyChainException {
    public static final DataNotFoundException EXCEPTION = new DataNotFoundException();

    protected DataNotFoundException() {
        super(404, "Data is not found");
    }
}
