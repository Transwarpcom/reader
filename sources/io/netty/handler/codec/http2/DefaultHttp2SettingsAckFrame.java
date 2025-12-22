package io.netty.handler.codec.http2;

import io.netty.util.internal.StringUtil;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/DefaultHttp2SettingsAckFrame.class */
final class DefaultHttp2SettingsAckFrame implements Http2SettingsAckFrame {
    DefaultHttp2SettingsAckFrame() {
    }

    @Override // io.netty.handler.codec.http2.Http2SettingsAckFrame, io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "SETTINGS(ACK)";
    }

    public String toString() {
        return StringUtil.simpleClassName(this);
    }
}
