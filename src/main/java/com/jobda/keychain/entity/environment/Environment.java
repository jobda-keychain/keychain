package com.jobda.keychain.entity.environment;

import com.jobda.keychain.entity.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Environment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @OneToMany(mappedBy = "environment", cascade = CascadeType.REMOVE)
    private List<Account> accounts;

    protected Environment(String name) {
        this.name = name;
    }

    public Environment createEnvironment(/*Request 추가*/) {
        return new Environment(null);
    }

    //환경 이름 수정 메소드 추가

}
