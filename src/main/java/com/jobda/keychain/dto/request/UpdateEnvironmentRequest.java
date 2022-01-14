package com.jobda.keychain.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateEnvironmentRequest {

    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @Length(min = 2, max = 255)
    @NotBlank
    private String serverDomain;

    @Length(min = 2, max = 255)
    @NotBlank
    private String clientDomain;

}
