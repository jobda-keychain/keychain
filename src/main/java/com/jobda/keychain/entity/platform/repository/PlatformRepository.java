package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlatformRepository extends CrudRepository<Platform, Long>, PlatformRepositoryExtension {
    Optional<Platform> findByName(ServiceType name);
}
