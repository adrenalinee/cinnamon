package org.cinnamon.core.domain.embeddable;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActivityParam is a Querydsl query type for ActivityParam
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QActivityParam extends BeanPath<ActivityParam> {

    private static final long serialVersionUID = 2076522019L;

    public static final QActivityParam activityParam = new QActivityParam("activityParam");

    public final SimplePath<java.io.Serializable> id = createSimple("id", java.io.Serializable.class);

    public final StringPath uri = createString("uri");

    public QActivityParam(String variable) {
        super(ActivityParam.class, forVariable(variable));
    }

    public QActivityParam(Path<? extends ActivityParam> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityParam(PathMetadata<?> metadata) {
        super(ActivityParam.class, metadata);
    }

}

