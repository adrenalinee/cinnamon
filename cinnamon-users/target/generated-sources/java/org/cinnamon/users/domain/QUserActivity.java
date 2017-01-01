package org.cinnamon.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserActivity is a Querydsl query type for UserActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserActivity extends EntityPathBase<UserActivity> {

    private static final long serialVersionUID = -508828565L;

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
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserActivity(PathMetadata metadata, PathInits inits) {
        this(UserActivity.class, metadata, inits);
    }

    public QUserActivity(Class<? extends UserActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activityParam = inits.isInitialized("activityParam") ? new org.cinnamon.core.domain.embeddable.QActivityParam(forProperty("activityParam")) : null;
    }

}

