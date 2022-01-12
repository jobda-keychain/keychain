package com.jobda.keychain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateAccountRequest {

    @NotNull
    @Length(min = 2, max = 20)
    private String userId;

    @NotNull
    @Length(min = 2, max = 20)
    private String password;

    @Max(100)
    private String description;

    private Long environment;
}
