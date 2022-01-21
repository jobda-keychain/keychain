package com.jobda.keychain.entity.account;

import com.jobda.keychain.entity.BaseLastModifiedAtEntity;
import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "environment_id"}))
public class Account extends BaseLastModifiedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    public static Account createAccount(String userId, String password, Environment environment, String description) {
        Account account = new Account();
        account.userId = userId;
        account.password = password;
        account.environment = environment;
        account.description = description;
        return account;
    }

    public void changeInfo(String userId, String password, String description) {
        this.userId = userId;
        this.password = password;
        this.description = description;
    }

}
