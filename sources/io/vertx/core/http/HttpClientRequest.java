package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpClientRequest.class */
public interface HttpClientRequest extends WriteStream<Buffer>, ReadStream<HttpClientResponse> {
    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    HttpClientRequest exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.WriteStream
    HttpClientRequest write(Buffer buffer);

    @Fluent
    /* renamed from: write, reason: avoid collision after fix types in other method */
    HttpClientRequest write2(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    WriteStream<Buffer> setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.streams.WriteStream
    WriteStream<Buffer> drainHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.ReadStream
    @Fluent
    @Deprecated
    /* renamed from: handler */
    ReadStream<HttpClientResponse> handler2(Handler<HttpClientResponse> handler);

    @Override // io.vertx.core.streams.ReadStream
    @Deprecated
    /* renamed from: pause */
    ReadStream<HttpClientResponse> pause2();

    @Override // io.vertx.core.streams.ReadStream
    @Deprecated
    /* renamed from: resume */
    ReadStream<HttpClientResponse> resume2();

    @Override // io.vertx.core.streams.ReadStream
    @Deprecated
    /* renamed from: fetch */
    ReadStream<HttpClientResponse> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    @Deprecated
    ReadStream<HttpClientResponse> endHandler(Handler<Void> handler);

    @Fluent
    HttpClientRequest setFollowRedirects(boolean z);

    @Fluent
    HttpClientRequest setMaxRedirects(int i);

    @Fluent
    HttpClientRequest setChunked(boolean z);

    boolean isChunked();

    HttpMethod method();

    String getRawMethod();

    @Fluent
    HttpClientRequest setRawMethod(String str);

    String absoluteURI();

    String uri();

    String path();

    String query();

    @Fluent
    HttpClientRequest setHost(String str);

    String getHost();

    @CacheReturn
    MultiMap headers();

    @Fluent
    HttpClientRequest putHeader(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpClientRequest putHeader(CharSequence charSequence, CharSequence charSequence2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpClientRequest putHeader(String str, Iterable<String> iterable);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpClientRequest putHeader(CharSequence charSequence, Iterable<CharSequence> iterable);

    @Fluent
    HttpClientRequest write(String str);

    @Fluent
    HttpClientRequest write(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    HttpClientRequest write(String str, String str2);

    @Fluent
    HttpClientRequest write(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    HttpClientRequest continueHandler(Handler<Void> handler);

    @Fluent
    HttpClientRequest sendHead();

    @Fluent
    HttpClientRequest sendHead(Handler<HttpVersion> handler);

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

    @Override // io.vertx.core.streams.WriteStream
    void end(Handler<AsyncResult<Void>> handler);

    @Fluent
    HttpClientRequest setTimeout(long j);

    @Fluent
    HttpClientRequest pushHandler(Handler<HttpClientRequest> handler);

    boolean reset(long j);

    @CacheReturn
    HttpConnection connection();

    @Fluent
    @Deprecated
    HttpClientRequest connectionHandler(Handler<HttpConnection> handler);

    @Fluent
    HttpClientRequest writeCustomFrame(int i, int i2, Buffer buffer);

    StreamPriority getStreamPriority();

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

    @Override // io.vertx.core.streams.ReadStream
    @Deprecated
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<HttpClientResponse> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    default boolean reset() {
        return reset(0L);
    }

    default int streamId() {
        return -1;
    }

    @Fluent
    default HttpClientRequest writeCustomFrame(HttpFrame frame) {
        return writeCustomFrame(frame.type(), frame.flags(), frame.payload());
    }

    @Fluent
    default HttpClientRequest setStreamPriority(StreamPriority streamPriority) {
        return this;
    }
}
