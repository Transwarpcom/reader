package io.vertx.core.streams.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/impl/PipeImpl.class */
public class PipeImpl<T> implements Pipe<T> {
    private static final Handler<AsyncResult<Void>> NULL_HANDLER = ar -> {
    };
    private final ReadStream<T> src;
    private WriteStream<T> dst;
    private boolean endOnSuccess = true;
    private boolean endOnFailure = true;
    private final Promise<Void> result = Promise.promise();

    public PipeImpl(ReadStream<T> src) {
        this.src = src;
        Promise<Void> promise = this.result;
        promise.getClass();
        src.endHandler((v1) -> {
            r1.tryComplete(v1);
        });
        Promise<Void> promise2 = this.result;
        promise2.getClass();
        src.exceptionHandler(promise2::tryFail);
    }

    @Override // io.vertx.core.streams.Pipe
    public synchronized Pipe<T> endOnFailure(boolean end) {
        this.endOnFailure = end;
        return this;
    }

    @Override // io.vertx.core.streams.Pipe
    public synchronized Pipe<T> endOnSuccess(boolean end) {
        this.endOnSuccess = end;
        return this;
    }

    @Override // io.vertx.core.streams.Pipe
    public synchronized Pipe<T> endOnComplete(boolean end) {
        this.endOnSuccess = end;
        this.endOnFailure = end;
        return this;
    }

    @Override // io.vertx.core.streams.Pipe
    public void to(WriteStream<T> ws) {
        to(ws, NULL_HANDLER);
    }

    @Override // io.vertx.core.streams.Pipe
    public void to(WriteStream<T> ws, Handler<AsyncResult<Void>> completionHandler) {
        boolean endOnSuccess;
        boolean endOnFailure;
        if (ws == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (this.dst != null) {
                throw new IllegalStateException();
            }
            this.dst = ws;
            endOnSuccess = this.endOnSuccess;
            endOnFailure = this.endOnFailure;
        }
        Handler<Void> drainHandler = v -> {
            this.src.resume2();
        };
        this.src.handler2(item -> {
            ws.write(item);
            if (ws.writeQueueFull()) {
                this.src.pause2();
                ws.drainHandler(drainHandler);
            }
        });
        ws.exceptionHandler(err -> {
            this.result.tryFail(new WriteException(err));
        });
        this.src.resume2();
        this.result.future().setHandler2(ar -> {
            try {
                this.src.handler2(null);
            } catch (Exception e) {
            }
            try {
                this.src.exceptionHandler((Handler<Throwable>) null);
            } catch (Exception e2) {
            }
            try {
                this.src.endHandler(null);
            } catch (Exception e3) {
            }
            try {
                if (ar.succeeded()) {
                    if (endOnSuccess) {
                        ws.end((Handler<AsyncResult<Void>>) completionHandler);
                        return;
                    }
                } else {
                    Throwable err2 = ar.cause();
                    if (err2 instanceof WriteException) {
                        ar = Future.failedFuture(err2.getCause());
                        this.src.resume2();
                    } else if (endOnFailure) {
                        ws.end();
                    }
                }
                completionHandler.handle(ar);
            } catch (Exception e4) {
                if (endOnFailure) {
                    ws.end();
                }
                completionHandler.handle(Future.failedFuture(e4));
            }
        });
    }

    @Override // io.vertx.core.streams.Pipe
    public void close() {
        synchronized (this) {
            this.src.exceptionHandler((Handler<Throwable>) null);
            this.src.handler2(null);
            if (this.dst != null) {
                this.dst.drainHandler(null);
                this.dst.exceptionHandler((Handler<Throwable>) null);
            }
            if (this.result.future().isComplete()) {
                return;
            }
            this.src.resume2();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/impl/PipeImpl$WriteException.class */
    private static class WriteException extends VertxException {
        private WriteException(Throwable cause) {
            super(cause, true);
        }
    }
}
