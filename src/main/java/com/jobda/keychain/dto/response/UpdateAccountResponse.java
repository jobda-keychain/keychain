package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAccountResponse {
    private Long id;
    private String userId;
    private String password;
    private String platform;
    private String environment;
    private String description;
}
