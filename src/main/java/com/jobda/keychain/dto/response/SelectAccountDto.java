package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.PlatformType;
import lombok.Getter;

@Getter
public class SelectAccountDto {

    private Long id;

    private String accountId;

    private PlatformType platform;

    private String environment;

    private String description;

    public static SelectAccountDto of(SelectAccountDto selectAccountDtoData){
        SelectAccountDto selectAccountDto = new SelectAccountDto();
        selectAccountDto.id = selectAccountDtoData.getId();
        selectAccountDto.accountId = selectAccountDtoData.getAccountId();
        selectAccountDto.platform = selectAccountDtoData.getPlatform();
        selectAccountDto.environment = selectAccountDtoData.getEnvironment();
        selectAccountDto.description = selectAccountDtoData.getDescription();
        return selectAccountDto;
    }
}