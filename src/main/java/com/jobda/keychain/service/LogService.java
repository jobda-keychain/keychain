package com.jobda.keychain.service;

import com.jobda.keychain.entity.log.RequestLog;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.log.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogService {

    private final RequestLogRepository requestLogRepository;

    @Transactional
    public void saveRequestLog(String clientIpAddress, MethodType methodType) {
        String clientAddress = clientIpAddress;

        RequestLog requestLog = RequestLog.createLog(methodType, clientAddress);
        requestLogRepository.save(requestLog);
    }

}
