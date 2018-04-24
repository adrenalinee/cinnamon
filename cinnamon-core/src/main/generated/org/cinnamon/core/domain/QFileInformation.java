package org.cinnamon.core.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileInformation is a Querydsl query type for FileInformation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileInformation extends EntityPathBase<FileInformation> {

    private static final long serialVersionUID = -211177214L;

    public static final QFileInformation fileInformation = new QFileInformation("fileInformation");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath ext = createString("ext");

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final StringPath md5 = createString("md5");

    public final StringPath md5FileName = createString("md5FileName");

    public final StringPath name = createString("name");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final StringPath type = createString("type");

    public final EnumPath<org.cinnamon.core.domain.enumeration.UseStatus> useStatus = createEnum("useStatus", org.cinnamon.core.domain.enumeration.UseStatus.class);

    public QFileInformation(String variable) {
        super(FileInformation.class, forVariable(variable));
    }

    public QFileInformation(Path<? extends FileInformation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileInformation(PathMetadata metadata) {
        super(FileInformation.class, metadata);
    }

}

