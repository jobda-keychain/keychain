package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.PlatformType;
import lombok.Getter;

@Getter
public class DetailsResponse {

    private Long id;

    private String accountId;

    private String password;

    private PlatformType platform;

    private DetailsAccountEnvironmentDto environment;

    private String description;

    public static DetailsResponse of(Account account) {
        DetailsResponse detailsResponse = new DetailsResponse();
        detailsResponse.id = account.getId();
        detailsResponse.accountId = account.getAccountId();
        detailsResponse.password = account.getPassword();
        detailsResponse.platform = account.getEnvironment().getPlatform().getName();
        detailsResponse.environment = DetailsAccountEnvironmentDto.of(account.getEnvironment());
        detailsResponse.description = account.getDescription();
        return detailsResponse;
    }
}
