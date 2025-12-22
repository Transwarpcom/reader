package io.netty.handler.codec.socksx.v5;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v5/Socks5PasswordAuthRequest.class */
public interface Socks5PasswordAuthRequest extends Socks5Message {
    String username();

    String password();
}
