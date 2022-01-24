package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.dto.response.DetailsResponse;
import com.jobda.keychain.dto.response.SelectAccountResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.service.AccountService;
import com.jobda.keychain.utils.LogUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "계정", description = "계정에 대한 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Operation(tags=  "계정", summary = "계정 정보 추가", description = "계정을 추가하는 API")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createAccount(HttpServletRequest servletRequest,
                              @RequestBody @Valid CreateAccountRequest request) {
        accountService.createAccount(LogUtil.getClientIp(servletRequest), request);
    }

    @Operation(tags = "계정", summary = "계정 정보 수정", description = "계정을 수정하는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public UpdateAccountResponse updateAccount(HttpServletRequest servletRequest,
                                            @RequestBody @Valid UpdateAccountRequest request,
                                            @Parameter(description = "계정의 id") @PathVariable long id) {
        return accountService.updateAccount(LogUtil.getClientIp(servletRequest), id, request);
    }

    @Operation(tags = "계정", summary = "계정 정보 조회", description = "계정을 전체 조회하거나 필터링하여 조회하는 API")
    @GetMapping
    public SelectAccountResponse selectAccount(@RequestParam(value = "size") int size,
                                            @RequestParam(value = "page") int page,
                                            @RequestParam(value = "platform", required = false) PlatformType platform,
                                            @RequestParam(value = "environment", required = false) List<Long> environmentIds) {

        Pageable pageable = PageRequest.of(page, size);
        return accountService.selectAccount(pageable, platform, environmentIds);
    }

    @Operation(tags = "계정", summary = "계정 정보 삭제", description = "계정을 삭제하는 API")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(HttpServletRequest servletRequest,
                           @PathVariable long id) {
        accountService.deleteAccount(LogUtil.getClientIp(servletRequest), id);
    }

    @Operation(tags = "계정", summary = "계정 정보 상세 조회", description = "해당 계정의 상세 정보를 조회하는 API")
    @GetMapping("/details/{id}")
    public DetailsResponse detailsAccount(HttpServletRequest servletRequest,
                                       @PathVariable long id) {
        return accountService.detailsAccount(LogUtil.getClientIp(servletRequest), id);
    }

    @Operation(tags = "계정", summary = "자동 로그인", description = "해당 계정 정보로 토큰을 발급해 로그인한다")
    @PostMapping("/{id}")
    public TokenResponse loginAccount(@Parameter(description = "계정의 id") @PathVariable Long id) {
        return accountService.getToken(id);
    }

}
