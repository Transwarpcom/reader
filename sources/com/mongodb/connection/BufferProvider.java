package com.mongodb.connection;

import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/BufferProvider.class */
public interface BufferProvider {
    ByteBuf getBuffer(int i);
}
