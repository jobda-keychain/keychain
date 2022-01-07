package com.jobda.keychain.entity.account_environment;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccountEnvironment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    private AccountEnvironment(Account account, Environment environment) {
        this.account = account;
        this.environment = environment;
    }

    public static AccountEnvironment createAccountEnvironment(/*request*/) {
        return new AccountEnvironment(null, null);
    }

}
