package com.jobda.keychain.service;

import com.jobda.keychain.AuthApiClient;
import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.dto.response.DetailsResponse;
import com.jobda.keychain.dto.response.SelectUserDto;
import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;

import com.jobda.keychain.exception.DataNotFoundException;
import com.jobda.keychain.exception.UnableLoginException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final EnvironmentRepository environmentRepository;
    private final AuthApiClient authApiClient;
    private final PlatformRepository platformRepository;

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
    public void createUser(CreateAccountRequest request) {
        Environment environment = environmentRepository.findById(request.getEnvironmentId()).orElseThrow(() -> {
            throw new DataNotFoundException("Environment Not Found");
        });

        Account account = Account.createAccount(request.getUserId(), request.getPassword(), environment, request.getDescription());

        String token = callLoginApi(account.getUserId(), account.getPassword(), environment.getServerDomain());

        if (token == null || token.isBlank()) {
            throw UnableLoginException.EXCEPTION;
        }

        accountRepository.save(account);
    }

    /**
     * 계정 정보 수정
     * 성공하면 수정된 정보 반환
     * 계정 정보가 없으면 404 Not Found 발생
     * 로그인 실패 시 UnableLoginException 발생
     * Account Entity 중복 발생 시 409 Conflict 발생
     *
     * @author: sse
     **/
    @Transactional
    public UpdateAccountResponse updateUser(long id, UpdateAccountRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));

        account.changeInfo(request.getUserId(), request.getPassword(), request.getDescription());

        Environment environment = account.getEnvironment();

        String token = callLoginApi(account.getUserId(), account.getPassword(), environment.getServerDomain());

        if (token == null || token.isBlank()) {
            throw UnableLoginException.EXCEPTION;
        }

        accountRepository.save(account);

        return UpdateAccountResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .password(account.getPassword())
                .platform(environment.getPlatform().getName())
                .environment(environment.getName())
                .description(account.getDescription())
                .build();

    }

    public SelectUserResponse selectUser(Pageable pageable, ServiceType platform, List<Long> ids) {
        Page<SelectUserDto> selectUser = platformRepository.selectUser(pageable, platform, ids);
        return new SelectUserResponse(selectUser.toList(), selectUser.getTotalPages());
    }

    public DetailsResponse detailsUser(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("User Not Found");
        });
        String environment = account.getEnvironment().getName();
        ServiceType platform = account.getEnvironment().getPlatform().getName();
        return new DetailsResponse(account.getId(), account.getUserId(), account.getPassword(), platform, environment, account.getDescription());
    }

    @Transactional
    public void deleteUser(long id) {
        accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("userId Not Found");
        });
        accountRepository.deleteById(id);
    }

    /**
     * account id를 매개변수로 받고
     * 성공 시 TokenResponse 반환
     *
     * @author: sse
     **/
    public TokenResponse getToken(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User is not found"));

        String token = callLoginApi(account.getUserId(), account.getPassword(), account.getEnvironment().getServerDomain());

        return new TokenResponse(token);
    }

}