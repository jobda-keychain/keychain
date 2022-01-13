package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<EnvironmentNameDto> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnvironmentNameDto {

        private Long id;

        private String name;

        private ServiceType platform;

    }

}
