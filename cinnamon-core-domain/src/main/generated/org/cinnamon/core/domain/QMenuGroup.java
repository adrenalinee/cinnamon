package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMenuGroup is a Querydsl query type for MenuGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMenuGroup extends EntityPathBase<MenuGroup> {

    private static final long serialVersionUID = 1713348818L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuGroup menuGroup = new QMenuGroup("menuGroup");

    public final StringPath description = createString("description");

    public final StringPath dimension = createString("dimension");

    public final NumberPath<Long> menuGroupId = createNumber("menuGroupId", Long.class);

    public final ListPath<Menu, QMenu> menus = this.<Menu, QMenu>createList("menus", Menu.class, QMenu.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QSite site;

    public QMenuGroup(String variable) {
        this(MenuGroup.class, forVariable(variable), INITS);
    }

    public QMenuGroup(Path<? extends MenuGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenuGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenuGroup(PathMetadata<?> metadata, PathInits inits) {
        this(MenuGroup.class, metadata, inits);
    }

    public QMenuGroup(Class<? extends MenuGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.site = inits.isInitialized("site") ? new QSite(forProperty("site")) : null;
    }

}

