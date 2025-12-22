package io.netty.handler.codec.http.websocketx;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/websocketx/WebSocket07FrameDecoder.class */
public class WebSocket07FrameDecoder extends WebSocket08FrameDecoder {
    public WebSocket07FrameDecoder(boolean expectMaskedFrames, boolean allowExtensions, int maxFramePayloadLength) {
        this(WebSocketDecoderConfig.newBuilder().expectMaskedFrames(expectMaskedFrames).allowExtensions(allowExtensions).maxFramePayloadLength(maxFramePayloadLength).build());
    }

    public WebSocket07FrameDecoder(boolean expectMaskedFrames, boolean allowExtensions, int maxFramePayloadLength, boolean allowMaskMismatch) {
        this(WebSocketDecoderConfig.newBuilder().expectMaskedFrames(expectMaskedFrames).allowExtensions(allowExtensions).maxFramePayloadLength(maxFramePayloadLength).allowMaskMismatch(allowMaskMismatch).build());
    }

    public WebSocket07FrameDecoder(WebSocketDecoderConfig decoderConfig) {
        super(decoderConfig);
    }
}
