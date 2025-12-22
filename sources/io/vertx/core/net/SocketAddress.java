package io.vertx.core.net;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.net.impl.SocketAddressImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/SocketAddress.class */
public interface SocketAddress {
    String host();

    int port();

    String path();

    static SocketAddress inetSocketAddress(int port, String host) {
        return new SocketAddressImpl(port, host);
    }

    static SocketAddress domainSocketAddress(String path) {
        return new SocketAddressImpl(path);
    }
}
