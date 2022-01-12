package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlatformRepositoryImpl extends QuerydslRepositorySupport implements PlatformRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public PlatformRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Platform.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Platform> selectUser(ServiceType serviceType, List<Long> ids) {
//        queryFactory.select()
        return null;
    }
}
