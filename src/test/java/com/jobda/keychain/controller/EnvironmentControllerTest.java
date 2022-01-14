package com.jobda.keychain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobda.keychain.KeychainApplication;
import com.jobda.keychain.dto.request.AddEnvironmentRequest;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
@ActiveProfiles("test")
class EnvironmentControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private AccountRepository accountRepository;

    long environmentId_delete_200;
    long environmentId_delete_400;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        Platform platform = platformRepository.save(Platform.createPlatform(ServiceType.JOBDA));

        environmentRepository.save(Environment.createEnvironment("dv-5", "https://github.com", "https://github.com", platform));
        environmentRepository.save(Environment.createEnvironment("dv-6", "https://github.com", "https://github.com", platform));
        Environment environment = environmentRepository.save(Environment.createEnvironment("dv-2", "https://github.com", "https://github.com", platform));
        platform.getEnvironments().add(environment);

        environmentId_delete_200 = environmentRepository.save(Environment.createEnvironment("dv-9", "https://github.com", "https://github.com", platform)).getId();
        
        Account save = accountRepository.save(
                Account.createAccount("asdf", "asdf", environment, "")
        );
        environment.getAccounts().add(save);
        environmentId_delete_400 = environment.getId();
    }

    @Test
    void 환경_추가_200() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", "https://github.com", ServiceType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isCreated());
    }

    @Test
    void 환경_추가_NPE() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-1", "https://github.com", null, ServiceType.JOBDA);

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
    void 중복되는_환경() throws Exception {
        AddEnvironmentRequest request = new AddEnvironmentRequest("dv-2", "https://github.com", "https://github.com", ServiceType.JOBDA);

        mvc.perform(post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andExpect(status().isConflict());
    }

    @Test
    void 서비스에_대한_환경_목록_서비스필터X() throws Exception {
        mvc.perform(get("/environments/search")
        ).andExpect(status().isOk()).andDo(print());
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
    void 서비스에_대한_환경_목록() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBDA")
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void 서비스에_대한_환경_목록_400() throws Exception {
        mvc.perform(get("/environments/search?platform=JOBFLEX")
        ).andExpect(status().isBadRequest());
    }

}
