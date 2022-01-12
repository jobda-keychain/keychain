package com.jobda.keychain.entity.account.repository;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAllByEnvironment(Environment environment);
}
