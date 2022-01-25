package com.jobda.keychain.service;

import com.jobda.keychain.KeychainApplication;
import com.jobda.keychain.entity.log.MethodType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    void saveLogTest() {
        logService.saveRequestLog(MethodType.ADD_ENVIRONMENT, "http://localhost:8080");
    }

}