package com.jobda.keychain.entity.platform;

import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Platform {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private ServiceType name;

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private List<Environment> environments = new ArrayList<>();

    public static Platform createPlatform(ServiceType name) {
        Platform platform = new Platform();
        platform.name = name;

        return platform;
    }

}
