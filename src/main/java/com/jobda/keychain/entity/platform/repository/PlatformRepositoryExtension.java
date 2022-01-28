package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectAccountDto;
import com.jobda.keychain.entity.platform.PlatformType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {
    Page<SelectAccountDto> selectAccount(Pageable pageable, PlatformType platform, List<Long> environmentIds);
}
