package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EnvironmentsResponse {

    private final List<EnvironmentDto> data;

    private final long totalPages;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EnvironmentDto {

        private final long id;

        private final String name;

        private final String serverDomain;

        private final String clientDomain;

        private final ServiceType platform;

    }

}
