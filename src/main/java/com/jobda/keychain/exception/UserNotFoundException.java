package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class UserNotFoundException extends KeyChainException {
    private final UserNotFoundException EXCEPTION = new UserNotFoundException();

    protected UserNotFoundException() {
        super(404, "User not found");
    }
}
