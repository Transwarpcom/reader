package io.vertx.core.datagram;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/DatagramPacket.class */
public interface DatagramPacket {
    SocketAddress sender();

    Buffer data();
}
