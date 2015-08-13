package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QscheduledJob is a Querydsl query type for scheduledJob
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QscheduledJob extends EntityPathBase<scheduledJob> {

    private static final long serialVersionUID = -1805526530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QscheduledJob scheduledJob = new QscheduledJob("scheduledJob");

    public final StringPath name = createString("name");

    public final NumberPath<Long> scheduledJobId = createNumber("scheduledJobId", Long.class);

    public final QGroup shceduleGroup;

    public QscheduledJob(String variable) {
        this(scheduledJob.class, forVariable(variable), INITS);
    }

    public QscheduledJob(Path<? extends scheduledJob> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QscheduledJob(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QscheduledJob(PathMetadata<?> metadata, PathInits inits) {
        this(scheduledJob.class, metadata, inits);
    }

    public QscheduledJob(Class<? extends scheduledJob> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shceduleGroup = inits.isInitialized("shceduleGroup") ? new QGroup(forProperty("shceduleGroup"), inits.get("shceduleGroup")) : null;
    }

}

