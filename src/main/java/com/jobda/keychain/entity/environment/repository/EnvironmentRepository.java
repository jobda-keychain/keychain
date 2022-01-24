package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.Platform;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EnvironmentRepository extends CrudRepository<Environment, Long>, EnvironmentRepositoryExtension {
    boolean existsByPlatformAndName(Platform platform, String name);
    Optional<Environment> findByPlatformAndName(Platform platform, String name);
}
