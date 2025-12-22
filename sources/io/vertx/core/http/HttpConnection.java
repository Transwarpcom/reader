package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpConnection.class */
public interface HttpConnection {
    @Fluent
    HttpConnection goAway(long j, int i, Buffer buffer);

    @Fluent
    HttpConnection goAwayHandler(Handler<GoAway> handler);

    @Fluent
    HttpConnection shutdownHandler(Handler<Void> handler);

    @Fluent
    HttpConnection shutdown();

    @Fluent
    HttpConnection shutdown(long j);

    @Fluent
    HttpConnection closeHandler(Handler<Void> handler);

    void close();

    Http2Settings settings();

    @Fluent
    HttpConnection updateSettings(Http2Settings http2Settings);

    @Fluent
    HttpConnection updateSettings(Http2Settings http2Settings, Handler<AsyncResult<Void>> handler);

    Http2Settings remoteSettings();

    @Fluent
    HttpConnection remoteSettingsHandler(Handler<Http2Settings> handler);

    @Fluent
    HttpConnection ping(Buffer buffer, Handler<AsyncResult<Buffer>> handler);

    @Fluent
    HttpConnection pingHandler(Handler<Buffer> handler);

    @Fluent
    HttpConnection exceptionHandler(Handler<Throwable> handler);

    @CacheReturn
    SocketAddress remoteAddress();

    @CacheReturn
    SocketAddress localAddress();

    boolean isSsl();

    @GenIgnore({"permitted-type"})
    SSLSession sslSession();

    @GenIgnore
    X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

    String indicatedServerName();

    default int getWindowSize() {
        return -1;
    }

    @Fluent
    default HttpConnection setWindowSize(int windowSize) {
        return this;
    }

    @Fluent
    default HttpConnection goAway(long errorCode) {
        return goAway(errorCode, -1);
    }

    @Fluent
    default HttpConnection goAway(long errorCode, int lastStreamId) {
        return goAway(errorCode, lastStreamId, null);
    }
}
