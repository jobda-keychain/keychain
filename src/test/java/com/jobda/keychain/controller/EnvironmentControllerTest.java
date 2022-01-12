package com.jobda.keychain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobda.keychain.KeychainApplication;
import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
class EnvironmentControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        Platform platform = platformRepository.save(Platform.createPlatform(PlatformType.JOBDA));
        environmentRepository.save(Environment.createEnvironment("dv-2", "https://github.com", "https://github.com", platform));
    }

    @Test
    void 환경_추가_200() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", "https://github.com", PlatformType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isCreated());
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
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", "https://github.com", null);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void 환경_추가_저장되어_있지_않은_플랫폼() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", "https://github.com", PlatformType.JOBDA_CMS);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isNotFound());
    }

    @Test
    void 중복되는_환경() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-2", "https://github.com", "https://github.com", PlatformType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isConflict());
    }

    @Test
    void 서비스에_대한_환경_목록() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBDA")
        ).andExpect(status().isOk());
    }

    @Test
    void 서비스에_대한_환경_목록_400() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBFLEX")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void 서비스에_대한_환경_목록_404() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBDA_CMS")
        ).andExpect(status().isNotFound());
    }

}
