package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.environment.Environment;
import lombok.Getter;

@Getter
public class DetailsAccountEnvironmentDto {

    private long id;

    private String label;

    public static DetailsAccountEnvironmentDto of(Environment environment) {
        DetailsAccountEnvironmentDto detailsAccountEnvironmentDto = new DetailsAccountEnvironmentDto();
        detailsAccountEnvironmentDto.id = environment.getId();
        detailsAccountEnvironmentDto.label = environment.getName();
        return detailsAccountEnvironmentDto;
    }
}