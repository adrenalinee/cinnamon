package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPermissionMenu is a Querydsl query type for PermissionMenu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPermissionMenu extends EntityPathBase<PermissionMenu> {

    private static final long serialVersionUID = 1333107356L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermissionMenu permissionMenu = new QPermissionMenu("permissionMenu");

    public final MapPath<String, PermissionMenuDetail, QPermissionMenuDetail> details = this.<String, PermissionMenuDetail, QPermissionMenuDetail>createMap("details", String.class, PermissionMenuDetail.class, QPermissionMenuDetail.class);

    public final QMenu menu;

    public final QPermission permission;

    public final NumberPath<Long> permissionMenuId = createNumber("permissionMenuId", Long.class);

    public final BooleanPath permitElse = createBoolean("permitElse");

    public final BooleanPath permitRoot = createBoolean("permitRoot");

    public QPermissionMenu(String variable) {
        this(PermissionMenu.class, forVariable(variable), INITS);
    }

    public QPermissionMenu(Path<? extends PermissionMenu> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermissionMenu(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermissionMenu(PathMetadata<?> metadata, PathInits inits) {
        this(PermissionMenu.class, metadata, inits);
    }

    public QPermissionMenu(Class<? extends PermissionMenu> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.permission = inits.isInitialized("permission") ? new QPermission(forProperty("permission"), inits.get("permission")) : null;
    }

}

