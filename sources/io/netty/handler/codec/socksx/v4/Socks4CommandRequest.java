package io.netty.handler.codec.socksx.v4;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v4/Socks4CommandRequest.class */
public interface Socks4CommandRequest extends Socks4Message {
    Socks4CommandType type();

    String userId();

    String dstAddr();

    int dstPort();
}
