package com.jobda.keychain.service;

import com.jobda.keychain.AuthApiClient;
import com.jobda.keychain.dto.request.LoginApiRequest;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.exception.UnableLoginException;
import com.jobda.keychain.dto.request.CreateUserRequest;
import com.jobda.keychain.dto.request.UpdateUserRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final AuthApiClient authApiClient;


    /**
    * 외부 로그인 API 호출 메서드
    * 성공 시 Token 발급, 실패 시 UnableLoginException 발생
    *
    * @author: sse
    **/
    public String callLoginApi(String id, String password, String serverDomain) {
        URI uri = URI.create(serverDomain);
        LoginApiRequest apiRequest = new LoginApiRequest(id, password);

        try {
            return authApiClient.login(uri, apiRequest).getAccessToken();
        } catch (FeignException e) {
            // todo   예외 처리에 대한 기획 미정
            throw UnableLoginException.EXCEPTION;
        }
    }

    @Transactional
    public void createUser(CreateUserRequest request) {
        Account user = Account.createAccount(null, null, null, null);
        accountRepository.save(user);
    }

    @Transactional
    public void updateUser(long id, UpdateUserRequest request) {
        Account account = accountRepository.findById(id).orElseThrow();

        //account.update(account, request);

        accountRepository.save(account);
    }

    public void test() {

    }

    public List<Account> selectUser(){
        //response dto를 만들어서 반환하는 것이 좋을 것 같음.
        //return accountRepository.findAll();
        return new ArrayList<>();
    }
    public void deleteUser(long id){
        accountRepository.deleteById(id);
        //계정이 존재하지 않는 경우에도 확인해야 할 듯
    }
}