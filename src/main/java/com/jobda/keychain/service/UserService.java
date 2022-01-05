package com.jobda.keychain.service;

import com.jobda.keychain.entity.User;
import com.jobda.keychain.repository.UserRepository;
import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(CreateUserRequest request) {
        User user = User.create(request);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(int userIdx, UpdateUserRequest request) {
        User user = userRepository.findById(userIdx).orElseThrow();

        User.update(user, request);

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