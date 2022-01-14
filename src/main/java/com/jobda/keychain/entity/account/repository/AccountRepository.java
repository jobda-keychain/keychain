package com.jobda.keychain.entity.account.repository;

import com.jobda.keychain.entity.account.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
