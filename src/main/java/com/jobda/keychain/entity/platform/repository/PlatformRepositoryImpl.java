package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
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
import static com.jobda.keychain.entity.account.QAccount.account;
@Repository
public class PlatformRepositoryImpl extends QuerydslRepositorySupport implements PlatformRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public PlatformRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Platform.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<SelectUserResponse.SelectUserDto> selectUser(Pageable pageable, ServiceType serviceType, List<Long> ids) {
        JPQLQuery<SelectUserResponse.SelectUserDto> query = querydsl().applyPagination(pageable,
                queryFactory.select(
                                Projections.fields(SelectUserResponse.SelectUserDto.class,
                                        account.id,
                                        account.userId,
                                        account.environment.platform.name.as("platform"),
                                        account.environment.name.as("environment"),
                                        account.description)
                        )
                        .from(platform)
                        .leftJoin(platform.environments, environment)
                        .on(platform.name.eq(serviceType))
                        .leftJoin(environment.accounts, account));
        List<SelectUserResponse.SelectUserDto> list = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(list, pageable, totalCount);

    }
    private Querydsl querydsl() {
        return Objects.requireNonNull(getQuerydsl());
    }

}
