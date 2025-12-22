package io.vertx.core;

import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/AsyncResult.class */
public interface AsyncResult<T> {
    T result();

    Throwable cause();

    boolean succeeded();

    boolean failed();

    default <U> AsyncResult<U> map(final Function<T, U> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        return new AsyncResult<U>() { // from class: io.vertx.core.AsyncResult.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.vertx.core.AsyncResult
            public U result() {
                if (succeeded()) {
                    return (U) mapper.apply(AsyncResult.this.result());
                }
                return null;
            }

            @Override // io.vertx.core.AsyncResult
            public Throwable cause() {
                return AsyncResult.this.cause();
            }

            @Override // io.vertx.core.AsyncResult
            public boolean succeeded() {
                return AsyncResult.this.succeeded();
            }

            @Override // io.vertx.core.AsyncResult
            public boolean failed() {
                return AsyncResult.this.failed();
            }
        };
    }

    default <V> AsyncResult<V> map(V v) {
        return (AsyncResult<V>) map((Function) t -> {
            return v;
        });
    }

    default <V> AsyncResult<V> mapEmpty() {
        return map((AsyncResult<T>) null);
    }

    default AsyncResult<T> otherwise(final Function<Throwable, T> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        return new AsyncResult<T>() { // from class: io.vertx.core.AsyncResult.2
            @Override // io.vertx.core.AsyncResult
            public T result() {
                if (AsyncResult.this.succeeded()) {
                    return (T) AsyncResult.this.result();
                }
                if (AsyncResult.this.failed()) {
                    return (T) mapper.apply(AsyncResult.this.cause());
                }
                return null;
            }

            @Override // io.vertx.core.AsyncResult
            public Throwable cause() {
                return null;
            }

            @Override // io.vertx.core.AsyncResult
            public boolean succeeded() {
                return AsyncResult.this.succeeded() || AsyncResult.this.failed();
            }

            @Override // io.vertx.core.AsyncResult
            public boolean failed() {
                return false;
            }
        };
    }

    default AsyncResult<T> otherwise(T value) {
        return otherwise((Function) err -> {
            return value;
        });
    }

    default AsyncResult<T> otherwiseEmpty() {
        return otherwise((Function) err -> {
            return null;
        });
    }
}
