package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
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
    public Page<Account> selectUser(Pageable pageable, ServiceType serviceType, List<Long> ids) {
        JPAQuery<Account> query =
                queryFactory.select(account)
                        .from(platform)
                        .leftJoin(platform.environments, environment)
                        .leftJoin(environment.accounts, account)
                        .where(serviceTypeEq(serviceType))
                        .where(idsIn(ids)
                        );

        JPQLQuery<Account> selectUserDtoJPQLQuery =
                querydsl().applyPagination(pageable, query);

        List<Account> list = selectUserDtoJPQLQuery.fetch();
        return new PageImpl<>(list, pageable, list.size());
    }

    private Querydsl querydsl() {
        return Objects.requireNonNull(getQuerydsl());
    }

    private BooleanExpression serviceTypeEq(ServiceType serviceType) {
        return serviceType != null ? platform.name.eq(serviceType) : null;
    }

    private BooleanExpression idsIn(List<Long> ids) {
        return ids != null ? environment.id.in(ids) : null;
    }

}
