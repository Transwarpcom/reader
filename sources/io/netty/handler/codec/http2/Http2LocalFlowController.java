package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2LocalFlowController.class */
public interface Http2LocalFlowController extends Http2FlowController {
    Http2LocalFlowController frameWriter(Http2FrameWriter http2FrameWriter);

    void receiveFlowControlledFrame(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z) throws Http2Exception;

    boolean consumeBytes(Http2Stream http2Stream, int i) throws Http2Exception;

    int unconsumedBytes(Http2Stream http2Stream);

    int initialWindowSize(Http2Stream http2Stream);
}
