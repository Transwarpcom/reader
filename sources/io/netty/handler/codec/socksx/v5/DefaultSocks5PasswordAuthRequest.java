package io.netty.handler.codec.socksx.v5;

import io.netty.handler.codec.DecoderResult;
import io.netty.util.internal.StringUtil;
import io.vertx.ext.web.handler.FormLoginHandler;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v5/DefaultSocks5PasswordAuthRequest.class */
public class DefaultSocks5PasswordAuthRequest extends AbstractSocks5Message implements Socks5PasswordAuthRequest {
    private final String username;
    private final String password;

    public DefaultSocks5PasswordAuthRequest(String username, String password) {
        if (username == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_USERNAME_PARAM);
        }
        if (password == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_PASSWORD_PARAM);
        }
        if (username.length() > 255) {
            throw new IllegalArgumentException("username: **** (expected: less than 256 chars)");
        }
        if (password.length() > 255) {
            throw new IllegalArgumentException("password: **** (expected: less than 256 chars)");
        }
        this.username = username;
        this.password = password;
    }

    @Override // io.netty.handler.codec.socksx.v5.Socks5PasswordAuthRequest
    public String username() {
        return this.username;
    }

    @Override // io.netty.handler.codec.socksx.v5.Socks5PasswordAuthRequest
    public String password() {
        return this.password;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(StringUtil.simpleClassName(this));
        DecoderResult decoderResult = decoderResult();
        if (!decoderResult.isSuccess()) {
            buf.append("(decoderResult: ");
            buf.append(decoderResult);
            buf.append(", username: ");
        } else {
            buf.append("(username: ");
        }
        buf.append(username());
        buf.append(", password: ****)");
        return buf.toString();
    }
}
