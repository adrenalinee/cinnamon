package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserActivity is a Querydsl query type for UserActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserActivity extends EntityPathBase<UserActivity> {

    private static final long serialVersionUID = -932745656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserActivity userActivity = new QUserActivity("userActivity");

    public final org.cinnamon.core.domain.embeddable.QActivityParam activityParam;

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath type = createString("type");

    public final NumberPath<Long> userActivityId = createNumber("userActivityId", Long.class);

    public final StringPath userId = createString("userId");

    public QUserActivity(String variable) {
        this(UserActivity.class, forVariable(variable), INITS);
    }

    public QUserActivity(Path<? extends UserActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserActivity(PathMetadata<?> metadata, PathInits inits) {
        this(UserActivity.class, metadata, inits);
    }

    public QUserActivity(Class<? extends UserActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activityParam = inits.isInitialized("activityParam") ? new org.cinnamon.core.domain.embeddable.QActivityParam(forProperty("activityParam")) : null;
    }

}

