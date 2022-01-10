package com.jobda.keychain.controller;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import com.jobda.keychain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
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


    @PutMapping("/{userIdx}")
    public void updateUser(@RequestBody UpdateUserRequest request, @PathVariable int userIdx) {
        userService.updateUser(userIdx, request);
    }


    @GetMapping
    public List<Account> UserList(@RequestParam(value = "platform", required = false) String platform, @RequestParam(value = "environment", required = false) List<String> environment) {

        return userService.selectUser();
    }

    @DeleteMapping("/{idx}")
    public String deleteUser(@PathVariable Integer idx) {
        userService.deleteUser(idx);
        return "success";
    }

}
