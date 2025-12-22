package io.vertx.core.net;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetSocket.class */
public interface NetSocket extends ReadStream<Buffer>, WriteStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    NetSocket exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    NetSocket handler2(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    NetSocket pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    NetSocket resume2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.WriteStream
    NetSocket write(Buffer buffer);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    NetSocket setWriteQueueMaxSize2(int i);

    NetSocket drainHandler(Handler<Void> handler);

    String writeHandlerID();

    @Fluent
    NetSocket write(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    NetSocket write(String str);

    @Fluent
    NetSocket write(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    NetSocket write(String str, String str2);

    @Fluent
    NetSocket write(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Fluent
    NetSocket sendFile(String str, long j, long j2);

    @Fluent
    NetSocket sendFile(String str, long j, long j2, Handler<AsyncResult<Void>> handler);

    @CacheReturn
    SocketAddress remoteAddress();

    @CacheReturn
    SocketAddress localAddress();

    void end();

    void end(Handler<AsyncResult<Void>> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    @Fluent
    /* renamed from: closeHandler */
    NetSocket mo1996closeHandler(Handler<Void> handler);

    @Fluent
    NetSocket upgradeToSsl(Handler<Void> handler);

    @Fluent
    NetSocket upgradeToSsl(String str, Handler<Void> handler);

    boolean isSsl();

    @GenIgnore({"permitted-type"})
    SSLSession sslSession();

    @GenIgnore
    X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

    String indicatedServerName();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.streams.ReadStream
    /* bridge */ /* synthetic */ default ReadStream<Buffer> endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    /* bridge */ /* synthetic */ default ReadStream handler2(Handler handler) {
        return handler2((Handler<Buffer>) handler);
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

    @Fluent
    default NetSocket sendFile(String filename) {
        return sendFile(filename, 0L, Long.MAX_VALUE);
    }

    @Fluent
    default NetSocket sendFile(String filename, long offset) {
        return sendFile(filename, offset, Long.MAX_VALUE);
    }

    @Fluent
    default NetSocket sendFile(String filename, Handler<AsyncResult<Void>> resultHandler) {
        return sendFile(filename, 0L, Long.MAX_VALUE, resultHandler);
    }

    @Fluent
    default NetSocket sendFile(String filename, long offset, Handler<AsyncResult<Void>> resultHandler) {
        return sendFile(filename, offset, Long.MAX_VALUE, resultHandler);
    }
}
