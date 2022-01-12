package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.service.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/environments")
@RestController
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEnvironment(@RequestBody @Valid AddEnvironmentRequest request) {
        environmentService.addEnvironment(request);
    }

    @GetMapping("/search")
    public EnvironmentsResponse getEnvironmentsOfService(@RequestParam ServiceType platform) {
        return environmentService.getEnvironmentsOfService(platform);
    }

}
