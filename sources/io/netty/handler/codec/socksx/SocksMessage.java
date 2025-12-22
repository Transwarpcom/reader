package io.netty.handler.codec.socksx;

import io.netty.handler.codec.DecoderResultProvider;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/SocksMessage.class */
public interface SocksMessage extends DecoderResultProvider {
    SocksVersion version();
}
