package io.netty.handler.codec.socksx.v4;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v4/Socks4CommandResponse.class */
public interface Socks4CommandResponse extends Socks4Message {
    Socks4CommandStatus status();

    String dstAddr();

    int dstPort();
}
