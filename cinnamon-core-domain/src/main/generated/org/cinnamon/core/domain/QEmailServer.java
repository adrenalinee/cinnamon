package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmailServer is a Querydsl query type for EmailServer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailServer extends EntityPathBase<EmailServer> {

    private static final long serialVersionUID = 1638603249L;

    public static final QEmailServer emailServer = new QEmailServer("emailServer");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final BooleanPath defaultServer = createBoolean("defaultServer");

    public final StringPath description = createString("description");

    public final NumberPath<Long> emailServerId = createNumber("emailServerId", Long.class);

    public final StringPath fromAddress = createString("fromAddress");

    public final StringPath fromName = createString("fromName");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> port = createNumber("port", Integer.class);

    public final StringPath subjectPrefix = createString("subjectPrefix");

    public final StringPath username = createString("username");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QEmailServer(String variable) {
        super(EmailServer.class, forVariable(variable));
    }

    public QEmailServer(Path<? extends EmailServer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailServer(PathMetadata<?> metadata) {
        super(EmailServer.class, metadata);
    }

}

