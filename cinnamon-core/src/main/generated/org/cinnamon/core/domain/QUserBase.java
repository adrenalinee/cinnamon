package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserBase is a Querydsl query type for UserBase
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserBase extends EntityPathBase<UserBase> {

    private static final long serialVersionUID = 1362232970L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBase userBase = new QUserBase("userBase");

    public final BooleanPath accepted = createBoolean("accepted");

    public final DateTimePath<java.util.Date> acceptedAt = createDateTime("acceptedAt", java.util.Date.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath email = createString("email");

    public final EnumPath<org.cinnamon.core.domain.enumeration.EntityType> entityType = createEnum("entityType", org.cinnamon.core.domain.enumeration.EntityType.class);

    public final StringPath job = createString("job");

    public final StringPath language = createString("language");

    public final DateTimePath<java.util.Date> lastLoginAt = createDateTime("lastLoginAt", java.util.Date.class);

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final StringPath nation = createString("nation");

    public final SetPath<Permission, QPermission> permissions = this.<Permission, QPermission>createSet("permissions", Permission.class, QPermission.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    public final StringPath tel = createString("tel");

    public final SetPath<UserGroup, QUserGroup> userGroups = this.<UserGroup, QUserGroup>createSet("userGroups", UserGroup.class, QUserGroup.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public final QUserIntroduce userIntroduce;

    public final QUserPassword userPassword;

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public final BooleanPath validEmail = createBoolean("validEmail");

    public QUserBase(String variable) {
        this(UserBase.class, forVariable(variable), INITS);
    }

    public QUserBase(Path<? extends UserBase> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserBase(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserBase(PathMetadata<?> metadata, PathInits inits) {
        this(UserBase.class, metadata, inits);
    }

    public QUserBase(Class<? extends UserBase> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userIntroduce = inits.isInitialized("userIntroduce") ? new QUserIntroduce(forProperty("userIntroduce")) : null;
        this.userPassword = inits.isInitialized("userPassword") ? new QUserPassword(forProperty("userPassword")) : null;
    }

}

