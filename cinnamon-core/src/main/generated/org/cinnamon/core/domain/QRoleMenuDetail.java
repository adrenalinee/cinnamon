package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRoleMenuDetail is a Querydsl query type for RoleMenuDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoleMenuDetail extends EntityPathBase<RoleMenuDetail> {

    private static final long serialVersionUID = -456072588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleMenuDetail roleMenuDetail = new QRoleMenuDetail("roleMenuDetail");

    public final StringPath name = createString("name");

    public final BooleanPath permit = createBoolean("permit");

    public final QRoleMenu roleMenu;

    public final NumberPath<Long> roleMenuDetailId = createNumber("roleMenuDetailId", Long.class);

    public QRoleMenuDetail(String variable) {
        this(RoleMenuDetail.class, forVariable(variable), INITS);
    }

    public QRoleMenuDetail(Path<? extends RoleMenuDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleMenuDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleMenuDetail(PathMetadata<?> metadata, PathInits inits) {
        this(RoleMenuDetail.class, metadata, inits);
    }

    public QRoleMenuDetail(Class<? extends RoleMenuDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.roleMenu = inits.isInitialized("roleMenu") ? new QRoleMenu(forProperty("roleMenu"), inits.get("roleMenu")) : null;
    }

}

