package org.cinnamon.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileChunk is a Querydsl query type for FileChunk
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileChunk extends EntityPathBase<FileChunk> {

    private static final long serialVersionUID = -1494520960L;

    public static final QFileChunk fileChunk = new QFileChunk("fileChunk");

    public final NumberPath<Integer> chunkNumber = createNumber("chunkNumber", Integer.class);

    public final StringPath identifier = createString("identifier");

    public final StringPath name = createString("name");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath path = createString("path");

    public final NumberPath<Integer> totalChunks = createNumber("totalChunks", Integer.class);

    public final NumberPath<Long> totalSize = createNumber("totalSize", Long.class);

    public QFileChunk(String variable) {
        super(FileChunk.class, forVariable(variable));
    }

    public QFileChunk(Path<? extends FileChunk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileChunk(PathMetadata metadata) {
        super(FileChunk.class, metadata);
    }

}

