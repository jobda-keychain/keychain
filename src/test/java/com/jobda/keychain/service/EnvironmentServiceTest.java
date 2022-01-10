package com.jobda.keychain.service;

import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class EnvironmentServiceTest {

    @Test
    void 서비스_찾기() throws Exception {
        //given
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com/syxxn", "https://github.com/syxxn", "JOBDA");
        Platform platform = Platform.createPlatform(ServiceType.valueOf(request.getPlatform()));

        //when
        Environment environment = Environment.createEnvironment(request, platform);

        //then
        assertEquals(platform.getName(), ServiceType.JOBDA);
        assertEquals(environment.getPlatform(), platform);
        assertNotNull(environment.getName());
    }

}