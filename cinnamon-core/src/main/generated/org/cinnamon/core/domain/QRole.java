package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -2100243260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRole role = new QRole("role");

    public final StringPath authority = createString("authority");

    public final QUserGroup defaultUserGroup;

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final ListPath<RoleMenu, QRoleMenu> roleMenus = this.<RoleMenu, QRoleMenu>createList("roleMenus", RoleMenu.class, QRoleMenu.class, PathInits.DIRECT2);

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QRole(String variable) {
        this(Role.class, forVariable(variable), INITS);
    }

    public QRole(Path<? extends Role> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRole(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRole(PathMetadata<?> metadata, PathInits inits) {
        this(Role.class, metadata, inits);
    }

    public QRole(Class<? extends Role> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.defaultUserGroup = inits.isInitialized("defaultUserGroup") ? new QUserGroup(forProperty("defaultUserGroup"), inits.get("defaultUserGroup")) : null;
    }

}

