package com.jobda.keychain.dto.request;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddEnvironmentRequest {

    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @NotBlank
    private String serverDomain;

    @NotBlank
    private String clientDomain;

    @NotNull
    private ServiceType platform;

}