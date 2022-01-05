package com.jobda.keychain.controller;

import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }

}
