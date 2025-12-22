package cn.hutool.core.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/RejectPolicy.class */
public enum RejectPolicy {
    ABORT(new ThreadPoolExecutor.AbortPolicy()),
    DISCARD(new ThreadPoolExecutor.DiscardPolicy()),
    DISCARD_OLDEST(new ThreadPoolExecutor.DiscardOldestPolicy()),
    CALLER_RUNS(new ThreadPoolExecutor.CallerRunsPolicy());

    private final RejectedExecutionHandler value;

    RejectPolicy(RejectedExecutionHandler handler) {
        this.value = handler;
    }

    public RejectedExecutionHandler getValue() {
        return this.value;
    }
}
