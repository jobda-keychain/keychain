package com.jobda.keychain.entity.environment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Environment {

    @Id
    @Column(length = 10)
    private String name;

    private Environment(String name) {
        this.name = name;
    }
    
    public Environment create(/*Request 추가*/) {
        return new Environment(null);
    }

    //환경 이름 수정 메소드 추가

}
