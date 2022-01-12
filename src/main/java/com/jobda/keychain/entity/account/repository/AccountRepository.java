package com.jobda.keychain.entity.account.repository;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.Platform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    // 리스트 전체 조회
    Page<Account> findAll(Pageable pageable);
    // 리스트 platform 조회
    Optional<List<Account>> findByEnvironment(Environment environments);

}
