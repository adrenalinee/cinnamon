package org.cinnamon.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPermissionMenu is a Querydsl query type for PermissionMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermissionMenu extends EntityPathBase<PermissionMenu> {

    private static final long serialVersionUID = 695538687L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermissionMenu permissionMenu = new QPermissionMenu("permissionMenu");

    public final MapPath<String, PermissionMenuDetail, QPermissionMenuDetail> details = this.<String, PermissionMenuDetail, QPermissionMenuDetail>createMap("details", String.class, PermissionMenuDetail.class, QPermissionMenuDetail.class);

    public final org.cinnamon.sites.domain.QMenu menu;

    public final QPermission permission;

    public final NumberPath<Long> permissionMenuId = createNumber("permissionMenuId", Long.class);

    public final BooleanPath permitElse = createBoolean("permitElse");

    public final BooleanPath permitRoot = createBoolean("permitRoot");

    public QPermissionMenu(String variable) {
        this(PermissionMenu.class, forVariable(variable), INITS);
    }

    public QPermissionMenu(Path<? extends PermissionMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPermissionMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPermissionMenu(PathMetadata metadata, PathInits inits) {
        this(PermissionMenu.class, metadata, inits);
    }

    public QPermissionMenu(Class<? extends PermissionMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new org.cinnamon.sites.domain.QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.permission = inits.isInitialized("permission") ? new QPermission(forProperty("permission"), inits.get("permission")) : null;
    }

}

