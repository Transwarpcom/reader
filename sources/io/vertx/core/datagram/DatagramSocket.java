package io.vertx.core.datagram;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/DatagramSocket.class */
public interface DatagramSocket extends ReadStream<DatagramPacket>, Measured {
    @Fluent
    DatagramSocket send(Buffer buffer, int i, String str, Handler<AsyncResult<DatagramSocket>> handler);

    WriteStream<Buffer> sender(int i, String str);

    @Fluent
    DatagramSocket send(String str, int i, String str2, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket send(String str, String str2, int i, String str3, Handler<AsyncResult<DatagramSocket>> handler);

    void close(Handler<AsyncResult<Void>> handler);

    void close();

    @CacheReturn
    SocketAddress localAddress();

    @Fluent
    DatagramSocket listenMulticastGroup(String str, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket listenMulticastGroup(String str, String str2, String str3, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket unlistenMulticastGroup(String str, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket unlistenMulticastGroup(String str, String str2, String str3, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket blockMulticastGroup(String str, String str2, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket blockMulticastGroup(String str, String str2, String str3, Handler<AsyncResult<DatagramSocket>> handler);

    @Fluent
    DatagramSocket listen(int i, String str, Handler<AsyncResult<DatagramSocket>> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<DatagramPacket> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<DatagramPacket> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<DatagramPacket> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<DatagramPacket> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<DatagramPacket> handler2(Handler<DatagramPacket> handler);

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    DatagramSocket exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<DatagramPacket> endHandler2(Handler handler) {
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
}
