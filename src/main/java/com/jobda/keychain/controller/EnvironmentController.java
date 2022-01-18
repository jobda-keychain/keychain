package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.service.EnvironmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "환경")
@RequiredArgsConstructor
@RequestMapping("/environments")
@RestController
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @ApiOperation(value = "환경 추가", notes = "환경을 추가한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEnvironment(@RequestBody @Valid AddEnvironmentRequest request) {
        environmentService.addEnvironment(request);
    }
  
    @GetMapping
    public EnvironmentsResponse getEnvironments(Pageable page) {
        return environmentService.getEnvironments(page);
    }

    @PutMapping("/{id}")
    public void updateEnvironment(@PathVariable long id, @RequestBody @Valid UpdateEnvironmentRequest request) {
        environmentService.updateEnvironment(id, request);
    }
  
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEnvironment(@PathVariable long id) {
        environmentService.deleteEnvironment(id);
    }
  
    @GetMapping("/search")
    public PlatformEnvironmentsResponse getEnvironmentsOfService(@RequestParam(required = false) PlatformType platform) {
        return environmentService.getEnvironmentsOfService(platform);
    }

}
