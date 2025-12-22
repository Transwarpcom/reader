package kotlinx.coroutines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: ThreadPoolDispatcher.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ThreadPoolDispatcherKt.class */
public final class ThreadPoolDispatcherKt {
    @ObsoleteCoroutinesApi
    @NotNull
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(@NotNull String name) {
        return newFixedThreadPoolContext(1, name);
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int nThreads, @NotNull String name) {
        if (!(nThreads >= 1)) {
            throw new IllegalArgumentException(("Expected at least one thread, but " + nThreads + " specified").toString());
        }
        AtomicInteger threadNo = new AtomicInteger();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(nThreads, (v3) -> {
            return m4202newFixedThreadPoolContext$lambda1(r1, r2, r3, v3);
        });
        return ExecutorsKt.from((ExecutorService) executor);
    }

    /* renamed from: newFixedThreadPoolContext$lambda-1, reason: not valid java name */
    private static final Thread m4202newFixedThreadPoolContext$lambda1(int $nThreads, String $name, AtomicInteger $threadNo, Runnable runnable) {
        Thread t = new Thread(runnable, $nThreads == 1 ? $name : $name + '-' + $threadNo.incrementAndGet());
        t.setDaemon(true);
        return t;
    }
}
