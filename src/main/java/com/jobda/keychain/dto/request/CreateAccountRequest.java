package com.jobda.keychain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateAccountRequest {

    @Schema(description = "계정 아이디", minLength = 2, maxLength = 20)
    @NotBlank
    @Length(min = 2, max = 20)
    private String accountId;

    @Schema(description = "계정 패스워드", minLength = 2, maxLength = 20)
    @NotBlank
    @Length(min = 2, max = 20)
    private String password;

    @Schema(description = "계정 설명", minLength = 0, maxLength = 100)
    @NotNull
    @Length(max = 100)
    private String description;

    @Schema(description = "환경의 id")
    @NotNull
    private Long environmentId;

}
