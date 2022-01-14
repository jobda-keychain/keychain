package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAccountResponse {

    private final Long id;

    private final String userId;

    private final String password;

    private final String platform;

    private final String environment;

    private final String description;

}
