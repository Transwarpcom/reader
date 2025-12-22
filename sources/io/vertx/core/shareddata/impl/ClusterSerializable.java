package io.vertx.core.shareddata.impl;

import io.vertx.core.buffer.Buffer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/ClusterSerializable.class */
public interface ClusterSerializable {
    void writeToBuffer(Buffer buffer);

    int readFromBuffer(int i, Buffer buffer);
}
