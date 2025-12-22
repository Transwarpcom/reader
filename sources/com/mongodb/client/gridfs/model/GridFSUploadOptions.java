package com.mongodb.client.gridfs.model;

import com.mongodb.lang.Nullable;
import org.bson.Document;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/model/GridFSUploadOptions.class */
public final class GridFSUploadOptions {
    private Integer chunkSizeBytes;
    private Document metadata;

    @Nullable
    public Integer getChunkSizeBytes() {
        return this.chunkSizeBytes;
    }

    public GridFSUploadOptions chunkSizeBytes(@Nullable Integer chunkSizeBytes) {
        this.chunkSizeBytes = chunkSizeBytes;
        return this;
    }

    @Nullable
    public Document getMetadata() {
        return this.metadata;
    }

    public GridFSUploadOptions metadata(@Nullable Document metadata) {
        this.metadata = metadata;
        return this;
    }
}
