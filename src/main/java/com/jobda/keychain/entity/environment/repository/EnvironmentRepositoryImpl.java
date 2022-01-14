package com.jobda.keychain.entity.environment.repository;

import com.jobda.keychain.dto.response.EnvironmentsResponse;
import com.jobda.keychain.entity.environment.Environment;
import com.querydsl.core.types.Projections;
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

@Repository
public class EnvironmentRepositoryImpl extends QuerydslRepositorySupport implements EnvironmentRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public EnvironmentRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Environment.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<EnvironmentsResponse.EnvironmentDto> findAllByPlatformEnvironment(Pageable page) {
        JPQLQuery<EnvironmentsResponse.EnvironmentDto> query = querydsl().applyPagination(page,
                queryFactory.select(
                        Projections.fields(EnvironmentsResponse.EnvironmentDto.class,
                        environment.id,
                        environment.name,
                        environment.serverDomain,
                        environment.clientDomain,
                        environment.platform.name.as("platform")
                )).from(environment)
                .join(environment.platform, platform)
                .on(platform.name.eq(environment.platform.name)));
        List<EnvironmentsResponse.EnvironmentDto> list = query.fetch();

        return new PageImpl<>(list, page, query.fetchCount());
    }

    private Querydsl querydsl() {
        return Objects.requireNonNull(getQuerydsl());
    }

}
