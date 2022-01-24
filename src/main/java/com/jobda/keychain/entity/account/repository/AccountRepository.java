package com.jobda.keychain.entity.account.repository;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByAccountIdAndEnvironment(String accountId, Environment environment);
}
