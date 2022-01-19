package com.jobda.keychain.entity.environment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnvironment is a Querydsl query type for Environment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnvironment extends EntityPathBase<Environment> {

    private static final long serialVersionUID = -741856616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnvironment environment = new QEnvironment("environment");

    public final ListPath<com.jobda.keychain.entity.account.Account, com.jobda.keychain.entity.account.QAccount> accounts = this.<com.jobda.keychain.entity.account.Account, com.jobda.keychain.entity.account.QAccount>createList("accounts", com.jobda.keychain.entity.account.Account.class, com.jobda.keychain.entity.account.QAccount.class, PathInits.DIRECT2);

    public final StringPath clientDomain = createString("clientDomain");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.jobda.keychain.entity.platform.QPlatform platform;

    public final StringPath serverDomain = createString("serverDomain");

    public QEnvironment(String variable) {
        this(Environment.class, forVariable(variable), INITS);
    }

    public QEnvironment(Path<? extends Environment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnvironment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnvironment(PathMetadata metadata, PathInits inits) {
        this(Environment.class, metadata, inits);
    }

    public QEnvironment(Class<? extends Environment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.platform = inits.isInitialized("platform") ? new com.jobda.keychain.entity.platform.QPlatform(forProperty("platform")) : null;
    }

}

