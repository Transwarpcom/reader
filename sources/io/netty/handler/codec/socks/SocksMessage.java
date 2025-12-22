package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksMessage.class */
public abstract class SocksMessage {
    private final SocksMessageType type;
    private final SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;

    @Deprecated
    public abstract void encodeAsByteBuf(ByteBuf byteBuf);

    protected SocksMessage(SocksMessageType type) {
        if (type == null) {
            throw new NullPointerException("type");
        }
        this.type = type;
    }

    public SocksMessageType type() {
        return this.type;
    }

    public SocksProtocolVersion protocolVersion() {
        return this.protocolVersion;
    }
}
