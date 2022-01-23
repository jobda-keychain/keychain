package com.jobda.keychain.event;

import com.jobda.keychain.entity.log.MethodType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogEvent {

    private final String clientIpAddress;
    private final MethodType methodType;

}
