package com.jobda.keychain.service;

import com.jobda.keychain.entity.log.Log;
import com.jobda.keychain.entity.log.MethodType;
import com.jobda.keychain.entity.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    @Transactional
    public void saveLog(HttpServletRequest request, HttpServletResponse response, MethodType methodType) {
        String clientAddress = request.getRemoteAddr();
        int statusCode = response.getStatus();

        Log log = Log.createLog(methodType, clientAddress, statusCode);
        logRepository.save(log);
    }

}
