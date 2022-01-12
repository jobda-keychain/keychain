package com.jobda.keychain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginApiRequest {
    private String id;
    private String password;
}
