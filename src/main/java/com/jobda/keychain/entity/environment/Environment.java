package com.jobda.keychain.entity.environment;

import com.jobda.keychain.entity.account_environment.AccountEnvironment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Environment {

    @Id
    @Column(length = 10)
    private String name;

    @OneToMany(mappedBy = "environment", cascade = CascadeType.REMOVE)
    private List<AccountEnvironment> accountEnvironments;

    private Environment(String name) {
        this.name = name;
    }

    public Environment createEnvironment(/*Request 추가*/) {
        return new Environment(null);
    }

    //환경 이름 수정 메소드 추가

}
