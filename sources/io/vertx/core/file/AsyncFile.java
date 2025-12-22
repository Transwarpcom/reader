package io.vertx.core.file;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/AsyncFile.class */
public interface AsyncFile extends ReadStream<Buffer>, WriteStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler, reason: merged with bridge method [inline-methods] */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause, reason: merged with bridge method [inline-methods] */
    ReadStream<Buffer> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume, reason: merged with bridge method [inline-methods] */
    ReadStream<Buffer> resume2();

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.WriteStream
    AsyncFile write(Buffer buffer);

    @Fluent
    /* renamed from: write, reason: avoid collision after fix types in other method */
    AsyncFile write2(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize, reason: merged with bridge method [inline-methods] */
    WriteStream<Buffer> setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.streams.WriteStream
    WriteStream<Buffer> drainHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    AsyncFile exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch, reason: merged with bridge method [inline-methods] */
    ReadStream<Buffer> fetch2(long j);

    @Override // io.vertx.core.streams.WriteStream
    void end();

    @Override // io.vertx.core.streams.WriteStream
    void end(Handler<AsyncResult<Void>> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    @Fluent
    AsyncFile write(Buffer buffer, long j, Handler<AsyncResult<Void>> handler);

    @Fluent
    AsyncFile read(Buffer buffer, int i, long j, int i2, Handler<AsyncResult<Buffer>> handler);

    @Fluent
    AsyncFile flush();

    @Fluent
    AsyncFile flush(Handler<AsyncResult<Void>> handler);

    @Fluent
    AsyncFile setReadPos(long j);

    @Fluent
    AsyncFile setReadLength(long j);

    @Fluent
    AsyncFile setWritePos(long j);

    long getWritePos();

    @Fluent
    AsyncFile setReadBufferSize(int i);

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
    @Fluent
    /* bridge */ /* synthetic */ default WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write2(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }
}
