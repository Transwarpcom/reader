package com.mongodb.internal.connection;

import com.mongodb.connection.AsyncCompletionHandler;
import java.nio.ByteBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsyncWritableByteChannel.class */
interface AsyncWritableByteChannel {
    void write(ByteBuffer byteBuffer, AsyncCompletionHandler<Void> asyncCompletionHandler);
}
