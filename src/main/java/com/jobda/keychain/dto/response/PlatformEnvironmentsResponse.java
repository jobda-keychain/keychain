package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlatformEnvironmentsResponse {

    private final List<EnvironmentsDto> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnvironmentsDto {

        private Long id;

        private String name;

        private ServiceType platform;

    }

}
