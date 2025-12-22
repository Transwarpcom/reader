package io.netty.handler.codec.spdy;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdyPingFrame.class */
public interface SpdyPingFrame extends SpdyFrame {
    int id();

    SpdyPingFrame setId(int i);
}
