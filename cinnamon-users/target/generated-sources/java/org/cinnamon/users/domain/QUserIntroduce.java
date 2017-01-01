package org.cinnamon.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserIntroduce is a Querydsl query type for UserIntroduce
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserIntroduce extends EntityPathBase<UserIntroduce> {

    private static final long serialVersionUID = 2073543491L;

    public static final QUserIntroduce userIntroduce = new QUserIntroduce("userIntroduce");

    public final StringPath introduce = createString("introduce");

    public final StringPath userId = createString("userId");

    public QUserIntroduce(String variable) {
        super(UserIntroduce.class, forVariable(variable));
    }

    public QUserIntroduce(Path<? extends UserIntroduce> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserIntroduce(PathMetadata metadata) {
        super(UserIntroduce.class, metadata);
    }

}

