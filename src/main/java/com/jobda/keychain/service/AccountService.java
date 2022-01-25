package com.jobda.keychain.service;

import com.jobda.keychain.AuthApiClient;
import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.LoginApiRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.dto.response.DetailsResponse;
import com.jobda.keychain.dto.response.SelectAccountDto;
import com.jobda.keychain.dto.response.SelectAccountResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.account.repository.AccountRepository;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.environment.repository.EnvironmentRepository;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.entity.platform.repository.PlatformRepository;
import com.jobda.keychain.event.LogEvent;
import com.jobda.keychain.exception.AlreadyDataExistsException;
import com.jobda.keychain.exception.DataNotFoundException;
import com.jobda.keychain.exception.UnableLoginException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
public class AccountService {

    private final AccountRepository accountRepository;
    private final EnvironmentRepository environmentRepository;
    private final AuthApiClient authApiClient;
    private final PlatformRepository platformRepository;
    private final MailService mailService;

    private final ApplicationEventPublisher eventPublisher;

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
    public void createAccount(String clientIpAddress, CreateAccountRequest request) {
        Environment environment = environmentRepository.findById(request.getEnvironmentId()).orElseThrow(() -> {
            throw new DataNotFoundException("environment not found");
        });

        Account account = Account.createAccount(request.getAccountId(), request.getPassword(), environment, request.getDescription());

        String token = callLoginApi(account.getAccountId(), account.getPassword(), environment.getServerDomain());

        if (token == null || token.isBlank()) {
            throw UnableLoginException.EXCEPTION;
        }

        accountRepository.save(account);
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.ADD_ACCOUNT));

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
    public UpdateAccountResponse updateAccount(String clientIpAddress, long id, UpdateAccountRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("account not found"));

        if (accountRepository.findByAccountIdAndEnvironment(request.getAccountId(), account.getEnvironment()).size() > 0) {
            throw new AlreadyDataExistsException("Same Account is already exists");
        }

        account.changeInfo(request.getAccountId(), request.getPassword(), request.getDescription());

        Environment environment = account.getEnvironment();

        String token = callLoginApi(account.getAccountId(), account.getPassword(), environment.getServerDomain());

        if (token == null || token.isBlank()) {
            throw UnableLoginException.EXCEPTION;
        }

        UpdateAccountResponse response = UpdateAccountResponse.builder()
                .id(account.getId())
                .accountId(account.getAccountId())
                .password(account.getPassword())
                .platform(environment.getPlatform().getName())
                .environment(environment.getName())
                .description(account.getDescription())
                .build();

        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.UPDATE_ACCOUNT));
        return response;
    }

    public SelectAccountResponse selectAccount(Pageable pageable, PlatformType platform, List<Long> environmentIds) {
        Page<SelectAccountDto> selectAccount = platformRepository.selectAccount(pageable, platform, environmentIds);
        List<SelectAccountDto> selectAccountDtoList = selectAccount.stream()
                .map(SelectAccountDto::of)
                .collect(Collectors.toList());

        return SelectAccountResponse.builder()
                .data(selectAccountDtoList)
                .totalPages(selectAccount.getTotalPages())
                .build();
    }

    public DetailsResponse detailsAccount(String clientIpAddress, long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("account not found");
        });
        DetailsResponse response = DetailsResponse.of(account);
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.DETAILS_ACCOUNT));
        return response;
    }

    @Transactional
    public void deleteAccount(String clientIpAddress, long id) {
        accountRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("account not found");
        });
        accountRepository.deleteById(id);
        eventPublisher.publishEvent(new LogEvent(clientIpAddress, MethodType.DELETE_ACCOUNT));
    }

    /**
     * account id를 매개변수로 받고
     * 성공 시 TokenResponse 반환
     *
     * @author: sse
     **/
    public TokenResponse getToken(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("account not found"));
        Environment environment = account.getEnvironment();

        String token = callLoginApi(account.getAccountId(), account.getPassword(), environment.getServerDomain());

        return new TokenResponse(token, environment.getClientDomain());
    }

}