package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/CompositeFutureImpl.class */
public class CompositeFutureImpl implements CompositeFuture, Handler<AsyncResult<CompositeFuture>> {
    private static final Function<CompositeFuture, Object> ALL = cf -> {
        int size = cf.size();
        for (int i = 0; i < size; i++) {
            if (!cf.succeeded(i)) {
                return cf.cause(i);
            }
        }
        return cf;
    };
    private final Future[] results;
    private int count;
    private Object result;
    private Promise<CompositeFuture> promise = Promise.promise();

    public static CompositeFuture all(Future<?>... results) {
        CompositeFutureImpl composite = new CompositeFutureImpl(results);
        int len = results.length;
        for (Future<?> result : results) {
            result.setHandler2(ar -> {
                if (ar.succeeded()) {
                    synchronized (composite) {
                        composite.count++;
                        if (composite.isComplete() || composite.count < len) {
                            return;
                        }
                        composite.complete();
                        return;
                    }
                }
                synchronized (composite) {
                    if (composite.isComplete()) {
                        return;
                    }
                    composite.fail(ar.cause());
                }
            });
        }
        if (len == 0) {
            composite.complete();
        }
        return composite;
    }

    public static CompositeFuture any(Future<?>... results) {
        CompositeFutureImpl composite = new CompositeFutureImpl(results);
        int len = results.length;
        for (Future<?> result : results) {
            result.setHandler2(ar -> {
                if (ar.succeeded()) {
                    synchronized (composite) {
                        if (composite.isComplete()) {
                            return;
                        }
                        composite.complete();
                        return;
                    }
                }
                synchronized (composite) {
                    composite.count++;
                    if (composite.isComplete() || composite.count < len) {
                        return;
                    }
                    composite.fail(ar.cause());
                }
            });
        }
        if (results.length == 0) {
            composite.complete();
        }
        return composite;
    }

    public static CompositeFuture join(Future<?>... results) {
        return join(ALL, results);
    }

    private static CompositeFuture join(Function<CompositeFuture, Object> pred, Future<?>... results) {
        CompositeFutureImpl compositeFutureImpl = new CompositeFutureImpl(results);
        int len = results.length;
        for (Future<?> result : results) {
            result.setHandler2(ar -> {
                synchronized (compositeFutureImpl) {
                    compositeFutureImpl.count++;
                    if (compositeFutureImpl.isComplete() || compositeFutureImpl.count < len) {
                        return;
                    }
                    compositeFutureImpl.doComplete(pred.apply(compositeFutureImpl));
                }
            });
        }
        if (len == 0) {
            compositeFutureImpl.doComplete(compositeFutureImpl);
        }
        return compositeFutureImpl;
    }

    private CompositeFutureImpl(Future<?>... results) {
        this.results = results;
    }

    @Override // io.vertx.core.CompositeFuture, io.vertx.core.Future
    /* renamed from: setHandler */
    public Future<CompositeFuture> setHandler2(Handler<AsyncResult<CompositeFuture>> handler) {
        this.promise.future().onComplete2(handler);
        return this;
    }

    @Override // io.vertx.core.CompositeFuture
    public Throwable cause(int index) {
        return future(index).cause();
    }

    @Override // io.vertx.core.CompositeFuture
    public boolean succeeded(int index) {
        return future(index).succeeded();
    }

    @Override // io.vertx.core.CompositeFuture
    public boolean failed(int index) {
        return future(index).failed();
    }

    @Override // io.vertx.core.CompositeFuture
    public boolean isComplete(int index) {
        return future(index).isComplete();
    }

    @Override // io.vertx.core.CompositeFuture
    public <T> T resultAt(int index) {
        return future(index).result();
    }

    private <T> Future<T> future(int index) {
        if (index < 0 || index > this.results.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.results[index];
    }

    @Override // io.vertx.core.CompositeFuture
    public int size() {
        return this.results.length;
    }

    @Override // io.vertx.core.Future
    public synchronized boolean isComplete() {
        return this.result != null;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized boolean succeeded() {
        return this.result == this;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized boolean failed() {
        return this.result instanceof Throwable;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized Throwable cause() {
        if (this.result instanceof Throwable) {
            return (Throwable) this.result;
        }
        return null;
    }

    @Override // io.vertx.core.Future, io.vertx.core.AsyncResult
    public synchronized CompositeFuture result() {
        if (this.result == this) {
            return this;
        }
        return null;
    }

    @Override // io.vertx.core.CompositeFuture, io.vertx.core.Future
    public void complete() {
        if (!tryComplete()) {
            throw new IllegalStateException("Result is already complete: " + (this.result == this ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Future
    public void complete(CompositeFuture result) {
        if (!tryComplete(result)) {
            throw new IllegalStateException("Result is already complete: " + (result == this ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Future
    public void fail(Throwable cause) {
        if (!tryFail(cause)) {
            throw new IllegalStateException("Result is already complete: " + (this.result == this ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Future
    public void fail(String failureMessage) {
        if (!tryFail(failureMessage)) {
            throw new IllegalStateException("Result is already complete: " + (this.result == this ? "succeeded" : "failed"));
        }
    }

    @Override // io.vertx.core.Future
    public boolean tryComplete(CompositeFuture result) {
        return doComplete(result);
    }

    @Override // io.vertx.core.CompositeFuture, io.vertx.core.Future
    public boolean tryComplete() {
        return tryComplete((CompositeFuture) this);
    }

    @Override // io.vertx.core.Future
    public boolean tryFail(Throwable cause) {
        return doComplete(cause);
    }

    @Override // io.vertx.core.Future
    public boolean tryFail(String failureMessage) {
        return tryFail(new NoStackTraceThrowable(failureMessage));
    }

    private boolean doComplete(Object result) {
        synchronized (this) {
            if (this.result != null) {
                return false;
            }
            this.result = result;
            this.promise.handle((AsyncResult<CompositeFuture>) this);
            return true;
        }
    }

    @Override // io.vertx.core.Future
    public Handler<AsyncResult<CompositeFuture>> completer() {
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.Future, io.vertx.core.Handler
    public void handle(AsyncResult<CompositeFuture> asyncResult) {
        if (asyncResult.succeeded()) {
            complete((CompositeFuture) this);
        } else {
            fail(asyncResult.cause());
        }
    }
}
