package com.jobda.keychain.entity;

import com.jobda.keychain.request.CreateUserRequest;
import com.jobda.keychain.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "service")
    private int service;

    @Column(name = "stage")
    private int stage;

    @Column(name = "des")
    private String des;

    public static User create(CreateUserRequest request) {
        return new User(0, request.getId(), request.getPw(), request.getService(), request.getStage(), request.getDes());
    }

    public static void update(User user, UpdateUserRequest request) {

        if (StringUtils.hasLength(request.getId())) {
            user.id = request.getId();
        }

        if (StringUtils.hasLength(request.getPw())) {
            user.pw = request.getPw();
        }

        if (StringUtils.hasLength(request.getDes())) {
            user.des = request.getDes();
        }

        if (request.getService() != null) {
            user.service = request.getService();
        }

        if (request.getStage() != null) {
            user.stage = request.getStage();
        }
    }
}
