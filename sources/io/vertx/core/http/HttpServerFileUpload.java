package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServerFileUpload.class */
public interface HttpServerFileUpload extends ReadStream<Buffer> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    HttpServerFileUpload exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Buffer> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Buffer> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    @Fluent
    HttpServerFileUpload streamToFileSystem(String str);

    String filename();

    String name();

    String contentType();

    String contentTransferEncoding();

    String charset();

    long size();

    boolean isSizeAvailable();

    AsyncFile file();

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
}
