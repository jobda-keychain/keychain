package com.jobda.keychain.entity.platform;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlatform is a Querydsl query type for Platform
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlatform extends EntityPathBase<Platform> {

    private static final long serialVersionUID = -842263634L;

    public static final QPlatform platform = new QPlatform("platform");

    public final ListPath<com.jobda.keychain.entity.environment.Environment, com.jobda.keychain.entity.environment.QEnvironment> environments = this.<com.jobda.keychain.entity.environment.Environment, com.jobda.keychain.entity.environment.QEnvironment>createList("environments", com.jobda.keychain.entity.environment.Environment.class, com.jobda.keychain.entity.environment.QEnvironment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<PlatformType> name = createEnum("name", PlatformType.class);

    public QPlatform(String variable) {
        super(Platform.class, forVariable(variable));
    }

    public QPlatform(Path<? extends Platform> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlatform(PathMetadata metadata) {
        super(Platform.class, metadata);
    }

}

