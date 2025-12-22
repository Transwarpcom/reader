package io.netty.handler.codec.http2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2SettingsReceivedConsumer.class */
public interface Http2SettingsReceivedConsumer {
    void consumeReceivedSettings(Http2Settings http2Settings);
}
