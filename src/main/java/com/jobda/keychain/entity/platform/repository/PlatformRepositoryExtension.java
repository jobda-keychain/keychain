package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;

import java.util.List;

public interface PlatformRepositoryExtension {

    List<Platform> selectUser(ServiceType platform, List<Long> ids);

}
