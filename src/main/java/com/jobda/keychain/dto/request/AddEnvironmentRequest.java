package com.jobda.keychain.dto.request;

import com.jobda.keychain.entity.platform.PlatformType;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "환경 이름", dataType = "string", required = true, example = "dv-1")
    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "서버 도메인", dataType = "string", required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String serverDomain;

    @ApiModelProperty(value = "프론트 도메인", dataType = "string", required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String clientDomain;

    @ApiModelProperty(value = "플랫폼", dataType = "string", required = true, example = "JOBDA 또는 JOBDA_CMS")
    @NotNull
    private PlatformType platform;

}