package io.netty.handler.ssl;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/OpenSslEngineMap.class */
interface OpenSslEngineMap {
    ReferenceCountedOpenSslEngine remove(long j);

    void add(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine);

    ReferenceCountedOpenSslEngine get(long j);
}
