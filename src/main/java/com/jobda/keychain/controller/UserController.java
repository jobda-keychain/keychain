package com.jobda.keychain.controller;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.dto.request.CreateUserRequest;
import com.jobda.keychain.dto.request.UpdateUserRequest;
import com.jobda.keychain.entity.platform.ServiceType;
import com.jobda.keychain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<Account> UserList(@RequestParam(value = "size") int size,
                                  @RequestParam(value = "page") int page,
                                  @RequestParam(value = "platform", required = false) ServiceType platform,
                                  @RequestParam(value = "environment", required = false) List<Long> ids) {

        Pageable pageable = PageRequest.of(page, size);
        return userService.selectUser(pageable,  platform);
    }

    @DeleteMapping("/{idx}")
    public String deleteUser(@PathVariable Integer idx) {
        userService.deleteUser(idx);
        return "success";
    }

}
