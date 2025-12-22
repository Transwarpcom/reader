package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/FailedFuture.class */
public class FailedFuture<T> implements Future<T>, Promise<T> {
    private final Throwable cause;

    FailedFuture(Throwable t) {
        this.cause = t != null ? t : new NoStackTraceThrowable(null);
    }

    FailedFuture(String failureMessage) {
        this(new NoStackTraceThrowable(failureMessage));
    }

    @Override // io.vertx.core.Future
    public boolean isComplete() {
        return true;
    }

    @Override // io.vertx.core.Future
    public Future<T> setHandler(Handler<AsyncResult<T>> handler) {
        handler.handle(this);
        return this;
    }

    @Override // io.vertx.core.Future
    public void complete(T result) {
        throw new IllegalStateException("Result is already complete: failed");
    }

    @Override // io.vertx.core.Future
    public void complete() {
        throw new IllegalStateException("Result is already complete: failed");
    }

    @Override // io.vertx.core.Future
    public void fail(Throwable cause) {
        throw new IllegalStateException("Result is already complete: failed");
    }

    @Override // io.vertx.core.Future
    public void fail(String failureMessage) {
        throw new IllegalStateException("Result is already complete: failed");
    }

    @Override // io.vertx.core.Future
    public boolean tryComplete(T result) {
        return false;
    }

    @Override // io.vertx.core.Future
    public boolean tryComplete() {
        return false;
    }

    @Override // io.vertx.core.Future
    public boolean tryFail(Throwable cause) {
        return false;
    }

    @Override // io.vertx.core.Future
    public boolean tryFail(String failureMessage) {
        return false;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public T result() {
        return null;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public Throwable cause() {
        return this.cause;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public boolean succeeded() {
        return false;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public boolean failed() {
        return true;
    }

    @Override // io.vertx.core.Future, io.vertx.core.Handler
    public void handle(AsyncResult<T> asyncResult) {
        throw new IllegalStateException("Result is already complete: failed");
    }

    @Override // io.vertx.core.Promise
    public Future<T> future() {
        return this;
    }

    public String toString() {
        return "Future{cause=" + this.cause.getMessage() + "}";
    }
}
