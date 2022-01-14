package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnvironmentRepositoryExtension {
    Page<Environment> findAllByPlatformEnvironment(Pageable page);
}
