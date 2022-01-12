package com.jobda.keychain;

import com.jobda.keychain.dto.request.LoginApiRequest;
import com.jobda.keychain.dto.response.LoginApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;

@FeignClient(name = "jobda-auth-api", url = "place-holder")
public interface AuthApiClient {

    @PostMapping(headers = {HttpHeaders.ACCEPT + "=" + "*/*"})
    LoginApiResponse login(URI baseUrl, LoginApiRequest requestBody);


}
