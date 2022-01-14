package com.jobda.keychain.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UpdateAccountRequest {

    @NotBlank
    @Length(min = 2, max = 20)
    private String userId;

    @NotBlank
    @Length(min = 2, max = 20)
    private String password;

    @Length(max = 100)
    private String description;
}
