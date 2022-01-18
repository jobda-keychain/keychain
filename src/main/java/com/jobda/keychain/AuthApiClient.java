package com.jobda.keychain;

import com.jobda.keychain.dto.request.LoginApiRequest;
import com.jobda.keychain.dto.response.JobdaAccountInfoResponse;
import com.jobda.keychain.dto.response.LoginApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(name = "jobda-auth-api", url = "place-holder")
public interface AuthApiClient {

    @PostMapping(headers = {HttpHeaders.ACCEPT + "=" + "*/*"})
    LoginApiResponse login(URI baseUrl, LoginApiRequest requestBody);

    @GetMapping(headers = {HttpHeaders.ACCEPT + "=" + "*/*"})
    JobdaAccountInfoResponse getAccountInfo(URI baseUrl, @RequestHeader("authorization") String token);

}
