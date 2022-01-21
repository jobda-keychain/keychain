package com.jobda.keychain.service;

import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.event.LogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogService {

    private final ApplicationEventPublisher publisher;

    @Transactional
    public void saveRequestLog(String clientIpAddress, MethodType methodType) {
        publisher.publishEvent(new LogEvent(clientIpAddress, methodType));
    }

}
