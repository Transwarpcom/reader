package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebSocketBase.class */
public interface WebSocketBase extends ReadStream<Buffer>, WriteStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    WebSocketBase exceptionHandler(Handler<Throwable> handler);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Buffer> pause2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Buffer> resume2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.WriteStream
    WebSocketBase write(Buffer buffer);

    @Fluent
    WebSocketBase write(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    WebSocketBase setWriteQueueMaxSize2(int i);

    WebSocketBase drainHandler(Handler<Void> handler);

    String binaryHandlerID();

    String textHandlerID();

    String subProtocol();

    Short closeStatusCode();

    String closeReason();

    MultiMap headers();

    @Fluent
    WebSocketBase writeFrame(WebSocketFrame webSocketFrame);

    @Fluent
    WebSocketBase writeFrame(WebSocketFrame webSocketFrame, Handler<AsyncResult<Void>> handler);

    @Fluent
    WebSocketBase writeFinalTextFrame(String str);

    @Fluent
    WebSocketBase writeFinalTextFrame(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    WebSocketBase writeFinalBinaryFrame(Buffer buffer);

    @Fluent
    WebSocketBase writeFinalBinaryFrame(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Fluent
    WebSocketBase writeBinaryMessage(Buffer buffer);

    @Fluent
    WebSocketBase writeBinaryMessage(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Fluent
    WebSocketBase writeTextMessage(String str);

    @Fluent
    WebSocketBase writeTextMessage(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    WebSocketBase writePing(Buffer buffer);

    @Fluent
    WebSocketBase writePong(Buffer buffer);

    @Fluent
    WebSocketBase closeHandler(Handler<Void> handler);

    @Fluent
    WebSocketBase frameHandler(Handler<WebSocketFrame> handler);

    @Fluent
    WebSocketBase textMessageHandler(Handler<String> handler);

    @Fluent
    WebSocketBase binaryMessageHandler(Handler<Buffer> handler);

    @Fluent
    WebSocketBase pongHandler(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.WriteStream
    void end();

    @Override // io.vertx.core.streams.WriteStream
    void end(Handler<AsyncResult<Void>> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    void close(short s);

    void close(short s, Handler<AsyncResult<Void>> handler);

    void close(short s, String str);

    void close(short s, String str, Handler<AsyncResult<Void>> handler);

    @CacheReturn
    SocketAddress remoteAddress();

    @CacheReturn
    SocketAddress localAddress();

    boolean isSsl();

    boolean isClosed();

    @GenIgnore({"permitted-type"})
    SSLSession sslSession();

    @GenIgnore
    X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* bridge */ /* synthetic */ default ReadStream<Buffer> endHandler(Handler handler) {
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

    /* bridge */ /* synthetic */ default WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Fluent
    /* bridge */ /* synthetic */ default WriteStream write(Object obj, Handler handler) {
        return write((Buffer) obj, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }
}
