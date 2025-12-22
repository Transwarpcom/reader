package io.vertx.ext.web.codec;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import io.vertx.ext.web.codec.impl.StreamingBodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/codec/BodyCodec.class */
public interface BodyCodec<T> {
    @GenIgnore
    void create(Handler<AsyncResult<BodyStream<T>>> handler);

    static BodyCodec<String> string() {
        return BodyCodecImpl.STRING;
    }

    static BodyCodec<String> string(String encoding) {
        return BodyCodecImpl.string(encoding);
    }

    static BodyCodec<Buffer> buffer() {
        return BodyCodecImpl.BUFFER;
    }

    static BodyCodec<JsonObject> jsonObject() {
        return BodyCodecImpl.JSON_OBJECT;
    }

    static BodyCodec<JsonArray> jsonArray() {
        return BodyCodecImpl.JSON_ARRAY;
    }

    static <U> BodyCodec<U> json(Class<U> type) {
        return BodyCodecImpl.json(type);
    }

    static BodyCodec<Void> none() {
        return BodyCodecImpl.NONE;
    }

    static <T> BodyCodec<T> create(Function<Buffer, T> decode) {
        return new BodyCodecImpl(decode);
    }

    static BodyCodec<Void> pipe(WriteStream<Buffer> stream) {
        return pipe(stream, true);
    }

    static BodyCodec<Void> pipe(WriteStream<Buffer> stream, boolean close) {
        return new StreamingBodyCodec(stream, close);
    }
}
