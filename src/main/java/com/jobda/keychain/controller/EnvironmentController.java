package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.service.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
