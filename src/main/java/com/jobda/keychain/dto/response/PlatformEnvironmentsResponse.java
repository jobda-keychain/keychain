package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<EnvironmentDto> data;

    @Getter
    @AllArgsConstructor
    public static class EnvironmentDto {

        private final Long id;

        private final String name;

    }

}
