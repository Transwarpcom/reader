package io.netty.handler.codec.http2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2SettingsFrame.class */
public interface Http2SettingsFrame extends Http2Frame {
    Http2Settings settings();

    @Override // io.netty.handler.codec.http2.Http2Frame
    String name();
}
