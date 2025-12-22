package com.mongodb.client.gridfs.model;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/model/GridFSDownloadOptions.class */
public final class GridFSDownloadOptions {
    private int revision = -1;

    public GridFSDownloadOptions revision(int revision) {
        this.revision = revision;
        return this;
    }

    public int getRevision() {
        return this.revision;
    }
}
