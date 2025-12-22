package com.mongodb.client.gridfs;

import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.client.gridfs.model.GridFSFile;
import java.io.InputStream;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/gridfs/GridFSDownloadStream.class */
public abstract class GridFSDownloadStream extends InputStream {
    public abstract GridFSFile getGridFSFile();

    public abstract GridFSDownloadStream batchSize(int i);

    @Override // java.io.InputStream
    public abstract int read();

    @Override // java.io.InputStream
    public abstract int read(byte[] bArr);

    @Override // java.io.InputStream
    public abstract int read(byte[] bArr, int i, int i2);

    @Override // java.io.InputStream
    public abstract long skip(long j);

    @Override // java.io.InputStream
    public abstract int available();

    public abstract void mark();

    @Override // java.io.InputStream
    public abstract void reset();

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();
}
