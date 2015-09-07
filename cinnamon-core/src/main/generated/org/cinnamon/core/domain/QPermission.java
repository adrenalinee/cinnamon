package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPermission is a Querydsl query type for Permission
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPermission extends EntityPathBase<Permission> {

    private static final long serialVersionUID = 1830299549L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermission permission = new QPermission("permission");

    public final StringPath authority = createString("authority");

    public final QUserGroup defaultUserGroup;

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final NumberPath<Long> permissionId = createNumber("permissionId", Long.class);

    public final ListPath<PermissionMenu, QPermissionMenu> permissionMenus = this.<PermissionMenu, QPermissionMenu>createList("permissionMenus", PermissionMenu.class, QPermissionMenu.class, PathInits.DIRECT2);

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QPermission(String variable) {
        this(Permission.class, forVariable(variable), INITS);
    }

    public QPermission(Path<? extends Permission> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermission(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermission(PathMetadata<?> metadata, PathInits inits) {
        this(Permission.class, metadata, inits);
    }

    public QPermission(Class<? extends Permission> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.defaultUserGroup = inits.isInitialized("defaultUserGroup") ? new QUserGroup(forProperty("defaultUserGroup"), inits.get("defaultUserGroup")) : null;
    }

}

