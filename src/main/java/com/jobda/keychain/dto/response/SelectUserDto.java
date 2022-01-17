package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.ServiceType;
import lombok.Getter;

@Getter
public class SelectUserDto{

    private Long id;

    private String userId;

    private ServiceType platform;

    private String environment;

    private String description;

    public static SelectUserDto of(Account account){
        SelectUserDto selectUserDto = new SelectUserDto();
        selectUserDto.id = account.getId();
        selectUserDto.userId = account.getUserId();
        selectUserDto.platform = account.getEnvironment().getPlatform().getName();
        selectUserDto.environment = account.getEnvironment().getName();
        selectUserDto.description = account.getDescription();
        return selectUserDto;
    }
}