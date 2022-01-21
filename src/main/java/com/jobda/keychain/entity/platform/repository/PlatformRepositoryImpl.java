package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserDto;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.PlatformType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

import static com.jobda.keychain.entity.account.QAccount.account;
import static com.jobda.keychain.entity.environment.QEnvironment.environment;
import static com.jobda.keychain.entity.platform.QPlatform.platform;

public class PlatformRepositoryImpl extends QuerydslRepositorySupport implements PlatformRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public PlatformRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Platform.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<SelectUserDto> selectUser(Pageable pageable, PlatformType serviceType, List<Long> environmentIds) {
        JPAQuery<SelectUserDto> query =
                queryFactory.select(Projections.fields(
                                        SelectUserDto.class,
                                        account.id,
                                        account.userId,
                                        account.environment.platform.name.as("platform"),
                                        account.environment.name.as("environment"),
                                        account.description
                                )
                        )
                        .from(platform)
                        .join(platform.environments, environment)
                        .join(environment.accounts, account)
                        .where(serviceTypeEq(serviceType))
                        .where(environmentIdsIn(environmentIds))
                        .orderBy(account.createdAt.desc());

        JPQLQuery<SelectUserDto> selectUserDtoJPQLQuery =
                querydsl().applyPagination(pageable, query);

        List<SelectUserDto> list = selectUserDtoJPQLQuery.fetch();
        return new PageImpl<>(list, pageable, selectUserDtoJPQLQuery.fetchCount());
    }

    private Querydsl querydsl() {
        return Objects.requireNonNull(getQuerydsl());
    }

    private BooleanExpression serviceTypeEq(PlatformType serviceType) {
        return serviceType != null ? platform.name.eq(serviceType) : null;
    }

    private BooleanExpression environmentIdsIn(List<Long> environmentIds) {
        if(environmentIds == null || environmentIds.size() <= 0) return null;
        return environment.id.in(environmentIds);
    }
}
