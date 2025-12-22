package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.nio.charset.CharsetEncoder;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksAuthRequest.class */
public final class SocksAuthRequest extends SocksRequest {
    private static final SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;
    private final String username;
    private final String password;

    public SocksAuthRequest(String username, String password) {
        super(SocksRequestType.AUTH);
        if (username == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_USERNAME_PARAM);
        }
        if (password == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_USERNAME_PARAM);
        }
        CharsetEncoder asciiEncoder = CharsetUtil.encoder(CharsetUtil.US_ASCII);
        if (!asciiEncoder.canEncode(username) || !asciiEncoder.canEncode(password)) {
            throw new IllegalArgumentException("username: " + username + " or password: **** values should be in pure ascii");
        }
        if (username.length() > 255) {
            throw new IllegalArgumentException("username: " + username + " exceeds 255 char limit");
        }
        if (password.length() > 255) {
            throw new IllegalArgumentException("password: **** exceeds 255 char limit");
        }
        this.username = username;
        this.password = password;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(SUBNEGOTIATION_VERSION.byteValue());
        byteBuf.writeByte(this.username.length());
        byteBuf.writeCharSequence(this.username, CharsetUtil.US_ASCII);
        byteBuf.writeByte(this.password.length());
        byteBuf.writeCharSequence(this.password, CharsetUtil.US_ASCII);
    }
}
