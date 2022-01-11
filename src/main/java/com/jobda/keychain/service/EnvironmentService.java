package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;

import com.jobda.keychain.exception.AlreadyHasEnvironmentException;
import com.jobda.keychain.exception.PlatformNotFoundException;
import com.jobda.keychain.exception.ServiceTypeInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnvironmentService {

    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

    public void addEnvironment(AddEnvironmentRequest request) {
        ServiceType serviceType;
        try {
            serviceType = ServiceType.valueOf(request.getPlatform());
        } catch(IllegalArgumentException e) {
            throw ServiceTypeInvalidException.EXCEPTION;
        }

        Platform platform = platformRepository.findByName(serviceType)
                .orElseThrow(() -> PlatformNotFoundException.EXCEPTION);

        if (environmentRepository.existsByPlatformAndName(platform, request.getName())) {
            throw AlreadyHasEnvironmentException.EXCEPTION;
        }

        Environment environment = Environment.createEnvironment(request, platform);
        environmentRepository.save(environment);
    }

}
