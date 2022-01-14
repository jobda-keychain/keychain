package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PlatformRepository extends CrudRepository<Platform, Long>, PlatformRepositoryExtension {
    Optional<Platform> findByName(ServiceType name);
}
