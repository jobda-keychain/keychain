package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.jobda.keychain.entity.environment.QEnvironment.environment;
import static com.jobda.keychain.entity.platform.QPlatform.platform;

public class EnvironmentRepositoryImpl extends QuerydslRepositorySupport implements EnvironmentRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public EnvironmentRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Environment.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Environment> findAllByPlatformEnvironments(ServiceType platformType) {
        return queryFactory.select(environment).from(environment)
                .join(environment.platform, platform)
                .on(platform.eq(environment.platform))
                .where(platformTypeEq(platformType))
                .fetch();
    }

    private BooleanExpression platformTypeEq(ServiceType platformType) {
        if (platformType == null) {
            return null;
        }
        return platform.name.eq(platformType);
    }

}
