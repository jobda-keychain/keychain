package com.jobda.keychain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String id;
    private String pw;
    private int service;
    private int stage;
    private String des;

}
