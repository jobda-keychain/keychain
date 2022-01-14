package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        existsSameName(platform, request.getName());

        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);
        environmentRepository.save(environment);
    }

    public EnvironmentsResponse getEnvironments(Pageable page) {
        Page<Environment> environmentPage = environmentRepository.findAllBy(page);

        return EnvironmentsResponse.builder()
                .data(environmentPage.stream()
                        .map(e -> new EnvironmentsResponse.EnvironmentDto(
                                e.getId(), e.getName(), e.getServerDomain(), e.getClientDomain(), e.getPlatform().getName()
                        )).collect(Collectors.toList())
                ).totalPages(environmentPage.getTotalPages())
                .build();
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
        Environment environment = getEnvironment(id);
        existsAccount(environment);
        existsSameName(environment.getPlatform(), request.getName());

        environment.update(request.getName(), request.getServerDomain(), request.getClientDomain());
    }

    /**
     * 환경 삭제
     * 환경에 속한 계정이 남아있는 경우 400 반환,
     * 계정이 없으면 삭제 가능
     *
     * @author: syxxn
     **/
    @Transactional
    public void deleteEnvironment(long id) {
        Environment environment = getEnvironment(id);
        existsAccount(environment);

        environmentRepository.delete(environment);
    }
  
     /**
     * platform(null인 경우)에는 환경 목록 전달
     * platform(null이 아닌 경우)에 속해있는 environment 목록 전달
     *
     * @author: syxxn
     **/
    public PlatformEnvironmentsResponse getEnvironmentsOfService(ServiceType platformType) {
        List<Environment> environments = environmentRepository.findAllByPlatformType(platformType);

        return new PlatformEnvironmentsResponse(environments.stream()
                .map(e -> new PlatformEnvironmentsResponse.EnvironmentsDto(
                        e.getId(), e.getName(), e.getPlatform().getName()
                )).collect(Collectors.toList()));
    }

    private Platform getPlatform(ServiceType platformType) {
        return platformRepository.findByName(platformType)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Platform not found");
                });
    }

    private Environment getEnvironment(long id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Environment is not found");
                });
    }

    private void existsSameName(Platform platform, String name) {
        if (environmentRepository.existsByPlatformAndName(platform, name)) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }
    }

    private void existsAccount(Environment environment) {
        if (environment.getAccounts().size() != 0) {
            throw new BadRequestException("Still have accounts in this environment");
        }
    }

}