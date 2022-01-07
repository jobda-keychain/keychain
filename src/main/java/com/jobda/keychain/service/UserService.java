package com.jobda.keychain.service;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(CreateUserRequest request) {
        Account user = Account.createAccount(/*request 넣어야 함*/);
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