package com.jobda.keychain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateEnvironmentRequest {


    @Schema(description = "환경 이름", type = "string", minLength = 2, maxLength = 10, required = true, example = "dv-1")
    @Length(min = 2, max = 10)
    @NotBlank
    private String name;

    @Schema(description = "서버 도메인", type = "string", minLength = 2, maxLength = 255, required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String serverDomain;

    @Schema(description = "프론트 도메인", type = "string", minLength = 2, maxLength = 255, required = true, example = "https://github.com/syxxn")
    @Length(min = 2, max = 255)
    @NotBlank
    private String clientDomain;

}
