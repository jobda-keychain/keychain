package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailsResponse {
    private Long id;
    private String userId;
    private String password;
    private ServiceType platform;
    private String environment;
    private String description;
}
