package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.exception.AlreadyDataExistsException;
import com.jobda.keychain.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnvironmentService {

    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

    public void addEnvironment(AddEnvironmentRequest request) {
        Platform platform = platformRepository.findByName(request.getPlatform())
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Platform not found");
                });

        if (environmentRepository.existsByPlatformAndName(platform, request.getName())) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }

        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);
        environmentRepository.save(environment);
    }

    @Transactional(readOnly = true)
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

}