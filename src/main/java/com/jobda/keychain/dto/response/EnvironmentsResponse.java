package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EnvironmentsResponse {

    private final List<EnvironmentDto> data;

    private final long totalPages;

    @Getter
    public static class EnvironmentDto {

        private long id;

        private String name;

        private String serverDomain;

        private String clientDomain;

        private ServiceType platform;

        public static EnvironmentDto of(Environment environment) {
            EnvironmentDto environmentDto = new EnvironmentDto();
            environmentDto.id = environment.getId();
            environmentDto.name = environment.getName();
            environmentDto.serverDomain = environment.getServerDomain();
            environmentDto.clientDomain = environment.getClientDomain();
            environmentDto.platform = environment.getPlatform().getName();
            return environmentDto;
        }

    }

}
