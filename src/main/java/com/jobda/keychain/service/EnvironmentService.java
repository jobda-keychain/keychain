package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse.EnvironmentDto;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.event.LogEvent;
import com.jobda.keychain.event.handler.LogEventHandler;
import com.jobda.keychain.exception.AlreadyDataExistsException;
import com.jobda.keychain.exception.BadRequestException;
import com.jobda.keychain.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EnvironmentService {

    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

    private final ApplicationEventPublisher eventPublisher;

    /**
     * platform에 동일한 환경 이름이 존재한다면 409
     * environment에 platform이 속해있다.
     *
     * @author: syxxn
     **/
    @Transactional
    public void addEnvironment(String clientIpAddress, AddEnvironmentRequest request) {
        Platform platform = getPlatform(request.getPlatform());

        existsSameName(platform, request.getName());

        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);
        environmentRepository.save(environment);
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.ADD_ENVIRONMENT));
    }

    /**
     * 환경 관리 페이지에서 보여주는 환경 목록
     * 탭으로 플랫폼을 이동할 수 있기 때문에 필터링 필요함.
     *
     * @author: syxxn
     **/
    public EnvironmentsResponse getEnvironments(Pageable page, PlatformType platformType) {
        Page<Environment> environmentPage = environmentRepository.findAllByPageableAndPlatformType(page, platformType);

        List<EnvironmentsResponse.EnvironmentDto> environmentDtoList = environmentPage.stream()
                .map(EnvironmentsResponse.EnvironmentDto::of)
                .collect(Collectors.toList());

        return EnvironmentsResponse.builder()
                .data(environmentDtoList)
                .totalPages(environmentPage.getTotalPages())
                .build();
    }

    /**
     * 환경 수정
     * 환경에 속한 사람이 있는 경우 400
     * 환경에 속한 사람이 없는 경우에는 name과 도메인 수정이 가능
     * 동일한 플랫폼에 중복된 이름의 환경이 있는지 확인 -> 동일한 환경이면 수정, 다른 환경이면 409
     *
     * @author: syxxn
     **/
    @Transactional
    public void updateEnvironment(String clientIpAddress, long id, UpdateEnvironmentRequest request) {
        Environment environment = getEnvironment(id);
        existsAccount(environment);
        Optional<Environment> duplicateNameEnvironment = environmentRepository.findByPlatformAndName(environment.getPlatform(), request.getName());
        if (duplicateNameEnvironment.isPresent() && !environment.getId().equals(duplicateNameEnvironment.get().getId())) {
            throw new AlreadyDataExistsException("Same name exists on the platform");
        }
        environment.update(request.getName(), request.getServerDomain(), request.getClientDomain());
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.UPDATE_ENVIRONMENT));
    }

    /**
     * 환경 삭제
     * 환경에 속한 계정이 남아있는 경우 400 반환,
     * 계정이 없으면 삭제 가능
     *
     * @author: syxxn
     **/
    @Transactional
    public void deleteEnvironment(String clientIpAddress, long id) {
        Environment environment = getEnvironment(id);
        existsAccount(environment);

        environmentRepository.delete(environment);
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.DELETE_ENVIRONMENT));
    }

    /**
     * platform(null인 경우)에는 환경 목록 전달
     * platform(null이 아닌 경우)에 속해있는 environment 목록 전달
     *
     * @author: syxxn
     **/
    public PlatformEnvironmentsResponse getEnvironmentListOfPlatform(PlatformType platformType) {
        List<Environment> environments = environmentRepository.findAllByPlatformType(platformType);

        List<EnvironmentDto> environmentsDtoList = environments.stream()
                .map(EnvironmentDto::of)
                .collect(Collectors.toList());

        return new PlatformEnvironmentsResponse(environmentsDtoList);
    }

    private Platform getPlatform(PlatformType platformType) {
        return platformRepository.findByName(platformType)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("platform not found");
                });
    }

    private Environment getEnvironment(long id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("environment not found");
                });
    }

    private void existsSameName(Platform platform, String name) {
        if (environmentRepository.existsByPlatformAndName(platform, name)) {
            throw new AlreadyDataExistsException("same name exists on the platform");
        }
    }

    private void existsAccount(Environment environment) {
        if (environment.getAccounts().size() != 0) {
            throw new BadRequestException("still have accounts in this environment");
        }
    }

}