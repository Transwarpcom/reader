package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksInitRequest.class */
public final class SocksInitRequest extends SocksRequest {
    private final List<SocksAuthScheme> authSchemes;

    public SocksInitRequest(List<SocksAuthScheme> authSchemes) {
        super(SocksRequestType.INIT);
        if (authSchemes == null) {
            throw new NullPointerException("authSchemes");
        }
        this.authSchemes = authSchemes;
    }

    public List<SocksAuthScheme> authSchemes() {
        return Collections.unmodifiableList(this.authSchemes);
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(protocolVersion().byteValue());
        byteBuf.writeByte(this.authSchemes.size());
        for (SocksAuthScheme authScheme : this.authSchemes) {
            byteBuf.writeByte(authScheme.byteValue());
        }
    }
}
