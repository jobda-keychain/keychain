package com.jobda.keychain.entity.account;

import com.jobda.keychain.entity.account_environment.AccountEnvironment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private ServiceType service;

    @Column(length = 100, nullable = false)
    private String description;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<AccountEnvironment> accountEnvironments;

    private Account(String userId, String password, ServiceType service, String description) {
        this.userId = userId;
        this.password = password;
        this.service = service;
        this.description = description;
    }

    public static Account createAccount(/*Request 추가 필요*/) {
        return new Account(null, null, null, null);
    }

}
