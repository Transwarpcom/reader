package io.netty.handler.codec.socksx.v4;

import io.netty.handler.codec.socksx.AbstractSocksMessage;
import io.netty.handler.codec.socksx.SocksVersion;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socksx/v4/AbstractSocks4Message.class */
public abstract class AbstractSocks4Message extends AbstractSocksMessage implements Socks4Message {
    @Override // io.netty.handler.codec.socksx.SocksMessage
    public final SocksVersion version() {
        return SocksVersion.SOCKS4a;
    }
}
