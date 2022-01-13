package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.dto.response.EnvironmentsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnvironmentRepositoryExtension {
    Page<EnvironmentsResponse.EnvironmentDto> findAllByPlatformEnvironment(Pageable page);
}
