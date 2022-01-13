package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.exception.AlreadyDataExistsException;
import com.jobda.keychain.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional(rollbackFor = Exception.class)
    public void addEnvironment(AddEnvironmentRequest request) {
        Platform platform = getPlatform(request.getPlatform());

        if (environmentRepository.existsByPlatformAndName(platform, request.getName())) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }

        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);
        environmentRepository.save(environment);
    }

    public EnvironmentsResponse getEnvironments(Pageable page) {
        Page<Environment> environmentPage = environmentRepository.findAllBy(page);

        return EnvironmentsResponse.builder()
                .data(environmentPage
                        .map(e -> EnvironmentsResponse.EnvironmentDto.builder()
                                .id(e.getId())
                                .name(e.getName())
                                .serverDomain(e.getServerDomain())
                                .clientDomain(e.getClientDomain())
                                .platform(e.getPlatform().getName())
                                .build())
                        .stream().collect(Collectors.toList())
                )
                .totalPages(environmentPage.getTotalPages())
                .build();
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