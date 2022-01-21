package com.jobda.keychain.event.handler;

import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.log.RequestLog;
import com.jobda.keychain.entity.log.repository.RequestLogRepository;
import com.jobda.keychain.event.LogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogEventHandler {

    private final RequestLogRepository requestLogRepository;

    @Async
    @EventListener
    public void saveRequestLog(LogEvent logEvent) {
        RequestLog requestLog = RequestLog.createLog(logEvent.getMethodType(), logEvent.getClientIpAddress());
        requestLogRepository.save(requestLog);
    }

}
