package com.jobda.keychain.service;

import com.jobda.keychain.AuthApiClient;
import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.LoginApiRequest;
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
import com.jobda.keychain.entity.platform.PlatformType;
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
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final EnvironmentRepository environmentRepository;
    private final AuthApiClient authApiClient;
    private final PlatformRepository platformRepository;
    private final MailService mailService;

    /**
     * 외부 로그인 API 호출 메서드
     * 성공 시 Token 발급, 실패 시 UnableLoginException 발생
     *
     * @author: sse
     **/
    public String callLoginApi(String id, String password, String serverDomain) {
        URI uri = URI.create(serverDomain + AuthApiClient.loginPath);
        LoginApiRequest apiRequest = new LoginApiRequest(id, password);

        try {
            return authApiClient.login(uri, apiRequest).getAccessToken();
        } catch (FeignException e) {
            // todo   예외 처리에 대한 기획 미정
            throw UnableLoginException.EXCEPTION;
        }
    }

    /**
    * 계정 정보 생성 메서드
    * 계정 생성 성공 시, 해당 계정에 등록된 이메일을 조회하여 메일을 전송한다.
    *
    * @author: sse
    **/
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

        if(environment.getPlatform().getName() == PlatformType.JOBDA) {
            URI uri = URI.create(environment.getServerDomain()+ AuthApiClient.getAccountInfoPath);
            String email = authApiClient.getAccountInfo(uri, AuthApiClient.tokenType + token).getEmail();
            mailService.sendMail(email);
        }
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

    public SelectUserResponse selectUser(Pageable pageable, PlatformType platform, List<Long> environmentIds) {
        Page<SelectUserDto> selectUser = platformRepository.selectUser(pageable, platform, environmentIds);
        List<SelectUserDto> selectUserDtoList = selectUser.stream()
                .map(SelectUserDto::of)
                .collect(Collectors.toList());

        return SelectUserResponse.builder()
                .data(selectUserDtoList)
                .totalPages(selectUser.getTotalPages())
                .build();
    }

    public DetailsResponse detailsUser(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("User Not Found");
        });
        return DetailsResponse.of(account);
    }

    @Transactional
    public void deleteUser(long id) {
        accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("User Not Found");
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