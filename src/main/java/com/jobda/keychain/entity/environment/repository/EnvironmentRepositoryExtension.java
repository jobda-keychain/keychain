package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.PlatformType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnvironmentRepositoryExtension {
    List<Environment> findAllByPlatformType(PlatformType platformType);
    Page<Environment> findAllByPageableAndPlatformType(Pageable page, PlatformType platformType);
}
