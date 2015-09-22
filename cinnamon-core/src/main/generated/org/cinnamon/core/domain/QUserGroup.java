package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserGroup is a Querydsl query type for UserGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserGroup extends EntityPathBase<UserGroup> {

    private static final long serialVersionUID = -715330074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserGroup userGroup = new QUserGroup("userGroup");

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final QRole role;

    public final NumberPath<Long> userGroupId = createNumber("userGroupId", Long.class);

    public final ListPath<UserBase, QUserBase> users = this.<UserBase, QUserBase>createList("users", UserBase.class, QUserBase.class, PathInits.DIRECT2);

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QUserGroup(String variable) {
        this(UserGroup.class, forVariable(variable), INITS);
    }

    public QUserGroup(Path<? extends UserGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserGroup(PathMetadata<?> metadata, PathInits inits) {
        this(UserGroup.class, metadata, inits);
    }

    public QUserGroup(Class<? extends UserGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role"), inits.get("role")) : null;
    }

}
