package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.PlatformType;
import lombok.AllArgsConstructor;
import com.jobda.keychain.entity.account.Account;
import lombok.Getter;

@Getter
public class SelectUserDto{

    private Long id;

    private String userId;

    private PlatformType platform;

    private String environment;

    private String description;

    public static SelectUserDto of(SelectUserDto selectUserDtoData){
        SelectUserDto selectUserDto = new SelectUserDto();
        selectUserDto.id = selectUserDtoData.getId();
        selectUserDto.userId = selectUserDtoData.getUserId();
        selectUserDto.platform = selectUserDtoData.getPlatform();
        selectUserDto.environment = selectUserDtoData.getEnvironment();
        selectUserDto.description = selectUserDtoData.getDescription();
        return selectUserDto;
    }
}