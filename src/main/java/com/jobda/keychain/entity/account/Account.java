package com.jobda.keychain.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private Service service;

    @Column(length = 100, nullable = false)
    private String description;

    private Account(String userId, String password, Service service, String description) {
        this.userId = userId;
        this.password = password;
        this.service = service;
        this.description = description;
    }

    public static Account create(/*Request 추가 필요*/) {
        return new Account(null, null, null, null);
    }

}
