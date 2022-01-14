package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserDto;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {
    Page<SelectUserDto> selectUser(Pageable pageable, ServiceType platform, List<Long> ids);
}
