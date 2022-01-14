package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.ServiceType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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
  
    @Override
    public Page<Environment> findAllByPlatformEnvironment(Pageable page) {
        JPQLQuery<Environment> query = querydsl().applyPagination(page,
                queryFactory.select(
                        environment
                )).from(environment)
                .join(environment.platform, platform)
                .on(platform.name.eq(environment.platform.name));
        List<Environment> list = query.fetch();

        return new PageImpl<>(list, page, query.fetchCount());
    }

    private Querydsl querydsl() {
        return Objects.requireNonNull(getQuerydsl());
    }
  
    private BooleanExpression platformTypeEq(ServiceType platformType) {
        if (platformType == null) {
            return null;
        }
        return platform.name.eq(platformType);
    }
  
}
