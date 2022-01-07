package com.jobda.keychain.entity.account;

import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    protected Account(String userId, String password, ServiceType service, String description) {
        this.userId = userId;
        this.password = password;
        this.service = service;
        this.description = description;
    }

    public static Account createAccount(/*Request 추가 필요*/) {
        return new Account(null, null, null, null);
    }

}
