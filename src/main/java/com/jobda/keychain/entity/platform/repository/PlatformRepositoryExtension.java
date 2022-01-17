package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.PlatformType;
import com.jobda.keychain.entity.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {
    Page<Account> selectUser(Pageable pageable, PlatformType platform, List<Long> ids);
}
