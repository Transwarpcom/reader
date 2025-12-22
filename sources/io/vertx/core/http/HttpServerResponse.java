package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServerResponse.class */
public interface HttpServerResponse extends WriteStream<Buffer> {
    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    HttpServerResponse exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.WriteStream
    HttpServerResponse write(Buffer buffer);

    @Fluent
    /* renamed from: write, reason: avoid collision after fix types in other method */
    HttpServerResponse write2(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    WriteStream<Buffer> setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.streams.WriteStream
    WriteStream<Buffer> drainHandler(Handler<Void> handler);

    int getStatusCode();

    @Fluent
    HttpServerResponse setStatusCode(int i);

    String getStatusMessage();

    @Fluent
    HttpServerResponse setStatusMessage(String str);

    @Fluent
    HttpServerResponse setChunked(boolean z);

    boolean isChunked();

    @CacheReturn
    MultiMap headers();

    @Fluent
    HttpServerResponse putHeader(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putHeader(CharSequence charSequence, CharSequence charSequence2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putHeader(String str, Iterable<String> iterable);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putHeader(CharSequence charSequence, Iterable<CharSequence> iterable);

    @CacheReturn
    MultiMap trailers();

    @Fluent
    HttpServerResponse putTrailer(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putTrailer(CharSequence charSequence, CharSequence charSequence2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putTrailer(String str, Iterable<String> iterable);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpServerResponse putTrailer(CharSequence charSequence, Iterable<CharSequence> iterable);

    @Fluent
    HttpServerResponse closeHandler(Handler<Void> handler);

    @Fluent
    HttpServerResponse endHandler(Handler<Void> handler);

    @Fluent
    HttpServerResponse write(String str, String str2);

    @Fluent
    HttpServerResponse write(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    HttpServerResponse write(String str);

    @Fluent
    HttpServerResponse write(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    HttpServerResponse writeContinue();

    void end(String str);

    void end(String str, Handler<AsyncResult<Void>> handler);

    void end(String str, String str2);

    void end(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    void end(Buffer buffer);

    /* renamed from: end, reason: avoid collision after fix types in other method */
    void end2(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    void end();

    @Fluent
    HttpServerResponse sendFile(String str, long j, long j2);

    @Fluent
    HttpServerResponse sendFile(String str, long j, long j2, Handler<AsyncResult<Void>> handler);

    void close();

    boolean ended();

    boolean closed();

    boolean headWritten();

    @Fluent
    HttpServerResponse headersEndHandler(Handler<Void> handler);

    @Fluent
    HttpServerResponse bodyEndHandler(Handler<Void> handler);

    long bytesWritten();

    int streamId();

    @Fluent
    HttpServerResponse push(HttpMethod httpMethod, String str, String str2, Handler<AsyncResult<HttpServerResponse>> handler);

    @Fluent
    HttpServerResponse push(HttpMethod httpMethod, String str, MultiMap multiMap, Handler<AsyncResult<HttpServerResponse>> handler);

    @Fluent
    HttpServerResponse push(HttpMethod httpMethod, String str, Handler<AsyncResult<HttpServerResponse>> handler);

    @Fluent
    HttpServerResponse push(HttpMethod httpMethod, String str, String str2, MultiMap multiMap, Handler<AsyncResult<HttpServerResponse>> handler);

    void reset(long j);

    @Fluent
    HttpServerResponse writeCustomFrame(int i, int i2, Buffer buffer);

    @Fluent
    HttpServerResponse addCookie(Cookie cookie);

    Cookie removeCookie(String str, boolean z);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default void end(Buffer buffer, Handler handler) {
        end2(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    @Fluent
    /* bridge */ /* synthetic */ default WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write2(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Fluent
    default HttpServerResponse sendFile(String filename) {
        return sendFile(filename, 0L);
    }

    @Fluent
    default HttpServerResponse sendFile(String filename, long offset) {
        return sendFile(filename, offset, Long.MAX_VALUE);
    }

    @Fluent
    default HttpServerResponse sendFile(String filename, Handler<AsyncResult<Void>> resultHandler) {
        return sendFile(filename, 0L, resultHandler);
    }

    @Fluent
    default HttpServerResponse sendFile(String filename, long offset, Handler<AsyncResult<Void>> resultHandler) {
        return sendFile(filename, offset, Long.MAX_VALUE, resultHandler);
    }

    default void reset() {
        reset(0L);
    }

    @Fluent
    default HttpServerResponse writeCustomFrame(HttpFrame frame) {
        return writeCustomFrame(frame.type(), frame.flags(), frame.payload());
    }

    @Fluent
    default HttpServerResponse setStreamPriority(StreamPriority streamPriority) {
        return this;
    }

    default Cookie removeCookie(String name) {
        return removeCookie(name, true);
    }
}
