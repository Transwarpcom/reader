package io.vertx.ext.web.codec.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/codec/impl/StreamingBodyCodec.class */
public class StreamingBodyCodec implements BodyCodec<Void> {
    private final WriteStream<Buffer> stream;
    private final boolean close;

    public StreamingBodyCodec(WriteStream<Buffer> stream) {
        this(stream, true);
    }

    public StreamingBodyCodec(WriteStream<Buffer> stream, boolean close) {
        this.stream = stream;
        this.close = close;
    }

    @Override // io.vertx.ext.web.codec.BodyCodec
    public void create(Handler<AsyncResult<BodyStream<Void>>> handler) {
        handler.handle(Future.succeededFuture(new BodyStream<Void>() { // from class: io.vertx.ext.web.codec.impl.StreamingBodyCodec.1
            Promise<Void> promise = Promise.promise();

            @Override // io.vertx.core.streams.WriteStream
            public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler2) {
                return write2(buffer, (Handler<AsyncResult<Void>>) handler2);
            }

            @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
            public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler2) {
                return exceptionHandler((Handler<Throwable>) handler2);
            }

            @Override // io.vertx.ext.web.codec.spi.BodyStream
            public Future<Void> result() {
                return this.promise.future();
            }

            @Override // io.vertx.core.Handler
            public void handle(Throwable cause) {
                this.promise.tryFail(cause);
            }

            @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
            public WriteStream<Buffer> exceptionHandler(Handler<Throwable> handler2) {
                StreamingBodyCodec.this.stream.exceptionHandler(handler2);
                return this;
            }

            /* renamed from: write, reason: avoid collision after fix types in other method */
            public WriteStream<Buffer> write2(Buffer data, Handler<AsyncResult<Void>> handler2) {
                StreamingBodyCodec.this.stream.write(data, handler2);
                return this;
            }

            @Override // io.vertx.core.streams.WriteStream
            public WriteStream<Buffer> write(Buffer data) {
                return write2(data, (Handler<AsyncResult<Void>>) null);
            }

            @Override // io.vertx.core.streams.WriteStream
            public void end() {
                end((Handler<AsyncResult<Void>>) null);
            }

            @Override // io.vertx.core.streams.WriteStream
            public void end(Handler<AsyncResult<Void>> handler2) {
                if (StreamingBodyCodec.this.close) {
                    StreamingBodyCodec.this.stream.end(ar -> {
                        if (ar.succeeded()) {
                            this.promise.tryComplete();
                        } else {
                            this.promise.tryFail(ar.cause());
                        }
                        if (handler2 != null) {
                            handler2.handle(ar);
                        }
                    });
                    return;
                }
                this.promise.tryComplete();
                if (handler2 != null) {
                    handler2.handle(Future.succeededFuture());
                }
            }

            @Override // io.vertx.core.streams.WriteStream
            /* renamed from: setWriteQueueMaxSize */
            public WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
                StreamingBodyCodec.this.stream.setWriteQueueMaxSize2(maxSize);
                return this;
            }

            @Override // io.vertx.core.streams.WriteStream
            public boolean writeQueueFull() {
                return StreamingBodyCodec.this.stream.writeQueueFull();
            }

            @Override // io.vertx.core.streams.WriteStream
            public WriteStream<Buffer> drainHandler(Handler<Void> handler2) {
                StreamingBodyCodec.this.stream.drainHandler(handler2);
                return this;
            }
        }));
    }
}
