package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity<? extends UserBase>> {

    private static final long serialVersionUID = 1123882978L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final QUserBase creator;

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(String variable) {
        this((Class) BaseEntity.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(Path<? extends BaseEntity> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(PathMetadata metadata, PathInits inits) {
        this((Class) BaseEntity.class, metadata, inits);
    }

    public QBaseEntity(Class<? extends BaseEntity<? extends UserBase>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creator = inits.isInitialized("creator") ? new QUserBase(forProperty("creator"), inits.get("creator")) : null;
    }

}

