package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateAccountResponse {

    private final Long id;

    private final String userId;

    private final String password;

    private final PlatformType platform;

    private final String environment;

    private final String description;

}
