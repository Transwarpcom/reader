package io.netty.handler.codec.http2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2FrameStreamEvent.class */
public final class Http2FrameStreamEvent {
    private final Http2FrameStream stream;
    private final Type type;

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2FrameStreamEvent$Type.class */
    enum Type {
        State,
        Writability
    }

    private Http2FrameStreamEvent(Http2FrameStream stream, Type type) {
        this.stream = stream;
        this.type = type;
    }

    public Http2FrameStream stream() {
        return this.stream;
    }

    public Type type() {
        return this.type;
    }

    static Http2FrameStreamEvent stateChanged(Http2FrameStream stream) {
        return new Http2FrameStreamEvent(stream, Type.State);
    }

    static Http2FrameStreamEvent writabilityChanged(Http2FrameStream stream) {
        return new Http2FrameStreamEvent(stream, Type.Writability);
    }
}
