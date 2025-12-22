package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/FutureImpl.class */
class FutureImpl<T> implements Promise<T>, Future<T> {
    private boolean failed;
    private boolean succeeded;
    private Handler<AsyncResult<T>> handler;
    private T result;
    private Throwable throwable;

    FutureImpl() {
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized T result() {
        return this.result;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized Throwable cause() {
        return this.throwable;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized boolean succeeded() {
        return this.succeeded;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized boolean failed() {
        return this.failed;
    }

    @Override // io.vertx.core.Future
    public synchronized boolean isComplete() {
        return this.failed || this.succeeded;
    }

    @Override // io.vertx.core.Future
    public Future<T> setHandler(Handler<AsyncResult<T>> handler) {
        Objects.requireNonNull(handler, "No null handler accepted");
        synchronized (this) {
            if (!isComplete()) {
                if (this.handler == null) {
                    this.handler = handler;
                } else {
                    addHandler(handler);
                }
                return this;
            }
            dispatch(handler);
            return this;
        }
    }

    private void addHandler(Handler<AsyncResult<T>> h) {
        FutureImpl<T>.Handlers<T> handlers;
        if (this.handler instanceof Handlers) {
            handlers = (Handlers) this.handler;
        } else {
            handlers = new Handlers<>();
            handlers.add(this.handler);
            this.handler = handlers;
        }
        handlers.add(h);
    }

    protected void dispatch(Handler<AsyncResult<T>> handler) {
        if (handler instanceof Handlers) {
            Iterator<Handler<AsyncResult<T>>> it = ((Handlers) handler).iterator();
            while (it.hasNext()) {
                Handler<AsyncResult<T>> h = it.next();
                h.handle(this);
            }
            return;
        }
        handler.handle(this);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void complete(T result) {
        if (!tryComplete(result)) {
            throw new IllegalStateException("Result is already complete: " + (this.succeeded ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void complete() {
        if (!tryComplete()) {
            throw new IllegalStateException("Result is already complete: " + (this.succeeded ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void fail(Throwable cause) {
        if (!tryFail(cause)) {
            throw new IllegalStateException("Result is already complete: " + (this.succeeded ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void fail(String failureMessage) {
        if (!tryFail(failureMessage)) {
            throw new IllegalStateException("Result is already complete: " + (this.succeeded ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryComplete(T result) {
        synchronized (this) {
            if (this.succeeded || this.failed) {
                return false;
            }
            this.result = result;
            this.succeeded = true;
            Handler<AsyncResult<T>> h = this.handler;
            this.handler = null;
            if (h != null) {
                dispatch(h);
                return true;
            }
            return true;
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryComplete() {
        return tryComplete(null);
    }

    public void handle(Future<T> ar) {
        if (ar.succeeded()) {
            complete(ar.result());
        } else {
            fail(ar.cause());
        }
    }

    @Override // io.vertx.core.Future
    public Handler<AsyncResult<T>> completer() {
        return this;
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Handler
    public void handle(AsyncResult<T> asyncResult) {
        if (asyncResult.succeeded()) {
            complete(asyncResult.result());
        } else {
            fail(asyncResult.cause());
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryFail(Throwable cause) {
        synchronized (this) {
            if (this.succeeded || this.failed) {
                return false;
            }
            this.throwable = cause != null ? cause : new NoStackTraceThrowable(null);
            this.failed = true;
            Handler<AsyncResult<T>> h = this.handler;
            this.handler = null;
            if (h != null) {
                h.handle(this);
                return true;
            }
            return true;
        }
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryFail(String failureMessage) {
        return tryFail(new NoStackTraceThrowable(failureMessage));
    }

    @Override // io.vertx.core.Promise
    public Future<T> future() {
        return this;
    }

    public String toString() {
        synchronized (this) {
            if (this.succeeded) {
                return "Future{result=" + this.result + "}";
            }
            if (this.failed) {
                return "Future{cause=" + this.throwable.getMessage() + "}";
            }
            return "Future{unresolved}";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/FutureImpl$Handlers.class */
    private class Handlers<T> extends ArrayList<Handler<AsyncResult<T>>> implements Handler<AsyncResult<T>> {
        private Handlers() {
        }

        @Override // io.vertx.core.Handler
        public void handle(AsyncResult<T> res) {
            Iterator<Handler<AsyncResult<T>>> it = iterator();
            while (it.hasNext()) {
                Handler<AsyncResult<T>> handler = it.next();
                handler.handle(res);
            }
        }
    }
}
