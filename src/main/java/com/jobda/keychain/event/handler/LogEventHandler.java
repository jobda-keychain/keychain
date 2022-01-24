package com.jobda.keychain.event.handler;

import com.jobda.keychain.event.LogEvent;
import com.jobda.keychain.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class LogEventHandler {

    private final LogService logService;

    @Transactional
    @TransactionalEventListener
    public void saveRequestLog(LogEvent logEvent) {
        logService.saveRequestLog(logEvent.getMethodType(), logEvent.getClientIpAddress());
    }

}
