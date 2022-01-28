package com.jobda.keychain.entity.log;

import com.jobda.keychain.entity.BaseCreatedAtEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RequestLog extends BaseCreatedAtEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 18, nullable = false)
    private MethodType methodType;

    @Column(nullable = false)
    private String clientIpAddress;

    public static RequestLog createLog(MethodType methodType, String clientIpAddress) {
        RequestLog requestLog = new RequestLog();
        requestLog.methodType = methodType;
        requestLog.clientIpAddress = clientIpAddress;

        return requestLog;
    }

}
