package com.jobda.keychain.entity.account_environment.repository;

import com.jobda.keychain.entity.account_environment.AccountEnvironment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEnvironmentRepository extends CrudRepository<AccountEnvironment, Long> {
}
