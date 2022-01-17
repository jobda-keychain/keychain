package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DetailsResponse {

    private Long id;

    private String userId;

    private String password;

    private PlatformType platform;

    private String environment;

    private String description;

    public static DetailsResponse of(Account account){
        DetailsResponse detailsResponse = new DetailsResponse();
        detailsResponse.id = account.getId();
        detailsResponse.userId = account.getUserId();
        detailsResponse.password = account.getPassword();
        detailsResponse.platform = account.getEnvironment().getPlatform().getName();
        detailsResponse.environment = account.getEnvironment().getName();
        detailsResponse.description = account.getDescription();
        return detailsResponse;
    }

}
