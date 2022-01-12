package com.jobda.keychain.entity.environment;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.Platform;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "platform_id"}))
public class Environment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private String serverDomain;

    @Column(nullable = false)
    private String clientDomain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @OneToMany(mappedBy = "environment", cascade = CascadeType.REMOVE)
    private List<Account> accounts = new ArrayList<>();

    public static Environment createEnvironment(String name, String serverDomain, String clientDomain, Platform platform) {
        Environment environment = new Environment();
        environment.name = name;
        environment.serverDomain = serverDomain;
        environment.clientDomain = clientDomain;
        environment.platform = platform;
        return environment;
    }

}
