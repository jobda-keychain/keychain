package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse.EnvironmentsDto;
import com.jobda.keychain.entity.platform.ServiceType;

import java.util.List;

public interface EnvironmentRepositoryExtension {
    List<EnvironmentsDto> findAllByPlatformEnvironments(ServiceType platformType);
}