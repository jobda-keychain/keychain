package com.jobda.keychain.exception;

import com.jobda.keychain.exception.handler.KeyChainException;

public class PlatformNotFoundException extends KeyChainException {

    public static final PlatformNotFoundException EXCEPTION = new PlatformNotFoundException();

    protected PlatformNotFoundException() {
        super(404, "Platform is not found");
    }

}
