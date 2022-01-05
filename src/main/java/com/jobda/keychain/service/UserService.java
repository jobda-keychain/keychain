package com.jobda.keychain.service;

import com.jobda.keychain.entity.User;
import com.jobda.keychain.repository.UserRepository;
import com.jobda.keychain.request.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void test() {

    }

    public List<User> selectUser(){
        return userRepository.findAll();
    }
    public void deleteUser(Integer idx){
        userRepository.deleteById(idx);
    }
}