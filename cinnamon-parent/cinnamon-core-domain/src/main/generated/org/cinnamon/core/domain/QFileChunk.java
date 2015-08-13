package org.cinnamon.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFileChunk is a Querydsl query type for FileChunk
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFileChunk extends EntityPathBase<FileChunk> {

    private static final long serialVersionUID = 1919841987L;

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

    public QFileChunk(PathMetadata<?> metadata) {
        super(FileChunk.class, metadata);
    }

}

