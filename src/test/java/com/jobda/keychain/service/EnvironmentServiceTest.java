package com.jobda.keychain.service;

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
        Platform platform = Platform.createPlatform(PlatformType.JOBDA);

        //when
        Environment environment = Environment.createEnvironment("dv-1", "https://github.com/syxxn", "https://github.com/syxxn", platform);

        //then
        assertEquals(platform.getName(), PlatformType.JOBDA);
        assertEquals(environment.getPlatform(), platform);
        assertNotNull(environment.getName());
    }

}