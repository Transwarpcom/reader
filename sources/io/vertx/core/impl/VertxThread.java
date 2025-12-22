package io.vertx.core.impl;

import io.netty.util.concurrent.FastThreadLocalThread;
import io.vertx.core.impl.BlockedThreadChecker;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxThread.class */
public final class VertxThread extends FastThreadLocalThread implements BlockedThreadChecker.Task {
    private final boolean worker;
    private final long maxExecTime;
    private final TimeUnit maxExecTimeUnit;
    private long execStart;
    private ContextImpl context;

    public VertxThread(Runnable target, String name, boolean worker, long maxExecTime, TimeUnit maxExecTimeUnit) {
        super(target, name);
        this.worker = worker;
        this.maxExecTime = maxExecTime;
        this.maxExecTimeUnit = maxExecTimeUnit;
    }

    ContextImpl getContext() {
        return this.context;
    }

    void setContext(ContextImpl context) {
        this.context = context;
    }

    public final void executeStart() {
        this.execStart = System.nanoTime();
    }

    public final void executeEnd() {
        this.execStart = 0L;
    }

    @Override // io.vertx.core.impl.BlockedThreadChecker.Task
    public long startTime() {
        return this.execStart;
    }

    public boolean isWorker() {
        return this.worker;
    }

    @Override // io.vertx.core.impl.BlockedThreadChecker.Task
    public long maxExecTime() {
        return this.maxExecTime;
    }

    @Override // io.vertx.core.impl.BlockedThreadChecker.Task
    public TimeUnit maxExecTimeUnit() {
        return this.maxExecTimeUnit;
    }
}
