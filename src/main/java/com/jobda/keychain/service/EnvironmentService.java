package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.exception.AlreadyDataExistsException;
import com.jobda.keychain.exception.BadRequestException;
import com.jobda.keychain.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EnvironmentService {

    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

    /**
     * platform에 동일한 환경 이름이 존재한다면 409
     * environment에 platform이 속해있다.
     *
     * @author: syxxn
     **/
    @Transactional
    public void addEnvironment(AddEnvironmentRequest request) {
        Platform platform = getPlatform(request.getPlatform());

        if (environmentRepository.existsByPlatformAndName(platform, request.getName())) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }

        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);
        environmentRepository.save(environment);
    }

    /**
     * 환경 수정
     * 환경에 속한 사람이 있는 경우 400
     * 환경에 속한 사람이 없는 경우에는 name과 도메인 수정이 가능
     *
     * @author: syxxn
     **/
    @Transactional
    public void updateEnvironment(long id, UpdateEnvironmentRequest request) {
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Environment is not found");
                });

        if (environment.getAccounts().size() != 0) {
            throw new BadRequestException("Still have accounts in this environment");
        }

        if (environmentRepository.existsByPlatformAndName(environment.getPlatform(), request.getName())) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }

        environment.update(request.getName(), request.getServerDomain(), request.getClientDomain());
        environmentRepository.save(environment);
    }
      
     /** 
     * platform에 속해있는 environment 목록 전달
     *
     * @author: syxxn
     **/
    public PlatformEnvironmentsResponse getEnvironmentsOfService(ServiceType platformType) {
        Platform platform = getPlatform(platformType);

        return new PlatformEnvironmentsResponse(platform.getEnvironments().stream()
                .map(e -> new PlatformEnvironmentsResponse.EnvironmentDto(e.getId(), e.getName()))
                .collect(Collectors.toList())
        );
    }

    private Platform getPlatform(ServiceType platformType) {
        return platformRepository.findByName(platformType)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Platform not found");
                });
    }

}