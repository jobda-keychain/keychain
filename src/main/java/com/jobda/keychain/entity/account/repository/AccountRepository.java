package com.jobda.keychain.entity.account.repository;

import com.jobda.keychain.entity.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
