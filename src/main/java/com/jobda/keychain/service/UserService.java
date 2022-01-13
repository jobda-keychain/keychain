package com.jobda.keychain.service;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.dto.request.CreateUserRequest;
import com.jobda.keychain.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final PlatformRepository platformRepository;
    private final EnvironmentRepository environmentRepository;

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

    /* 계정 리스트 */
    // 전체 탭
    public Page<Account> selectUser(Pageable pageable) {
        //response dto를 만들어서 반환하는 것이 좋을 것 같음.
        return accountRepository.findAll(pageable);
    }

    // 플랫폼 필터링
    public Page<Account> selectUser(Pageable pageable, ServiceType platform) {
//        List<Platform> platforms = platformRepository.findByName(platform);
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (Platform p : platforms) {
//            for (Environment e : p.getEnvironments()) {
//                List<Account> joinAccounts = accountRepository.findByEnvironment(e).orElseThrow();
//                accounts.addAll(joinAccounts);
//            }
//        }
        List<SelectUserResponse.SelectUserDto> platformList = platformRepository.selectUser(platform, null);
        List<Account> platformLists = platformRepository.selectUserA(platform, null);
        System.out.println("i.getId()" + "  "  + "i.getUserId()" + "  " + "i.getPlatform()" + "  " + "i.getEnvironment()" );
        for(var i : platformList){
            System.out.println(i.getId() + "  "  + i.getUserId() + "  " + i.getPlatform() + "  " + i.getEnvironment() );
//            System.out.println(i.getId() + "  " + i.getName());
        }
//        for(var i : platformLists){
//            System.out.println(i.getId() + "  "  + i.getUserId());
////            System.out.println(i.getId() + "  " + i.getName());
//        }
        /*
        platform = jobda
        platforms에 jobda인 레코드 저장
        platforms 에 environments 리스트 저장되어 있음
        그 envionments에 하나하나를 for문으로 돌려
        그걸 포함하고 있는 Account를 저장하고
        그걸 다시 accounts에 저장함.
         */
        //new PageImpl<Account>(accounts, pageable, accounts.size())
        return null;
    }

    // 플랫폼, 환경 필터링
    public Page<Account> selectUser(Pageable pageable, ServiceType platform, List<Long> ids) {
//        List<SelectUserResponse.SelectUserDto> platformList = platformRepository.selectUser(platform, ids);

        List<Platform> platforms = platformRepository.findByName(platform);
        ArrayList<Environment> environments = new ArrayList<>();

        /*
        List<Environment> Environments = environmentRepository.findByIdin(ids);
        Map<Long, Environment> collect =
                environments.stream()
                        .collect(Collectors.toMap(Environment::getId, Function.identity()));

        for (Platform p : platforms) {
            for (Long i : ids) {
                List<Environment> joinEnvironment = environmentRepository.findByPlatformAndId(p, i).orElseThrow();
                environments.addAll(joinEnvironment);
            }
        }
        */
        ArrayList<Account> accounts = new ArrayList<>();
        for (Environment e : environments) {
            List<Account> joinAccounts = accountRepository.findByEnvironment(e).orElseThrow();
            accounts.addAll(joinAccounts);
        }
        return new PageImpl<Account>(accounts, pageable, accounts.size());
    }

    public void deleteUser(long id) {
        accountRepository.deleteById(id);
        //계정이 존재하지 않는 경우에도 확인해야 할 듯
    }
}