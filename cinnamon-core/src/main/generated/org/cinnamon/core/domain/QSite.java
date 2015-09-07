package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSite is a Querydsl query type for Site
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSite extends EntityPathBase<Site> {

    private static final long serialVersionUID = -2100218987L;

    public static final QSite site = new QSite("site");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath indexPage = createString("indexPage");

    public final SetPath<MenuGroup, QMenuGroup> menuGroup = this.<MenuGroup, QMenuGroup>createSet("menuGroup", MenuGroup.class, QMenuGroup.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath siteId = createString("siteId");

    public final StringPath url = createString("url");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QSite(String variable) {
        super(Site.class, forVariable(variable));
    }

    public QSite(Path<? extends Site> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSite(PathMetadata<?> metadata) {
        super(Site.class, metadata);
    }

}

