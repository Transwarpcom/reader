package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServerRequest.class */
public interface HttpServerRequest extends ReadStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    HttpServerRequest exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Buffer> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Buffer> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    HttpVersion version();

    HttpMethod method();

    String rawMethod();

    boolean isSSL();

    String scheme();

    String uri();

    String path();

    String query();

    String host();

    long bytesRead();

    @CacheReturn
    HttpServerResponse response();

    @CacheReturn
    MultiMap headers();

    String getHeader(String str);

    @GenIgnore({"permitted-type"})
    String getHeader(CharSequence charSequence);

    @CacheReturn
    MultiMap params();

    String getParam(String str);

    @CacheReturn
    SocketAddress remoteAddress();

    @CacheReturn
    SocketAddress localAddress();

    @GenIgnore({"permitted-type"})
    SSLSession sslSession();

    @GenIgnore
    X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

    String absoluteURI();

    @CacheReturn
    NetSocket netSocket();

    @Fluent
    HttpServerRequest setExpectMultipart(boolean z);

    boolean isExpectMultipart();

    @Fluent
    HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> handler);

    @CacheReturn
    MultiMap formAttributes();

    String getFormAttribute(String str);

    ServerWebSocket upgrade();

    boolean isEnded();

    @Fluent
    HttpServerRequest customFrameHandler(Handler<HttpFrame> handler);

    @CacheReturn
    HttpConnection connection();

    @Fluent
    HttpServerRequest streamPriorityHandler(Handler<StreamPriority> handler);

    Cookie getCookie(String str);

    int cookieCount();

    @GenIgnore
    Map<String, Cookie> cookieMap();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Fluent
    default HttpServerRequest bodyHandler(Handler<Buffer> bodyHandler) {
        if (bodyHandler != null) {
            Buffer body = Buffer.buffer();
            body.getClass();
            handler2(body::appendBuffer);
            endHandler(v -> {
                bodyHandler.handle(body);
            });
        }
        return this;
    }

    default StreamPriority streamPriority() {
        return null;
    }
}
