package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserIntroduce is a Querydsl query type for UserIntroduce
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserIntroduce extends EntityPathBase<UserIntroduce> {

    private static final long serialVersionUID = 1817015558L;

    public static final QUserIntroduce userIntroduce = new QUserIntroduce("userIntroduce");

    public final StringPath introduce = createString("introduce");

    public final StringPath userId = createString("userId");

    public QUserIntroduce(String variable) {
        super(UserIntroduce.class, forVariable(variable));
    }

    public QUserIntroduce(Path<? extends UserIntroduce> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserIntroduce(PathMetadata<?> metadata) {
        super(UserIntroduce.class, metadata);
    }

}

