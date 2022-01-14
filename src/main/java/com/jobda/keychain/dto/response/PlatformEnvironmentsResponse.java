package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<EnvironmentsDto> data;

    @Getter
    @AllArgsConstructor
    public static class EnvironmentsDto {

        private final Long id;

        private final String name;

        private final ServiceType platform;

    }

}
