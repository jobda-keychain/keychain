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

    @Test
    void 계정추가_201() throws Exception {

        CreateAccountRequest createAccountRequest = new CreateAccountRequest("sasy0113", "ssy0113", "", 6L);

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    void 계정추가_형식_검사_400() throws Exception {

        CreateAccountRequest requestWithoutId = new CreateAccountRequest(null, "ssy0113", "", 1230L);
        CreateAccountRequest requestWithoutPassword = new CreateAccountRequest("sasy0113", null, "", 1230L);
        CreateAccountRequest requestWithoutDescription = new CreateAccountRequest("sasy0113", "ajefkf", null, 1230L);
        CreateAccountRequest requestWithoutEnvironment = new CreateAccountRequest("sasy0113", "afkeofk", "", null);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutId))
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutPassword))
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutDescription))
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutEnvironment))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void 계정추가_환경_NotFound_404() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("sasy0113", "ssy0113", "", 100L);

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        ).andExpect(status().isNotFound());
    }

    @Test
    void 계정추가_중복_409() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("sasy0113", "ssy0113", "", 5L);

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        ).andExpect(status().isConflict());

    }


    @Test
    void 계정수정_201() throws Exception {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest("sasy0113", "ssy0113", "o");

        mvc.perform(put("/accounts/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateAccountRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    void 계정수정_400() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        UpdateAccountRequest requestWithoutId = new UpdateAccountRequest(null, "ssy0113", "aekof");
        UpdateAccountRequest requestWithoutPassword = new UpdateAccountRequest("sasy0113", null, "");
        UpdateAccountRequest requestWithoutDescription = new UpdateAccountRequest("sasy0113", "ssy0113", null);

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutId))
        ).andExpect(status().isBadRequest());

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutPassword))
        ).andExpect(status().isBadRequest());

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestWithoutDescription))
        ).andExpect(status().isBadRequest());

        UpdateAccountRequest requestLengthOverflow = new UpdateAccountRequest("sasy0113sasy0113sasy0113sasy0113", "ssy0113", "fadf");
        UpdateAccountRequest requestLengthUnderflow = new UpdateAccountRequest("asdfasdf", "s", "fadf");

        // 아이디(또는 패스워드)가 20자 이상일 때
        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestLengthOverflow))
        ).andExpect(status().isBadRequest());

        // 아이디(또는 패스워드)가 2자 이하일 때
        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestLengthUnderflow))
        ).andExpect(status().isBadRequest());

    }

    @Test
    void 계정수정_404() throws Exception {
        UpdateAccountRequest accountRequest = new UpdateAccountRequest("sasy0113", "ssy0113", "");

        mvc.perform(put("/accounts/1230")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(accountRequest))
        ).andExpect(status().isNotFound());

    }

    @Test
    void 계정수정_409() throws Exception {
        mvc.perform(put("/accounts/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        new ObjectMapper().writeValueAsString(new UpdateAccountRequest("sasy0113", "ssy0113", "d"))
                )
        ).andExpect(status().isConflict());
    }

    @Test
    void 계정_자동_로그인_200() throws Exception {
        mvc.perform(post("/accounts/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 계정_자동_로그인_404() throws Exception {
        mvc.perform(post("/accounts/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}