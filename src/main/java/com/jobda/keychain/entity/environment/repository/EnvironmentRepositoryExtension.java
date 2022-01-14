package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnvironmentRepositoryExtension {
    List<Environment> findAllByPlatformType(ServiceType platformType);
    Page<Environment> findAllBy(Pageable page);
}
