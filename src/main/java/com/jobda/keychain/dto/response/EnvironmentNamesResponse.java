package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EnvironmentNamesResponse {

    private final List<EnvironmentNameDto> data;

    @Getter
    public static class EnvironmentNameDto {

        private Long id;

        private String name;

        private PlatformType platform;

        public static EnvironmentNameDto of(Environment environment) {
            EnvironmentNameDto environmentDto = new EnvironmentNameDto();
            environmentDto.id = environment.getId();
            environmentDto.name = environment.getName();
            environmentDto.platform = environment.getPlatform().getName();
            return environmentDto;
        }

    }

}
