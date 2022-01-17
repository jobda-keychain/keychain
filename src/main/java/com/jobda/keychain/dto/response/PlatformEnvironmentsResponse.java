package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<EnvironmentDto> data;

    @Getter
    public static class EnvironmentDto {

        private Long id;

        private String name;

        private ServiceType platform;

        public static EnvironmentDto of(Environment environment) {
            EnvironmentDto environmentDto = new EnvironmentDto();
            environmentDto.id = environment.getId();
            environmentDto.name = environment.getName();
            environmentDto.platform = environment.getPlatform().getName();
            return environmentDto;
        }

    }

}
