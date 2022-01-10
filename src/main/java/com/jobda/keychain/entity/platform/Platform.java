package com.jobda.keychain.entity.platform;

import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Platform {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9, nullable = false)
    private String name;

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private List<Environment> environments;

    protected Platform(String name) {
        this.name = name;
    }

    public static Platform createPlatform(String name) {
        Platform platform = new Platform();
        platform.name = name;

        return platform;
    }

}
