package com.jobda.keychain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateAccountRequest {

    @NotBlank
    @Length(min = 2, max = 20)
    private String userId;

    @NotBlank
    @Length(min = 2, max = 20)
    private String password;

    @Length(max = 100)
    private String description;

    @NotNull
    private Long environmentId;
}
