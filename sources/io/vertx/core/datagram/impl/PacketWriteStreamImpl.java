package io.vertx.core.datagram.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/impl/PacketWriteStreamImpl.class */
class PacketWriteStreamImpl implements WriteStream<Buffer>, Handler<AsyncResult<DatagramSocket>> {
    private DatagramSocketImpl datagramSocket;
    private Handler<Throwable> exceptionHandler;
    private final int port;
    private final String host;

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write2(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    PacketWriteStreamImpl(DatagramSocketImpl datagramSocket, int port, String host) {
        this.datagramSocket = datagramSocket;
        this.port = port;
        this.host = host;
    }

    @Override // io.vertx.core.Handler
    public void handle(AsyncResult<DatagramSocket> event) {
        if (event.failed() && this.exceptionHandler != null) {
            this.exceptionHandler.handle(event.cause());
        }
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public PacketWriteStreamImpl exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public PacketWriteStreamImpl write(Buffer data) {
        this.datagramSocket.send(data, this.port, this.host, this);
        return this;
    }

    /* renamed from: write, reason: avoid collision after fix types in other method */
    public WriteStream<Buffer> write2(Buffer data, Handler<AsyncResult<Void>> handler) {
        this.datagramSocket.send(data, this.port, this.host, ar -> {
            handle((AsyncResult<DatagramSocket>) ar);
            if (handler != null) {
                handler.handle(ar.mapEmpty());
            }
        });
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        return false;
    }

    @Override // io.vertx.core.streams.WriteStream
    public WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end() {
        this.datagramSocket.close();
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        this.datagramSocket.close(handler);
    }
}
