package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRoleMenu is a Querydsl query type for RoleMenu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoleMenu extends EntityPathBase<RoleMenu> {

    private static final long serialVersionUID = 1362451523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleMenu roleMenu = new QRoleMenu("roleMenu");

    public final MapPath<String, RoleMenuDetail, QRoleMenuDetail> details = this.<String, RoleMenuDetail, QRoleMenuDetail>createMap("details", String.class, RoleMenuDetail.class, QRoleMenuDetail.class);

    public final QMenu menu;

    public final BooleanPath permitElse = createBoolean("permitElse");

    public final BooleanPath permitRoot = createBoolean("permitRoot");

    public final QRole role;

    public final NumberPath<Long> roleMenuId = createNumber("roleMenuId", Long.class);

    public QRoleMenu(String variable) {
        this(RoleMenu.class, forVariable(variable), INITS);
    }

    public QRoleMenu(Path<? extends RoleMenu> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleMenu(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleMenu(PathMetadata<?> metadata, PathInits inits) {
        this(RoleMenu.class, metadata, inits);
    }

    public QRoleMenu(Class<? extends RoleMenu> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role"), inits.get("role")) : null;
    }

}

