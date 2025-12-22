package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/UnknownSocksRequest.class */
public final class UnknownSocksRequest extends SocksRequest {
    public UnknownSocksRequest() {
        super(SocksRequestType.UNKNOWN);
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
    }
}
