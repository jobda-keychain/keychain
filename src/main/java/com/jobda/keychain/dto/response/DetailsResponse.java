package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailsResponse {

    private Long id;

    private String userId;

    private String password;

    private PlatformType platform;

    private String environment;

    private String description;

}
