package com.jobda.keychain.controller;

import com.jobda.keychain.KeychainApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(classes = KeychainApplication.class)
@ActiveProfiles("test")
public class AccountControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /*
     *
     * 계정 조회
     * [200]
     * - 전체 조회
     * - 플랫폼 별 조회 (JOBDA, JOBDA_CMS)
     * - 플랫폼 + 환경 조회
     *
     * [400]
     * - 전체 조회 -> page, size 파라미터 없을 경우
     * - 플랫폼 조회 -> platformType에 없는 경우 (JOBDA, JODBA_CMS를 제외한 jobda같은 경우)
     * - 플랫폼 + 환경 조회 -> 정수형태가 아닌 경우 (환경 이름 등이 들어올 경우)
     *
     * 예외 (가능하지만 제공하지 않는 기능)
     * - 환경 조회 | 전체 조회 후 환경 조회 하지 않음. 환경 조회는 플랫폼 필터링과 함께만 가능함
     */

    // status 200
    @Test
    void 계정_전체_조회_200() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 계정_조희_플랫폼_필터링_JOBDA_200() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 계정_조희_플랫폼_필터링_JOBDA_CMS_200() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA_CMS"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 계정_조희_환경_필터링_200() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA&environment=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // 빈리스트 체크 코드 반영 전, 테스트 돌릴 시 body data 빈 배열
//    @Test
//    void 계정_조희_플랫폼_필터링_JOBDA_환경_빈리스트_필터링() throws Exception{
//        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA&environment="))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

    //status 400
    @Test
    void 계정_전체_조회_400() throws Exception {
        mvc.perform(get("/accounts"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void 계정_조희_플랫폼_필터링_400() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=jobda"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void 계정_조희_환경_필터링_400() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA&environment=dv"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    //status 404 - 예외처리해야됨
    @Test
    void 계정_조희_환경만_필터링_404() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&platform=JOBDA&environment=1000"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    //200 - 환경 조회
    @Test
    void 계정_조희_환경만_필터링_200() throws Exception {
        mvc.perform(get("/accounts?size=15&page=0&environment=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /*
     * 계정 삭제
     *
     * [204]
     * - 계정 삭제
     *
     * [404]
     * - 계정 삭제 -> id가 존재하지 않는 경우
     *
     * [400]
     * - 계정 삭제 -> id가 정수형이 아닐 경우
     *
     */
    @Test
    void 계정_삭제_204() throws Exception {
        mvc.perform(delete("/accounts/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void 계정_삭제_404() throws Exception {
        mvc.perform(delete("/accounts/1000"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void 계정_삭제_400() throws Exception {
        mvc.perform(delete("/accounts/abc"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
