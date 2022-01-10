package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.exception.PlatformNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnvironmentService {

    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

    public void addEnvironment(AddEnvironmentRequest request) {
        Platform platform = platformRepository.findByName(ServiceType.valueOf(request.getPlatform()))
                .orElseThrow(() -> PlatformNotFoundException.EXCEPTION);

        Environment environment = Environment.createEnvironment(request, platform);
        environmentRepository.save(environment);
    }

}
