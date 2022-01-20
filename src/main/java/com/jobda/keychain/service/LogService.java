package com.jobda.keychain.service;

import com.jobda.keychain.entity.log.RequestLog;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.log.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        boolean b = ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
        if(b) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(b) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(b) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(b) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(b) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
