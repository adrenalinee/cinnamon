package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPermissionMenuDetail is a Querydsl query type for PermissionMenuDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermissionMenuDetail extends EntityPathBase<PermissionMenuDetail> {

    private static final long serialVersionUID = 1500854797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermissionMenuDetail permissionMenuDetail = new QPermissionMenuDetail("permissionMenuDetail");

    public final StringPath name = createString("name");

    public final QPermissionMenu permissionMenu;

    public final NumberPath<Long> permissionMenuDetailId = createNumber("permissionMenuDetailId", Long.class);

    public final BooleanPath permit = createBoolean("permit");

    public QPermissionMenuDetail(String variable) {
        this(PermissionMenuDetail.class, forVariable(variable), INITS);
    }

    public QPermissionMenuDetail(Path<? extends PermissionMenuDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPermissionMenuDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPermissionMenuDetail(PathMetadata metadata, PathInits inits) {
        this(PermissionMenuDetail.class, metadata, inits);
    }

    public QPermissionMenuDetail(Class<? extends PermissionMenuDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.permissionMenu = inits.isInitialized("permissionMenu") ? new QPermissionMenu(forProperty("permissionMenu"), inits.get("permissionMenu")) : null;
    }

}

