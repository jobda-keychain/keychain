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
    public SelectUserResponse selectUser(Pageable pageable, ServiceType platform, List<Long> ids) {
        List<Platform> platforms = platformRepository.findByName(platform);
        ArrayList<Environment> environments = new ArrayList<>();

        Page<SelectUserResponse.SelectUserDto> selectUser = platformRepository.selectUser(pageable, platform, ids);
        return new SelectUserResponse(selectUser.toList(), selectUser.getTotalPages());
    }

    public void deleteUser(long id) {
        accountRepository.deleteById(id);
        //계정이 존재하지 않는 경우에도 확인해야 할 듯
    }
}