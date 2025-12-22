package io.netty.util.internal;

import java.nio.ByteBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/Cleaner.class */
interface Cleaner {
    void freeDirectBuffer(ByteBuffer byteBuffer);
}
