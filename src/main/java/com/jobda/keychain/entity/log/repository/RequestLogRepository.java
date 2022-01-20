package com.jobda.keychain.entity.log.repository;

import com.jobda.keychain.entity.log.RequestLog;
import org.springframework.data.repository.CrudRepository;

public interface RequestLogRepository extends CrudRepository<RequestLog, Long> {
}
