package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksAuthResponse.class */
public final class SocksAuthResponse extends SocksResponse {
    private static final SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;
    private final SocksAuthStatus authStatus;

    public SocksAuthResponse(SocksAuthStatus authStatus) {
        super(SocksResponseType.AUTH);
        if (authStatus == null) {
            throw new NullPointerException("authStatus");
        }
        this.authStatus = authStatus;
    }

    public SocksAuthStatus authStatus() {
        return this.authStatus;
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(SUBNEGOTIATION_VERSION.byteValue());
        byteBuf.writeByte(this.authStatus.byteValue());
    }
}
