package io.vertx.core.parsetools;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.impl.RecordParserImpl;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/RecordParser.class */
public interface RecordParser extends Handler<Buffer>, ReadStream<Buffer> {
    void setOutput(Handler<Buffer> handler);

    void delimitedMode(String str);

    void delimitedMode(Buffer buffer);

    void fixedSizeMode(int i);

    @Fluent
    RecordParser maxRecordSize(int i);

    @Override // io.vertx.core.Handler
    void handle(Buffer buffer);

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    RecordParser exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Buffer> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Buffer> resume2();

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

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

    static RecordParser newDelimited(String delim, Handler<Buffer> output) {
        return RecordParserImpl.newDelimited(delim, (ReadStream<Buffer>) null, output);
    }

    static RecordParser newDelimited(String delim, ReadStream<Buffer> stream) {
        return RecordParserImpl.newDelimited(delim, stream, (Handler<Buffer>) null);
    }

    static RecordParser newDelimited(String delim) {
        return RecordParserImpl.newDelimited(delim, (ReadStream<Buffer>) null, (Handler<Buffer>) null);
    }

    static RecordParser newDelimited(Buffer delim) {
        return RecordParserImpl.newDelimited(delim, (ReadStream<Buffer>) null, (Handler<Buffer>) null);
    }

    static RecordParser newDelimited(Buffer delim, Handler<Buffer> output) {
        return RecordParserImpl.newDelimited(delim, (ReadStream<Buffer>) null, output);
    }

    static RecordParser newDelimited(Buffer delim, ReadStream<Buffer> stream) {
        return RecordParserImpl.newDelimited(delim, stream, (Handler<Buffer>) null);
    }

    static RecordParser newFixed(int size) {
        return RecordParserImpl.newFixed(size, null, null);
    }

    static RecordParser newFixed(int size, Handler<Buffer> output) {
        return RecordParserImpl.newFixed(size, null, output);
    }

    static RecordParser newFixed(int size, ReadStream<Buffer> stream) {
        return RecordParserImpl.newFixed(size, stream, null);
    }
}
