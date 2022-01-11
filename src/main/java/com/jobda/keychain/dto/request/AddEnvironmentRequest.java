package com.jobda.keychain.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddEnvironmentRequest {

    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @NotBlank
    private String serverDomain;

    @NotBlank
    private String clientDomain;

    @NotBlank
    private String platform;

    public static AddEnvironmentRequest createAddEnvironmentRequest(String name, String serverDomain, String clientDomain, String platform) {
        AddEnvironmentRequest request = new AddEnvironmentRequest();
        request.name = name;
        request.serverDomain = serverDomain;
        request.clientDomain = clientDomain;
        request.platform = platform;

        return request;
    }

}
