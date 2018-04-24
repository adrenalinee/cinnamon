package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSite is a Querydsl query type for Site
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSite extends EntityPathBase<Site> {

    private static final long serialVersionUID = -2100218987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSite site = new QSite("site");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final QMenuGroup defaultMenuGroup;

    public final StringPath description = createString("description");

    public final StringPath label = createString("label");

    public final SetPath<MenuGroup, QMenuGroup> menuGroup = this.<MenuGroup, QMenuGroup>createSet("menuGroup", MenuGroup.class, QMenuGroup.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath siteId = createString("siteId");

    public final StringPath url = createString("url");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QSite(String variable) {
        this(Site.class, forVariable(variable), INITS);
    }

    public QSite(Path<? extends Site> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSite(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSite(PathMetadata metadata, PathInits inits) {
        this(Site.class, metadata, inits);
    }

    public QSite(Class<? extends Site> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.defaultMenuGroup = inits.isInitialized("defaultMenuGroup") ? new QMenuGroup(forProperty("defaultMenuGroup"), inits.get("defaultMenuGroup")) : null;
    }

}

