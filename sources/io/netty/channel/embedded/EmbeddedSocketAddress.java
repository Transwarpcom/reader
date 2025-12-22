package io.netty.channel.embedded;

import java.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/embedded/EmbeddedSocketAddress.class */
final class EmbeddedSocketAddress extends SocketAddress {
    private static final long serialVersionUID = 1400788804624980619L;

    EmbeddedSocketAddress() {
    }

    public String toString() {
        return "embedded";
    }
}
