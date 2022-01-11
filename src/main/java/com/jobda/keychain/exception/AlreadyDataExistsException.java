package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class AlreadyDataExistsException extends KeyChainException {
    public static final AlreadyDataExistsException EXCEPTION = new AlreadyDataExistsException();

    protected AlreadyDataExistsException() {
            super(409, "Already data is exists");
    }
}
