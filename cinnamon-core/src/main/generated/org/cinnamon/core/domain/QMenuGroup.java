package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuGroup is a Querydsl query type for MenuGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuGroup extends EntityPathBase<MenuGroup> {

    private static final long serialVersionUID = 1713348818L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuGroup menuGroup = new QMenuGroup("menuGroup");

    public final StringPath defaultPage = createString("defaultPage");

    public final StringPath description = createString("description");

    public final StringPath dimension = createString("dimension");

    public final StringPath label = createString("label");

    public final NumberPath<Long> menuGroupId = createNumber("menuGroupId", Long.class);

    public final ListPath<Menu, QMenu> menus = this.<Menu, QMenu>createList("menus", Menu.class, QMenu.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QSite site;

    public QMenuGroup(String variable) {
        this(MenuGroup.class, forVariable(variable), INITS);
    }

    public QMenuGroup(Path<? extends MenuGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuGroup(PathMetadata metadata, PathInits inits) {
        this(MenuGroup.class, metadata, inits);
    }

    public QMenuGroup(Class<? extends MenuGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.site = inits.isInitialized("site") ? new QSite(forProperty("site"), inits.get("site")) : null;
    }

}

