package io.vertx.core;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.spi.FutureFactory;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Future.class */
public interface Future<T> extends AsyncResult<T>, Handler<AsyncResult<T>> {

    @GenIgnore
    public static final FutureFactory factory = (FutureFactory) ServiceHelper.loadFactory(FutureFactory.class);

    boolean isComplete();

    @Fluent
    Future<T> setHandler(Handler<AsyncResult<T>> handler);

    @Deprecated
    void complete(T t);

    @Deprecated
    void complete();

    @Deprecated
    void fail(Throwable th);

    @Deprecated
    void fail(String str);

    @Deprecated
    boolean tryComplete(T t);

    @Deprecated
    boolean tryComplete();

    @Deprecated
    boolean tryFail(Throwable th);

    @Deprecated
    boolean tryFail(String str);

    @Override // io.vertx.core.AsyncResult
    T result();

    @Override // io.vertx.core.AsyncResult
    Throwable cause();

    @Override // io.vertx.core.AsyncResult
    boolean succeeded();

    @Override // io.vertx.core.AsyncResult
    boolean failed();

    @Override // io.vertx.core.Handler
    @GenIgnore
    void handle(AsyncResult<T> asyncResult);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.AsyncResult
    /* bridge */ /* synthetic */ default AsyncResult otherwise(Object obj) {
        return otherwise((Future<T>) obj);
    }

    @Override // io.vertx.core.AsyncResult
    /* bridge */ /* synthetic */ default AsyncResult map(Object obj) {
        return map((Future<T>) obj);
    }

    static <T> Future<T> future(Handler<Promise<T>> handler) {
        Promise<T> promise = Promise.promise();
        try {
            handler.handle(promise);
        } catch (Throwable e) {
            promise.tryFail(e);
        }
        return promise.future();
    }

    @Deprecated
    static <T> Future<T> future() {
        return factory.future();
    }

    static <T> Future<T> succeededFuture() {
        return factory.succeededFuture();
    }

    static <T> Future<T> succeededFuture(T result) {
        if (result == null) {
            return factory.succeededFuture();
        }
        return factory.succeededFuture(result);
    }

    static <T> Future<T> failedFuture(Throwable t) {
        return factory.failedFuture(t);
    }

    static <T> Future<T> failedFuture(String failureMessage) {
        return factory.failureFuture(failureMessage);
    }

    @Fluent
    default Future<T> onComplete(Handler<AsyncResult<T>> handler) {
        return setHandler(handler);
    }

    @Fluent
    default Future<T> onSuccess(Handler<T> handler) {
        return onComplete(ar -> {
            if (ar.succeeded()) {
                handler.handle(ar.result());
            }
        });
    }

    @Fluent
    default Future<T> onFailure(Handler<Throwable> handler) {
        return onComplete(ar -> {
            if (ar.failed()) {
                handler.handle(ar.cause());
            }
        });
    }

    default <U> Future<U> flatMap(Function<T, Future<U>> mapper) {
        return compose(mapper);
    }

    @GenIgnore
    @Deprecated
    default <U> Future<U> compose(Handler<T> handler, Future<U> next) {
        setHandler(ar -> {
            if (ar.succeeded()) {
                try {
                    handler.handle(ar.result());
                    return;
                } catch (Throwable err) {
                    if (next.isComplete()) {
                        throw err;
                    }
                    next.fail(err);
                    return;
                }
            }
            next.fail(ar.cause());
        });
        return next;
    }

    default <U> Future<U> compose(Function<T, Future<U>> mapper) {
        return compose(mapper, Future::failedFuture);
    }

    default <U> Future<U> compose(Function<T, Future<U>> successMapper, Function<Throwable, Future<U>> failureMapper) {
        if (successMapper == null) {
            throw new NullPointerException();
        }
        if (failureMapper == null) {
            throw new NullPointerException();
        }
        Promise<U> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                try {
                    ((Future) successMapper.apply(ar.result())).setHandler(ret);
                    return;
                } catch (Throwable e) {
                    ret.fail(e);
                    return;
                }
            }
            try {
                ((Future) failureMapper.apply(ar.cause())).setHandler(ret);
            } catch (Throwable e2) {
                ret.fail(e2);
            }
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default <U> Future<U> map(Function<T, U> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        Promise<U> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                try {
                    ret.complete(mapper.apply(ar.result()));
                    return;
                } catch (Throwable e) {
                    ret.fail(e);
                    return;
                }
            }
            ret.fail(ar.cause());
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default <V> Future<V> map(V value) {
        Promise<V> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                ret.complete(value);
            } else {
                ret.fail(ar.cause());
            }
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default <V> Future<V> mapEmpty() {
        return (Future) super.mapEmpty();
    }

    @Deprecated
    @CacheReturn
    default Handler<AsyncResult<T>> completer() {
        return this;
    }

    default Future<T> recover(Function<Throwable, Future<T>> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        Promise<T> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                ret.complete(result());
                return;
            }
            try {
                Future<T> mapped = (Future) mapper.apply(ar.cause());
                mapped.setHandler(ret);
            } catch (Throwable e) {
                ret.fail(e);
            }
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default Future<T> otherwise(Function<Throwable, T> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        Promise<T> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                ret.complete(result());
                return;
            }
            try {
                ret.complete(mapper.apply(ar.cause()));
            } catch (Throwable e) {
                ret.fail(e);
            }
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default Future<T> otherwise(T value) {
        Promise<T> ret = Promise.promise();
        setHandler(ar -> {
            if (ar.succeeded()) {
                ret.complete(result());
            } else {
                ret.complete(value);
            }
        });
        return ret.future();
    }

    @Override // io.vertx.core.AsyncResult
    default Future<T> otherwiseEmpty() {
        return (Future) super.otherwiseEmpty();
    }
}
