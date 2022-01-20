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

        CreateAccountRequest createAccountRequest = new CreateAccountRequest("sasy0113", "ssy0113", "", 1L);

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    void 계정추가_형식_검사_400() throws Exception {
        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "    \"environmentId\": 1230,\n" +
                        "    \"description\": \"\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"environmentId\": 1230,\n" +
                        "    \"description\": \"\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "    \"description\": \"\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "    \"environmentId\": 1230,\n" +
                        "}")
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
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("sasy0113", "ssy0113", "", 1L);

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        );

        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createAccountRequest))
        ).andExpect(status().isConflict());

    }


    @Test
    void 계정수정_201() throws Exception {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest("sasy0113", "ssy0113", "o");

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateAccountRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    void 계정수정_400() throws Exception {
        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"description\": \"o\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "    \"description\": \"o\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        // 아이디(또는 패스워드)가 20자 이상일 때
        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113sasy0113sasy0113sasy0113\",\n" +
                        "    \"password\": \"ssy0113\",\n" +
                        "    \"description\": \"o\"\n" +
                        "}")
        ).andExpect(status().isBadRequest());

        // 아이디(또는 패스워드)가 2자 이하일 때
        mvc.perform(put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"sasy0113\",\n" +
                        "    \"password\": \"s\",\n" +
                        "    \"description\": \"o\"\n" +
                        "}")
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
//        todo 409는 나중에 해야할 거 같아요. 원인을 모르겠어요...
//        mvc.perform(put("/accounts/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(
//                        new ObjectMapper().writeValueAsString( new UpdateAccountRequest("sasy0113", "ssy0113", "d"))
//                )
//        );
//
//        mvc.perform(get("/accounts?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)
//                ).andDo(it-> {
//            System.out.println(it.getResponse().getContentAsString());
//        });

    }


}