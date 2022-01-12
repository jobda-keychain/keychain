package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.PlatformType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnvironmentServiceTest {

    @Test
    void 환경_추가() {
        //given
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com/syxxn", "https://github.com/syxxn", PlatformType.JOBDA);
        Platform platform = Platform.createPlatform(request.getPlatform());

        //when
        Environment environment = Environment.createEnvironment(request.getName(), request.getServerDomain(), request.getClientDomain(), platform);

        //then
        assertEquals(platform.getName(), PlatformType.JOBDA);
        assertEquals(environment.getPlatform(), platform);
        assertNotNull(environment.getName());
    }

}