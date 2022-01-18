package com.jobda.keychain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateEnvironmentRequest {

    @ApiModelProperty(value = "환경 이름", dataType = "string(2~10)", required = true, example = "dv-1")
    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "서버 도메인", dataType = "string(2~255)", required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String serverDomain;

    @ApiModelProperty(value = "프론트 도메인", dataType = "string(2~255)", required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String clientDomain;

}
