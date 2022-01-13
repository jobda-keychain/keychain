package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {

    Page<SelectUserResponse.SelectUserDto> selectUser(Pageable pageable, ServiceType platform, List<Long> ids);
    public List<SelectUserResponse.SelectUserDto> selectUserE(Pageable pageable, ServiceType serviceType, List<Long> ids);

}
