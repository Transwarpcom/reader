package io.vertx.core.shareddata.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.shareddata.Lock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncLocks.class */
public class LocalAsyncLocks {
    private final ConcurrentMap<String, List<LockWaiter>> waitersMap = new ConcurrentHashMap();

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncLocks$Status.class */
    private enum Status {
        WAITING,
        ACQUIRED,
        TIMED_OUT
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncLocks$LockWaiter.class */
    private class LockWaiter {
        final Context context;
        final String lockName;
        final Handler<AsyncResult<Lock>> handler;
        final AtomicReference<Status> status = new AtomicReference<>(Status.WAITING);
        final Long timerId;

        LockWaiter(Context context, String lockName, long timeout, Handler<AsyncResult<Lock>> handler) {
            this.context = context;
            this.lockName = lockName;
            this.handler = handler;
            this.timerId = timeout != Long.MAX_VALUE ? Long.valueOf(context.owner().setTimer(timeout, tid -> {
                timeout();
            })) : null;
        }

        boolean isWaiting() {
            return this.status.get() == Status.WAITING;
        }

        void timeout() {
            if (this.status.compareAndSet(Status.WAITING, Status.TIMED_OUT)) {
                this.handler.handle(Future.failedFuture("Timed out waiting to get lock"));
            }
        }

        void acquireLock() {
            if (this.status.compareAndSet(Status.WAITING, Status.ACQUIRED)) {
                if (this.timerId != null) {
                    this.context.owner().cancelTimer(this.timerId.longValue());
                }
                this.context.runOnContext(v -> {
                    this.handler.handle(Future.succeededFuture(LocalAsyncLocks.this.new AsyncLock(this.lockName)));
                });
                return;
            }
            this.context.runOnContext(v2 -> {
                LocalAsyncLocks.this.nextWaiter(this.lockName);
            });
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncLocks$AsyncLock.class */
    private class AsyncLock implements Lock {
        final String lockName;
        final AtomicBoolean invoked = new AtomicBoolean();

        AsyncLock(String lockName) {
            this.lockName = lockName;
        }

        @Override // io.vertx.core.shareddata.Lock
        public void release() {
            if (this.invoked.compareAndSet(false, true)) {
                LocalAsyncLocks.this.nextWaiter(this.lockName);
            }
        }
    }

    public void acquire(Context context, String name, long timeout, Handler<AsyncResult<Lock>> handler) {
        LockWaiter lockWaiter = new LockWaiter(context, name, timeout, handler);
        List<LockWaiter> waiters = this.waitersMap.compute(name, (s, list) -> {
            List<LockWaiter> result;
            if (list != null) {
                result = new ArrayList<>(list.size() + 1);
                result.addAll(list);
            } else {
                result = new ArrayList<>(1);
            }
            result.add(lockWaiter);
            return result;
        });
        if (waiters.size() == 1) {
            waiters.get(0).acquireLock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextWaiter(String lockName) {
        List<LockWaiter> waiters = this.waitersMap.compute(lockName, (s, list) -> {
            if (list == null || list.size() == 1) {
                return null;
            }
            return new ArrayList(list.subList(1, list.size()));
        });
        if (waiters != null) {
            waiters.get(0).acquireLock();
        }
    }
}
