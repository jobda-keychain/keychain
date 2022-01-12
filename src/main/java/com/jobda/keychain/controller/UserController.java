package com.jobda.keychain.controller;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.dto.request.CreateUserRequest;
import com.jobda.keychain.dto.request.UpdateAccountRequest;
import com.jobda.keychain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@RequestBody @Valid UpdateAccountRequest request, @PathVariable long id) {
        userService.updateUser(id, request);
    }


    @GetMapping("")
    public List<Account> UserList() {
        return userService.selectUser();
    }

    @DeleteMapping("/{idx}")
    public String deleteUser(@PathVariable Integer idx) {
        userService.deleteUser(idx);
        return "success";
    }

}
