package com.jobda.keychain.dto.request;

import com.jobda.keychain.entity.platform.PlatformType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddEnvironmentRequest {

    @Schema(description = "환경 이름", type = "string", minLength = 2, maxLength = 10, required = true, example = "dv-1")
    @Pattern(regexp = "^[\\S]{2,10}$")
    @NotBlank
    private String name;

    @Schema(description = "서버 도메인", type = "string", minLength = 2, maxLength = 255, required = true, example = "https://github.com/syxxn")
    @Pattern(regexp = "((?:https:\\/\\/|http:\\/\\/)(?:.*?))")
    @NotBlank
    private String serverDomain;

    @Schema(description = "프론트 도메인", type = "string", minLength = 2, maxLength = 255, required = true, example = "https://github.com/syxxn")
    @Pattern(regexp = "((?:https:\\/\\/|http:\\/\\/)(?:.*?))")
    @NotBlank
    private String clientDomain;

    @Schema(description = "플랫폼", type = "string", required = true, example = "JOBDA 또는 JOBDA_CMS")
    @NotNull
    private PlatformType platform;

}