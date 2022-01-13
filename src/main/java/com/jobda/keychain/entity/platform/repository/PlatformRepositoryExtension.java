package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;

import java.util.List;

public interface PlatformRepositoryExtension {

    List<SelectUserResponse.SelectUserDto> selectUser(ServiceType platform, List<Long> ids);
    List<Account> selectUserA(ServiceType platform, List<Long> ids);

}
