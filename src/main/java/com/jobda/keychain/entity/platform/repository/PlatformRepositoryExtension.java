package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserDto;
import com.jobda.keychain.entity.platform.PlatformType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {
    Page<SelectUserDto> selectUser(Pageable pageable, PlatformType platform, List<Long> ids);
}
