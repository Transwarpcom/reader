package com.mongodb.client.gridfs;

import com.mongodb.annotations.NotThreadSafe;
import java.io.OutputStream;
import org.bson.BsonValue;
import org.bson.types.ObjectId;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/gridfs/GridFSUploadStream.class */
public abstract class GridFSUploadStream extends OutputStream {
    @Deprecated
    public abstract ObjectId getFileId();

    public abstract ObjectId getObjectId();

    public abstract BsonValue getId();

    public abstract void abort();

    @Override // java.io.OutputStream
    public abstract void write(int i);

    @Override // java.io.OutputStream
    public abstract void write(byte[] bArr);

    @Override // java.io.OutputStream
    public abstract void write(byte[] bArr, int i, int i2);

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }
}
