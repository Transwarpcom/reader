package cn.hutool.core.thread;

import java.util.concurrent.ExecutorService;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/FinalizableDelegatedExecutorService.class */
public class FinalizableDelegatedExecutorService extends DelegatedExecutorService {
    FinalizableDelegatedExecutorService(ExecutorService executor) {
        super(executor);
    }

    protected void finalize() {
        super.shutdown();
    }
}
