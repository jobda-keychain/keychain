package com.jobda.keychain.dto.request;

import lombok.*;

@Getter
@Setter
public class UpdateUserRequest {
    private String id;
    private String pw;
    private Integer service;
    private Integer stage;
    private String des;
}