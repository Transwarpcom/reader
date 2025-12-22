package io.vertx.core.impl;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxThreadFactory.class */
public class VertxThreadFactory implements ThreadFactory {
    private static final Object FOO = new Object();
    private static Map<VertxThread, Object> weakMap = new WeakHashMap();
    private final String prefix;
    private final AtomicInteger threadCount = new AtomicInteger(0);
    private final BlockedThreadChecker checker;
    private final boolean worker;
    private final long maxExecTime;
    private final TimeUnit maxExecTimeUnit;

    private static synchronized void addToMap(VertxThread thread) {
        weakMap.put(thread, FOO);
    }

    VertxThreadFactory(String prefix, BlockedThreadChecker checker, boolean worker, long maxExecTime, TimeUnit maxExecTimeUnit) {
        this.prefix = prefix;
        this.checker = checker;
        this.worker = worker;
        this.maxExecTime = maxExecTime;
        this.maxExecTimeUnit = maxExecTimeUnit;
    }

    public static synchronized void unsetContext(ContextImpl ctx) {
        for (VertxThread thread : weakMap.keySet()) {
            if (thread.getContext() == ctx) {
                thread.setContext(null);
            }
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        VertxThread t = new VertxThread(runnable, this.prefix + this.threadCount.getAndIncrement(), this.worker, this.maxExecTime, this.maxExecTimeUnit);
        if (this.checker != null) {
            this.checker.registerThread(t, t);
        }
        addToMap(t);
        t.setDaemon(false);
        return t;
    }
}
