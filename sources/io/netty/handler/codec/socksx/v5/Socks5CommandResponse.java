package io.netty.handler.codec.socksx.v5;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v5/Socks5CommandResponse.class */
public interface Socks5CommandResponse extends Socks5Message {
    Socks5CommandStatus status();

    Socks5AddressType bndAddrType();

    String bndAddr();

    int bndPort();
}
