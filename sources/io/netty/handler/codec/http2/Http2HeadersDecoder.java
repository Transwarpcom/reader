package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2HeadersDecoder.class */
public interface Http2HeadersDecoder {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2HeadersDecoder$Configuration.class */
    public interface Configuration {
        void maxHeaderTableSize(long j) throws Http2Exception;

        long maxHeaderTableSize();

        void maxHeaderListSize(long j, long j2) throws Http2Exception;

        long maxHeaderListSize();

        long maxHeaderListSizeGoAway();
    }

    Http2Headers decodeHeaders(int i, ByteBuf byteBuf) throws Http2Exception;

    Configuration configuration();
}
