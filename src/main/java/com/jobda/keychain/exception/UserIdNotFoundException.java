package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class UserIdNotFoundException extends KeyChainException {
    private static final UserIdNotFoundException EXCEPTION = new UserIdNotFoundException();

    public UserIdNotFoundException() {
        super(404, "id not found");
    }
}
