package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentRepository extends CrudRepository<Environment, Long> {
    List<Environment> findAllBy();
}
