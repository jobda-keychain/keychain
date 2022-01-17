package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformRepositoryExtension {
    Page<Account> selectUser(Pageable pageable, ServiceType platform, List<Long> ids);
}
