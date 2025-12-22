package io.netty.handler.codec.spdy;

import io.netty.channel.CombinedChannelDuplexHandler;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdyHttpCodec.class */
public final class SpdyHttpCodec extends CombinedChannelDuplexHandler<SpdyHttpDecoder, SpdyHttpEncoder> {
    public SpdyHttpCodec(SpdyVersion version, int maxContentLength) {
        super(new SpdyHttpDecoder(version, maxContentLength), new SpdyHttpEncoder(version));
    }

    public SpdyHttpCodec(SpdyVersion version, int maxContentLength, boolean validateHttpHeaders) {
        super(new SpdyHttpDecoder(version, maxContentLength, validateHttpHeaders), new SpdyHttpEncoder(version));
    }
}
