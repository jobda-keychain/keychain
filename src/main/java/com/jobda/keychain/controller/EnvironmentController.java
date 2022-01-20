package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.service.EnvironmentService;
import com.jobda.keychain.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "환경", description = "환경 정보와 관련된 API")
@RequiredArgsConstructor
@RequestMapping("/environments")
@RestController
public class EnvironmentController {

    private final EnvironmentService environmentService;
    private final LogService logService;

    @Operation(tags=  "환경", summary = "환경 추가", description = "환경을 추가한다.(성공하면 201)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEnvironment(HttpServletRequest servletRequest, @RequestBody @Valid AddEnvironmentRequest request) {
        environmentService.addEnvironment(request);
        logService.saveRequestLog(servletRequest.getRemoteAddr(), MethodType.ADD_ENVIRONMENT);
    }

    @Operation(tags=  "환경", summary = "환경 목록", description = "환경 관리 페이지에서 환경의 정보를 불러온다.(성공하면 200)")
    @GetMapping
    public EnvironmentsResponse getEnvironments(@Parameter(description = "가지고 오고 싶은 요소의 개수") @RequestParam(value = "size") int size,
                                                @Parameter(description = "몇 번째 페이지부터 갖고올지(0부터 시작)") @RequestParam(value = "page") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return environmentService.getEnvironments(pageable);
    }


    @Operation(tags=  "환경", summary = "환경 수정", description = "환경에 계정이 속해있지 않다면 정보를 수정할 수 있다.(성공하면 204)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateEnvironment(HttpServletRequest servletRequest,
                                  @Parameter(description = "환경의 PK") @PathVariable long id,
                                  @RequestBody @Valid UpdateEnvironmentRequest request) {
        environmentService.updateEnvironment(id, request);
        logService.saveRequestLog(servletRequest.getRemoteAddr(), MethodType.UPDATE_ENVIRONMENT);
    }

    @Operation(tags=  "환경", summary = "환경 삭제", description = "환경에 계정이 속해있지 않다면 정보를 삭제할 수 있다.(성공하면 204)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEnvironment(HttpServletRequest servletRequest,
                                  @Parameter(description = "환경의 PK") @PathVariable long id) {
        environmentService.deleteEnvironment(id);
        logService.saveRequestLog(servletRequest.getRemoteAddr(), MethodType.DELETE_ENVIRONMENT);
    }

    @Operation(tags=  "환경", summary = "환경 삭제", description = "환경에 계정이 속해있지 않다면 정보를 삭제할 수 있다.(성공하면 204)")
    @GetMapping("/search")
    public PlatformEnvironmentsResponse getEnvironmentsOfService(@Parameter(description = "플랫폼(JOBDA, JOBDA_CMS)") @RequestParam(required = false) PlatformType platform) {
        return environmentService.getEnvironmentsOfService(platform);
    }

}
