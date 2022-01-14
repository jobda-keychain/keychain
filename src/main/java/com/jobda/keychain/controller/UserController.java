package com.jobda.keychain.controller;

import com.jobda.keychain.dto.request.CreateAccountRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.dto.response.DetailsResponse;
import com.jobda.keychain.dto.response.TokenResponse;
import com.jobda.keychain.dto.response.UpdateAccountResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("")
    public List<Account> UserList() {
        return userService.selectUser();
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

    @PostMapping("/{id}")
    public TokenResponse loginAccount(@PathVariable Long id) {
        return userService.getToken(id);
    }
}
