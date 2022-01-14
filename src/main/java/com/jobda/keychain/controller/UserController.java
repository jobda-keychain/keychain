package com.jobda.keychain.controller;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateAccountRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }


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

    @DeleteMapping("/{idx}")
    public String deleteUser(@PathVariable Integer idx) {
        userService.deleteUser(idx);
        return "success";
    }

    @PostMapping("/{id}")
    public TokenResponse loginAccount(@PathVariable Long id) {
        return userService.getToken(id);
    }

}
