package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SelectUserDto{

    private final Long id;

    private final String userId;

    private final ServiceType platform;

    private final String environment;

    private final String description;

}