package io.netty.handler.codec;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/DecoderResultProvider.class */
public interface DecoderResultProvider {
    DecoderResult decoderResult();

    void setDecoderResult(DecoderResult decoderResult);
}
