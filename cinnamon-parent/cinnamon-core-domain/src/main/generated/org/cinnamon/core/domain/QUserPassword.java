package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserPassword is a Querydsl query type for UserPassword
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserPassword extends EntityPathBase<UserPassword> {

    private static final long serialVersionUID = 1940207060L;

    public static final QUserPassword userPassword = new QUserPassword("userPassword");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath password = createString("password");

    public final StringPath userId = createString("userId");

    public QUserPassword(String variable) {
        super(UserPassword.class, forVariable(variable));
    }

    public QUserPassword(Path<? extends UserPassword> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPassword(PathMetadata<?> metadata) {
        super(UserPassword.class, metadata);
    }

}

