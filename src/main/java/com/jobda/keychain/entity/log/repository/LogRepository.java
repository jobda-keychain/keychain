package com.jobda.keychain.entity.log.repository;

import com.jobda.keychain.entity.log.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}
