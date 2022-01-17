package com.jobda.keychain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateAccountRequest {

    @ApiModelProperty(value = "계정 아이디", dataType = "string", required = true, example = "string")
    @NotBlank
    @Length(min = 2, max = 20)
    private String userId;

    @ApiModelProperty(value = "계정 패스워드", dataType = "string", required = true, example = "string")
    @NotBlank
    @Length(min = 2, max = 20)
    private String password;

    @ApiModelProperty(value = "계정 설명", dataType = "string", required = true, example = "string")
    @NotNull
    @Length(max = 100)
    private String description;

}
