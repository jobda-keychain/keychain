package com.jobda.keychain.controller;

import com.jobda.keychain.entity.User;
import com.jobda.keychain.repository.UserRepository;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import com.jobda.keychain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PutMapping("/{userIdx}")
    public void updateUser(@RequestBody UpdateUserRequest request, @PathVariable int userIdx) {
        userService.updateUser(userIdx, request);
    }


    @GetMapping("")
    public List<User> UserList() {
        List<User> list = userService.selectUser();
        return list;
    }

    @DeleteMapping("/{idx}")
    public String deleteUser(@PathVariable Integer idx) {
        userService.deleteUser(idx);
        return "success";
    }

}
