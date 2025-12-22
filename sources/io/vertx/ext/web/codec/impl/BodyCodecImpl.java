package io.vertx.ext.web.codec.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.json.JsonCodec;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/codec/impl/BodyCodecImpl.class */
public class BodyCodecImpl<T> implements BodyCodec<T> {
    public static final Function<Buffer, Void> VOID_DECODER = buff -> {
        return null;
    };
    public static final Function<Buffer, String> UTF8_DECODER = (v0) -> {
        return v0.toString();
    };
    public static final Function<Buffer, JsonObject> JSON_OBJECT_DECODER = buff -> {
        Object val = JsonCodec.INSTANCE.fromBuffer(buff, Object.class);
        if (val == null) {
            return null;
        }
        if (val instanceof JsonObject) {
            return (JsonObject) val;
        }
        throw new DecodeException("Invalid Json Object decoded as " + val.getClass().getName());
    };
    public static final Function<Buffer, JsonArray> JSON_ARRAY_DECODER = buff -> {
        Object val = JsonCodec.INSTANCE.fromBuffer(buff, Object.class);
        if (val == null) {
            return null;
        }
        if (val instanceof JsonArray) {
            return (JsonArray) val;
        }
        throw new DecodeException("Invalid Json Object decoded as " + val.getClass().getName());
    };
    public static final BodyCodec<String> STRING = new BodyCodecImpl(UTF8_DECODER);
    public static final BodyCodec<Void> NONE = new BodyCodecImpl(VOID_DECODER);
    public static final BodyCodec<Buffer> BUFFER = new BodyCodecImpl(Function.identity());
    public static final BodyCodec<JsonObject> JSON_OBJECT = new BodyCodecImpl(JSON_OBJECT_DECODER);
    public static final BodyCodec<JsonArray> JSON_ARRAY = new BodyCodecImpl(JSON_ARRAY_DECODER);
    private final Function<Buffer, T> decoder;

    public static BodyCodecImpl<String> string(String encoding) {
        return new BodyCodecImpl<>(buff -> {
            return buff.toString(encoding);
        });
    }

    public static <T> BodyCodec<T> json(Class<T> type) {
        return new BodyCodecImpl(jsonDecoder(type));
    }

    public static <T> Function<Buffer, T> jsonDecoder(Class<T> type) {
        return buff -> {
            return JsonCodec.INSTANCE.fromBuffer(buff, type);
        };
    }

    public BodyCodecImpl(Function<Buffer, T> decoder) {
        this.decoder = decoder;
    }

    @Override // io.vertx.ext.web.codec.BodyCodec
    public void create(Handler<AsyncResult<BodyStream<T>>> handler) {
        handler.handle(Future.succeededFuture(new BodyStream<T>() { // from class: io.vertx.ext.web.codec.impl.BodyCodecImpl.1
            Buffer buffer = Buffer.buffer();
            Promise<T> state = Promise.promise();

            @Override // io.vertx.core.streams.WriteStream
            public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler2) {
                return write2(buffer, (Handler<AsyncResult<Void>>) handler2);
            }

            @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
            public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler2) {
                return exceptionHandler((Handler<Throwable>) handler2);
            }

            @Override // io.vertx.core.Handler
            public void handle(Throwable cause) {
                this.state.tryFail(cause);
            }

            @Override // io.vertx.ext.web.codec.spi.BodyStream
            public Future<T> result() {
                return this.state.future();
            }

            @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
            public WriteStream<Buffer> exceptionHandler(Handler<Throwable> handler2) {
                return this;
            }

            /* renamed from: write, reason: avoid collision after fix types in other method */
            public WriteStream<Buffer> write2(Buffer data, Handler<AsyncResult<Void>> handler2) {
                this.buffer.appendBuffer(data);
                handler2.handle(Future.succeededFuture());
                return this;
            }

            @Override // io.vertx.core.streams.WriteStream
            public WriteStream<Buffer> write(Buffer data) {
                this.buffer.appendBuffer(data);
                return this;
            }

            @Override // io.vertx.core.streams.WriteStream
            public void end() {
                end((Handler<AsyncResult<Void>>) null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.vertx.core.streams.WriteStream
            public void end(Handler<AsyncResult<Void>> handler2) {
                Object objApply;
                if (!this.state.future().isComplete()) {
                    if (this.buffer.length() > 0) {
                        try {
                            objApply = BodyCodecImpl.this.decoder.apply(this.buffer);
                        } catch (Throwable th) {
                            this.state.fail(th);
                            if (handler2 != null) {
                                handler2.handle(Future.failedFuture(th));
                                return;
                            }
                            return;
                        }
                    } else {
                        objApply = null;
                    }
                    this.state.complete(objApply);
                    if (handler2 != null) {
                        handler2.handle(Future.succeededFuture());
                    }
                }
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
            public WriteStream<Buffer> drainHandler(Handler<Void> handler2) {
                return this;
            }
        }));
    }
}
