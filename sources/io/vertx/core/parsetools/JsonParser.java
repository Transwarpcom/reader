package io.vertx.core.parsetools;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.impl.JsonParserImpl;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/JsonParser.class */
public interface JsonParser extends Handler<Buffer>, ReadStream<JsonEvent> {
    @Fluent
    JsonParser write(Buffer buffer);

    void end();

    @Fluent
    JsonParser objectEventMode();

    @Fluent
    JsonParser objectValueMode();

    @Fluent
    JsonParser arrayEventMode();

    @Fluent
    JsonParser arrayValueMode();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<JsonEvent> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<JsonEvent> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<JsonEvent> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    @Fluent
    ReadStream<JsonEvent> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.ReadStream
    @Fluent
    /* renamed from: handler */
    ReadStream<JsonEvent> handler2(Handler<JsonEvent> handler);

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    @Fluent
    JsonParser exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    @Fluent
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<JsonEvent> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    @Fluent
    /* bridge */ /* synthetic */ default ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    @Fluent
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    static JsonParser newParser() {
        return new JsonParserImpl(null);
    }

    static JsonParser newParser(ReadStream<Buffer> stream) {
        return new JsonParserImpl(stream);
    }
}
