package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProperty is a Querydsl query type for Property
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProperty extends EntityPathBase<Property> {

    private static final long serialVersionUID = 636115747L;

    public static final QProperty property = new QProperty("property");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public final StringPath value = createString("value");

    public QProperty(String variable) {
        super(Property.class, forVariable(variable));
    }

    public QProperty(Path<? extends Property> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProperty(PathMetadata metadata) {
        super(Property.class, metadata);
    }

}

