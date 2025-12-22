package org.bson.io;

import java.io.Closeable;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/io/BsonInput.class */
public interface BsonInput extends Closeable {
    int getPosition();

    byte readByte();

    void readBytes(byte[] bArr);

    void readBytes(byte[] bArr, int i, int i2);

    long readInt64();

    double readDouble();

    int readInt32();

    String readString();

    ObjectId readObjectId();

    String readCString();

    void skipCString();

    void skip(int i);

    @Deprecated
    void mark(int i);

    BsonInputMark getMark(int i);

    void reset();

    boolean hasRemaining();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
