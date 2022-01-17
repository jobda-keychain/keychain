package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.dto.response.DetailsResponse;
import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "계정")
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "계정 정보 추가", notes = "계정을 추가한다")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateAccountRequest request) {
        userService.createUser(request);
    }

    @ApiOperation(value = "계정 정보 수정", notes = "계정을 수정한다")
    @ApiImplicitParam(name = "id", value = "계정의 id", required = true, dataType = "number", paramType = "path", defaultValue = "0")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public UpdateAccountResponse updateUser(@RequestBody @Valid UpdateAccountRequest request, @PathVariable long id) {
        return userService.updateUser(id, request);
    }

    @GetMapping
    public SelectUserResponse selectUser(@RequestParam(value = "size") int size,
                                         @RequestParam(value = "page") int page,
                                         @RequestParam(value = "platform", required = false) ServiceType platform,
                                         @RequestParam(value = "environment", required = false) List<Long> ids) {

        Pageable pageable = PageRequest.of(page, size);
        return userService.selectUser(pageable, platform, ids);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/details/{id}")
    public DetailsResponse detailsUser(@PathVariable long id) {
        return userService.detailsUser(id);
    }

    @ApiOperation(value = "자동 로그인", notes = "해당 계정 정보로 토큰을 발급해 로그인한다")
    @ApiImplicitParam(name = "id", value = "계정의 id", required = true, dataType = "number", paramType = "path", defaultValue = "0")
    @PostMapping("/{id}")
    public TokenResponse loginAccount(@PathVariable Long id) {
        return userService.getToken(id);
    }

}
