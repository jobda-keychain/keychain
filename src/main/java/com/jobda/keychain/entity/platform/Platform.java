package com.jobda.keychain.entity.platform;

import com.jobda.keychain.entity.environment.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<Environment> environments;

    public static Platform createPlatform(ServiceType name) {
        Platform platform = new Platform();
        platform.name = name;

        return platform;
    }

}
