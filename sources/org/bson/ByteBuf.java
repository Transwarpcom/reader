package org.bson;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/ByteBuf.class */
public interface ByteBuf {
    int capacity();

    ByteBuf put(int i, byte b);

    int remaining();

    ByteBuf put(byte[] bArr, int i, int i2);

    boolean hasRemaining();

    ByteBuf put(byte b);

    ByteBuf flip();

    byte[] array();

    int limit();

    ByteBuf position(int i);

    ByteBuf clear();

    ByteBuf order(ByteOrder byteOrder);

    byte get();

    byte get(int i);

    ByteBuf get(byte[] bArr);

    ByteBuf get(int i, byte[] bArr);

    ByteBuf get(byte[] bArr, int i, int i2);

    ByteBuf get(int i, byte[] bArr, int i2, int i3);

    long getLong();

    long getLong(int i);

    double getDouble();

    double getDouble(int i);

    int getInt();

    int getInt(int i);

    int position();

    ByteBuf limit(int i);

    ByteBuf asReadOnly();

    ByteBuf duplicate();

    ByteBuffer asNIO();

    int getReferenceCount();

    ByteBuf retain();

    void release();
}
