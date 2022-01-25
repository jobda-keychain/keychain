package com.jobda.keychain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobda.keychain.KeychainApplication;
import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.dto.request.UpdateEnvironmentRequest;
import com.jobda.keychain.entity.platform.PlatformType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
@ActiveProfiles("test")
class EnvironmentControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    long environmentId_delete_200 = 4L;
    long environmentId_delete_400 = 2L;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void 환경_추가_200() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("pr-11", "https://github.com", "https://github.com", PlatformType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void 환경_추가_400_없는_플랫폼() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("pr-11", "https://github.com", "https://github.com", PlatformType.JOBDA_CMS);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void 환경_추가_NPE() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", null, PlatformType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void 환경_추가_존재하지_않는_플랫폼() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-8", "https://github.com", "https://github.com", null);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    void 중복되는_환경() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", "https://github.com", PlatformType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isConflict());
    }

    @Test
    void 환경_수정() throws Exception {
        UpdateEnvironmentRequest request = new UpdateEnvironmentRequest("pr-3", "https://www.midasit.com", "https://www.midasit.com");

        mvc.perform(put("/environments/" + environmentId_delete_200)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void 환경_수정_존재하지않는환경() throws Exception {
        UpdateEnvironmentRequest request = new UpdateEnvironmentRequest("dv", "https://www.midasit.com", "https://www.midasit.com");

        mvc.perform(put("/environments/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isNotFound());
    }

    @Test
    void 환경_수정_변경된게_없을_때() throws Exception {
        UpdateEnvironmentRequest request = new UpdateEnvironmentRequest("dv", "https://www.midasit.com", "https://www.midasit.com");

        mvc.perform(put("/environments/" + environmentId_delete_200)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void 환경_수정_겹치는_게_있을_때() throws Exception {
        UpdateEnvironmentRequest request = new UpdateEnvironmentRequest("dv", "https://www.midasit.com", "https://www.midasit.com");

        mvc.perform(put("/environments/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isConflict());
    }

    @Test
    void 환경_수정_유저O() throws Exception {
        UpdateEnvironmentRequest request = new UpdateEnvironmentRequest("pr-3", "https://www.midasit.com", "https://www.midasit.com");

        mvc.perform(put("/environments/" + environmentId_delete_400)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void 환경_삭제() throws Exception {
        mvc.perform(delete("/environments/" + environmentId_delete_200)
        ).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void 환경_목록() throws Exception {
        mvc.perform(get("/environments?size=2&page=0"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 환경_삭제_400() throws Exception {
        mvc.perform(delete("/environments/" + environmentId_delete_400)
        ).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void 환경_이름과_플랫폼_목록() throws Exception {
        mvc.perform(get("/environments/names")
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void 서비스에_대한_환경_목록_JOBDA() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBDA")
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void 서비스에_대한_환경_목록_JOBDA_CMS() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBDA_CMS")
        ).andExpect(status().isOk());
    }

}
