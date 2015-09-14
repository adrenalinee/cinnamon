package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -2100401747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final ListPath<Menu, QMenu> childs = this.<Menu, QMenu>createList("childs", Menu.class, QMenu.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final StringPath iconClass = createString("iconClass");

    public final QMenuGroup menuGroup;

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> orders = createNumber("orders", Integer.class);

    public final QMenu parent;

    public final EnumPath<org.cinnamon.core.domain.enumeration.MenuPosition> position = createEnum("position", org.cinnamon.core.domain.enumeration.MenuPosition.class);

    public final StringPath toolip = createString("toolip");

    public final EnumPath<org.cinnamon.core.domain.enumeration.MenuType> type = createEnum("type", org.cinnamon.core.domain.enumeration.MenuType.class);

    public final StringPath uri = createString("uri");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenu(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenu(PathMetadata<?> metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menuGroup = inits.isInitialized("menuGroup") ? new QMenuGroup(forProperty("menuGroup"), inits.get("menuGroup")) : null;
        this.parent = inits.isInitialized("parent") ? new QMenu(forProperty("parent"), inits.get("parent")) : null;
    }

}

