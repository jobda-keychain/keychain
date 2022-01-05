package com.jobda.keychain.controller;

import com.jobda.keychain.entity.User;
import com.jobda.keychain.repository.UserRepository;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }

    @GetMapping("/read")
    public List<User> UserList() {
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        for (User user : iterable) {
            list.add(user);
        }
        return list;
    }
}
