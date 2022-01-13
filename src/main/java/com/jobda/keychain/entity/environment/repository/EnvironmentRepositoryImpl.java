package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.dto.response.PlatformEnvironmentsResponse.EnvironmentsDto;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jobda.keychain.entity.environment.QEnvironment.environment;
import static com.jobda.keychain.entity.platform.QPlatform.platform;

@Repository
public class EnvironmentRepositoryImpl extends QuerydslRepositorySupport implements EnvironmentRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public EnvironmentRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Environment.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<EnvironmentsDto> findAllByPlatformEnvironments(ServiceType platformType) {
        return queryFactory.select(
                        Projections.fields(EnvironmentsDto.class,
                                environment.id,
                                environment.name,
                                environment.platform.name.as("platform")
                        )).from(environment)
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
