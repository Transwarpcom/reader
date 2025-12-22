package io.vertx.core.impl;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.StaticHandler;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/BlockedThreadChecker.class */
public class BlockedThreadChecker {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) BlockedThreadChecker.class);
    private final Map<Thread, Task> threads = new WeakHashMap();
    private final Timer timer = new Timer("vertx-blocked-thread-checker", true);

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/BlockedThreadChecker$Task.class */
    public interface Task {
        long startTime();

        long maxExecTime();

        TimeUnit maxExecTimeUnit();
    }

    BlockedThreadChecker(long interval, TimeUnit intervalUnit, final long warningExceptionTime, final TimeUnit warningExceptionTimeUnit) {
        this.timer.schedule(new TimerTask() { // from class: io.vertx.core.impl.BlockedThreadChecker.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                synchronized (BlockedThreadChecker.this) {
                    long now = System.nanoTime();
                    for (Map.Entry<Thread, Task> entry : BlockedThreadChecker.this.threads.entrySet()) {
                        long execStart = entry.getValue().startTime();
                        long dur = now - execStart;
                        long timeLimit = entry.getValue().maxExecTime();
                        TimeUnit maxExecTimeUnit = entry.getValue().maxExecTimeUnit();
                        long val = maxExecTimeUnit.convert(dur, TimeUnit.NANOSECONDS);
                        if (execStart != 0 && val >= timeLimit) {
                            String message = "Thread " + entry + " has been blocked for " + (dur / StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS) + " ms, time limit is " + TimeUnit.MILLISECONDS.convert(timeLimit, maxExecTimeUnit) + " ms";
                            if (warningExceptionTimeUnit.convert(dur, TimeUnit.NANOSECONDS) <= warningExceptionTime) {
                                BlockedThreadChecker.log.warn(message);
                            } else {
                                VertxException stackTrace = new VertxException("Thread blocked");
                                stackTrace.setStackTrace(entry.getKey().getStackTrace());
                                BlockedThreadChecker.log.warn(message, stackTrace);
                            }
                        }
                    }
                }
            }
        }, intervalUnit.toMillis(interval), intervalUnit.toMillis(interval));
    }

    public synchronized void registerThread(Thread thread, Task checked) {
        this.threads.put(thread, checked);
    }

    public void close() {
        this.timer.cancel();
    }
}
