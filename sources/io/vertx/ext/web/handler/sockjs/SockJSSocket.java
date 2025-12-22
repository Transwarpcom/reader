package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Session;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/SockJSSocket.class */
public interface SockJSSocket extends ReadStream<Buffer>, WriteStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    SockJSSocket exceptionHandler(Handler<Throwable> handler);

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
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    /* renamed from: write, reason: avoid collision after fix types in other method */
    SockJSSocket write2(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    WriteStream<Buffer> setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.streams.WriteStream
    WriteStream<Buffer> drainHandler(Handler<Void> handler);

    String writeHandlerID();

    @Override // io.vertx.core.streams.WriteStream
    void end();

    void close();

    SocketAddress remoteAddress();

    SocketAddress localAddress();

    MultiMap headers();

    String uri();

    Session webSession();

    User webUser();

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

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write2(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    default SockJSSocket write(Buffer data) {
        return write2(data, (Handler<AsyncResult<Void>>) null);
    }

    @Fluent
    default SockJSSocket write(String data) {
        return write(data, (Handler<AsyncResult<Void>>) null);
    }

    default SockJSSocket write(String data, Handler<AsyncResult<Void>> handler) {
        return write2(Buffer.buffer(data), handler);
    }

    default void close(int statusCode, String reason) {
        close();
    }
}
