package com.mongodb.client.gridfs.model;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/model/GridFSDownloadByNameOptions.class */
public final class GridFSDownloadByNameOptions {
    private int revision = -1;

    public GridFSDownloadByNameOptions revision(int revision) {
        this.revision = revision;
        return this;
    }

    public int getRevision() {
        return this.revision;
    }
}
