package com.jobda.keychain.entity.account;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
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

    @Column(length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    public static Account createAccount(String userId, String password, ServiceType service, String description) {
        Account account = new Account();
        account.userId = userId;
        account.password = password;
        account.description = description;

        return account;
    }

}
