package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.environment.Environment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<PlatformEnvironmentDto> data;

    @Getter
    public static class PlatformEnvironmentDto {

        private Long id;

        private String name;

        public static PlatformEnvironmentDto of(Environment environment) {
            PlatformEnvironmentDto environmentDto = new PlatformEnvironmentDto();
            environmentDto.id = environment.getId();
            environmentDto.name = environment.getName();
            return environmentDto;
        }

    }

}
