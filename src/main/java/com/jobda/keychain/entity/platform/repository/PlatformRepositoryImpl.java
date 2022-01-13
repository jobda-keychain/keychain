package com.jobda.keychain.entity.platform.repository;

import com.jobda.keychain.dto.response.SelectUserResponse;
import com.jobda.keychain.entity.account.Account;
import com.jobda.keychain.entity.environment.Environment;
import com.jobda.keychain.entity.platform.Platform;
import com.jobda.keychain.entity.platform.ServiceType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<SelectUserResponse.SelectUserDto> selectUser(ServiceType serviceType, List<Long> ids) {
//        return queryFactory.select(environment)
//                .from(platform)
//                .join(platform.environments, environment)
//                .on(platform.name.eq(serviceType))
//                .fetch();

        return queryFactory.select(Projections.fields(SelectUserResponse.SelectUserDto.class,
                        account.id, account.userId, account.environment.platform.name, account.environment.name,  account.description))
                .from(platform)
                .leftJoin(platform.environments, environment)
                .on(platform.name.eq(serviceType))
                .leftJoin(environment.accounts, account)
                .fetch();
    }

    @Override
    public List<Account> selectUserA(ServiceType serviceType, List<Long> ids) {
//        return queryFactory.select(environment)
//                .from(platform)
//                .join(platform.environments, environment)
//                .on(platform.name.eq(serviceType))
//                .fetch();

        return queryFactory.select(account)
                .from(environment)
                .innerJoin(environment.accounts, account)
                .fetch();
    }


}
