package com.jobda.keychain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobda.keychain.KeychainApplication;
import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
@ActiveProfiles("test")
class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }



}