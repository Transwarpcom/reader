package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/CloseHooks.class */
class CloseHooks {
    private final Logger log;
    private boolean closeHooksRun;
    private Set<Closeable> closeHooks;

    CloseHooks(Logger log) {
        this.log = log;
    }

    synchronized void add(Closeable hook) {
        if (this.closeHooks == null) {
            this.closeHooks = new HashSet();
        }
        this.closeHooks.add(hook);
    }

    synchronized boolean remove(Closeable hook) {
        if (this.closeHooks != null) {
            return this.closeHooks.remove(hook);
        }
        return false;
    }

    void run(Handler<AsyncResult<Void>> completionHandler) {
        Set<Closeable> copy = null;
        synchronized (this) {
            if (this.closeHooksRun) {
                throw new IllegalStateException("Close hooks already run");
            }
            this.closeHooksRun = true;
            if (this.closeHooks != null && !this.closeHooks.isEmpty()) {
                copy = new HashSet<>(this.closeHooks);
            }
        }
        if (copy != null && !copy.isEmpty()) {
            int num = copy.size();
            if (num != 0) {
                AtomicInteger count = new AtomicInteger();
                AtomicBoolean failed = new AtomicBoolean();
                for (Closeable hook : copy) {
                    Promise<Void> promise = Promise.promise();
                    promise.future().setHandler2(ar -> {
                        if (ar.failed()) {
                            if (failed.compareAndSet(false, true)) {
                                completionHandler.handle(Future.failedFuture(ar.cause()));
                            }
                        } else if (count.incrementAndGet() == num) {
                            completionHandler.handle(Future.succeededFuture());
                        }
                    });
                    try {
                        hook.close(promise);
                    } catch (Throwable t) {
                        this.log.warn("Failed to run close hooks", t);
                        promise.tryFail(t);
                    }
                }
                return;
            }
            completionHandler.handle(Future.succeededFuture());
            return;
        }
        completionHandler.handle(Future.succeededFuture());
    }
}
