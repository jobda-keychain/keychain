package com.jobda.keychain.entity.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class RequestLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 18, nullable = false)
    private MethodType methodType;

    @Column(nullable = false)
    private String requestUri;

    @Column(nullable = false)
    private String clientIpAddress;

    public static RequestLog createLog(MethodType methodType, String requestUri, String clientIpAddress) {
        RequestLog requestLog = new RequestLog();
        requestLog.methodType = methodType;
        requestLog.requestUri = requestUri;
        requestLog.clientIpAddress = clientIpAddress;

        return requestLog;
    }

}
