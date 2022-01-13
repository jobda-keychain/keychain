package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.entity.platform.ServiceType;

import java.util.List;

public interface EnvironmentRepositoryExtension {
    List<PlatformEnvironmentsResponse.EnvironmentNameDto> findAllByPlatformEnvironments(ServiceType platformType);
}