package com.jobda.keychain.service;

import com.jobda.keychain.entity.User;
import com.jobda.keychain.repository.UserRepository;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserRequest request) {
        User user = new User(0, request.getId(), request.getPw(), request.getService(), request.getStage(), request.getDes());

        userRepository.save(user);
    }

    public void updateUser(int userIdx, UpdateUserRequest request) {
        User user = userRepository.findById(userIdx).orElseThrow();

        if(StringUtils.hasLength(request.getId())) {
            user.setId(request.getId());
        }

        if(StringUtils.hasLength(request.getPw())) {
            user.setPw(request.getPw());
        }

        if(StringUtils.hasLength(request.getDes())) {
            user.setDes(request.getDes());
        }

        if(request.getService() != null) {
            user.setService(request.getService());
        }

        if(request.getStage() != null) {
            user.setStage(request.getStage());
        }

        userRepository.save(user);
    }

    public void test() {

    }

}
