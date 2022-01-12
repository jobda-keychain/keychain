package com.jobda.keychain.entity.account;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class KeychainRepositorySupport extends QuerydslRepositorySupport {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public KeychainRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

}
