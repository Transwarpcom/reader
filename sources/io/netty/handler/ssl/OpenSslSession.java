package io.netty.handler.ssl;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/OpenSslSession.class */
interface OpenSslSession extends SSLSession {
    void handshakeFinished() throws SSLException;

    void tryExpandApplicationBufferSize(int i);
}
