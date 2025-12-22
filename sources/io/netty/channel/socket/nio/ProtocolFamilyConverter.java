package io.netty.channel.socket.nio;

import io.netty.channel.socket.InternetProtocolFamily;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/socket/nio/ProtocolFamilyConverter.class */
final class ProtocolFamilyConverter {
    private ProtocolFamilyConverter() {
    }

    public static ProtocolFamily convert(InternetProtocolFamily family) {
        switch (family) {
            case IPv4:
                return StandardProtocolFamily.INET;
            case IPv6:
                return StandardProtocolFamily.INET6;
            default:
                throw new IllegalArgumentException();
        }
    }
}
