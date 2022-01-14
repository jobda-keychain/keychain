package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnvironmentRepositoryExtension {
    List<Environment> findAllByPlatformEnvironments(ServiceType platformType);
    Page<Environment> findAllByPlatformEnvironment(Pageable page);
}
