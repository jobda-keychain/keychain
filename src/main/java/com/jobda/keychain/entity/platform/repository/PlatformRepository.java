package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {
}
