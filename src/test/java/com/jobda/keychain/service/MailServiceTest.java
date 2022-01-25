package com.jobda.keychain.service;

import com.jobda.keychain.KeychainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
@ActiveProfiles("test")
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendMailTest() {
        mailService.sendMail("test@gmail.com");
    }

}