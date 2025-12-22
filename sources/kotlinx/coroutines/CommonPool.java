package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CommonPool.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\u0004\b��\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0082\b¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0013\u001a\u00060\u001ej\u0002`\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0002J!\u0010!\u001a\u00020\u00102\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010\u0005\u001a\u00020\u0019H��¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0017H��¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H��¢\u0006\u0002\b*J\b\u0010+\u001a\u00020\u0004H\u0016J\r\u0010\u000f\u001a\u00020\u0017H��¢\u0006\u0002\b,R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n��¨\u0006-"}, d2 = {"Lkotlinx/coroutines/CommonPool;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "()V", "DEFAULT_PARALLELISM_PROPERTY_NAME", "", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "parallelism", "", "getParallelism", "()I", "pool", "requestedParallelism", "usePrivatePool", "", "Try", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "createPlainPool", "Ljava/util/concurrent/ExecutorService;", "createPool", "dispatch", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getOrCreatePoolSync", "isGoodCommonPool", "fjpClass", "Ljava/lang/Class;", "isGoodCommonPool$kotlinx_coroutines_core", "restore", "restore$kotlinx_coroutines_core", "shutdown", RtspHeaders.Values.TIMEOUT, "", "shutdown$kotlinx_coroutines_core", "toString", "usePrivatePool$kotlinx_coroutines_core", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CommonPool.class */
public final class CommonPool extends ExecutorCoroutineDispatcher {

    @NotNull
    public static final CommonPool INSTANCE = new CommonPool();

    @NotNull
    private static final String DEFAULT_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.default.parallelism";
    private static final int requestedParallelism;
    private static boolean usePrivatePool;

    @Nullable
    private static volatile Executor pool;

    private CommonPool() {
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        Executor executor = pool;
        return executor == null ? getOrCreatePoolSync() : executor;
    }

    static {
        String property;
        int iIntValue;
        CommonPool commonPool = INSTANCE;
        try {
            property = System.getProperty(DEFAULT_PARALLELISM_PROPERTY_NAME);
        } catch (Throwable th) {
            property = null;
        }
        String property2 = property;
        if (property2 == null) {
            iIntValue = -1;
        } else {
            Integer parallelism = StringsKt.toIntOrNull(property2);
            if (parallelism == null || parallelism.intValue() < 1) {
                throw new IllegalStateException(Intrinsics.stringPlus("Expected positive number in kotlinx.coroutines.default.parallelism, but has ", property2).toString());
            }
            iIntValue = parallelism.intValue();
        }
        requestedParallelism = iIntValue;
    }

    private final int getParallelism() {
        Integer numValueOf = Integer.valueOf(requestedParallelism);
        int it = numValueOf.intValue();
        Integer num = it > 0 ? numValueOf : null;
        return num == null ? RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1) : num.intValue();
    }

    private final <T> T Try(Function0<? extends T> function0) {
        T tInvoke;
        try {
            tInvoke = function0.invoke();
        } catch (Throwable th) {
            tInvoke = null;
        }
        return tInvoke;
    }

    private final ExecutorService createPool() {
        Class cls;
        ExecutorService executorService;
        ExecutorService executorService2;
        if (System.getSecurityManager() != null) {
            return createPlainPool();
        }
        try {
            cls = Class.forName("java.util.concurrent.ForkJoinPool");
        } catch (Throwable th) {
            cls = null;
        }
        Class fjpClass = cls;
        if (fjpClass == null) {
            return createPlainPool();
        }
        if (!usePrivatePool && requestedParallelism < 0) {
            try {
                Object objInvoke = fjpClass.getMethod("commonPool", new Class[0]).invoke(null, new Object[0]);
                executorService2 = objInvoke instanceof ExecutorService ? (ExecutorService) objInvoke : null;
            } catch (Throwable th2) {
                executorService2 = null;
            }
            ExecutorService it = executorService2;
            if (it != null) {
                ExecutorService it2 = INSTANCE.isGoodCommonPool$kotlinx_coroutines_core(fjpClass, it) ? it : null;
                if (it2 != null) {
                    return it2;
                }
            }
        }
        try {
            Object objNewInstance = fjpClass.getConstructor(Integer.TYPE).newInstance(Integer.valueOf(INSTANCE.getParallelism()));
            executorService = objNewInstance instanceof ExecutorService ? (ExecutorService) objNewInstance : null;
        } catch (Throwable th3) {
            executorService = null;
        }
        ExecutorService it3 = executorService;
        return it3 == null ? createPlainPool() : it3;
    }

    public final boolean isGoodCommonPool$kotlinx_coroutines_core(@NotNull Class<?> cls, @NotNull ExecutorService executor) {
        Integer num;
        executor.submit(CommonPool::m4160isGoodCommonPool$lambda9);
        try {
            Object objInvoke = cls.getMethod("getPoolSize", new Class[0]).invoke(executor, new Object[0]);
            num = objInvoke instanceof Integer ? (Integer) objInvoke : null;
        } catch (Throwable th) {
            num = null;
        }
        Integer num2 = num;
        if (num2 == null) {
            return false;
        }
        int actual = num2.intValue();
        return actual >= 1;
    }

    /* renamed from: isGoodCommonPool$lambda-9, reason: not valid java name */
    private static final void m4160isGoodCommonPool$lambda9() {
    }

    private final ExecutorService createPlainPool() {
        AtomicInteger threadId = new AtomicInteger();
        return Executors.newFixedThreadPool(getParallelism(), (v1) -> {
            return m4161createPlainPool$lambda12(r1, v1);
        });
    }

    /* renamed from: createPlainPool$lambda-12, reason: not valid java name */
    private static final Thread m4161createPlainPool$lambda12(AtomicInteger $threadId, Runnable it) {
        Thread $this$createPlainPool_u24lambda_u2d12_u24lambda_u2d11 = new Thread(it, Intrinsics.stringPlus("CommonPool-worker-", Integer.valueOf($threadId.incrementAndGet())));
        $this$createPlainPool_u24lambda_u2d12_u24lambda_u2d11.setDaemon(true);
        return $this$createPlainPool_u24lambda_u2d12_u24lambda_u2d11;
    }

    private final synchronized Executor getOrCreatePoolSync() {
        Executor executor = pool;
        if (executor != null) {
            return executor;
        }
        ExecutorService it = createPool();
        CommonPool commonPool = INSTANCE;
        pool = it;
        return it;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo4326dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
        try {
            Executor executor = pool;
            Executor orCreatePoolSync = executor == null ? getOrCreatePoolSync() : executor;
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            orCreatePoolSync.execute(timeSource == null ? block : timeSource.wrapTask(block));
        } catch (RejectedExecutionException e) {
            AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource2 != null) {
                timeSource2.unTrackTask();
            }
            DefaultExecutor.INSTANCE.enqueue(block);
        }
    }

    public final synchronized void usePrivatePool$kotlinx_coroutines_core() throws InterruptedException {
        shutdown$kotlinx_coroutines_core(0L);
        usePrivatePool = true;
        pool = null;
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long timeout) throws InterruptedException {
        Executor executor = pool;
        ExecutorService $this$shutdown_u24lambda_u2d15 = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if ($this$shutdown_u24lambda_u2d15 != null) {
            $this$shutdown_u24lambda_u2d15.shutdown();
            if (timeout > 0) {
                $this$shutdown_u24lambda_u2d15.awaitTermination(timeout, TimeUnit.MILLISECONDS);
            }
            Iterable $this$forEach$iv = $this$shutdown_u24lambda_u2d15.shutdownNow();
            for (Object element$iv : $this$forEach$iv) {
                Runnable it = (Runnable) element$iv;
                DefaultExecutor.INSTANCE.enqueue(it);
            }
        }
        pool = CommonPool::m4162shutdown$lambda16;
    }

    /* renamed from: shutdown$lambda-16, reason: not valid java name */
    private static final void m4162shutdown$lambda16(Runnable it) {
        throw new RejectedExecutionException("CommonPool was shutdown");
    }

    public final synchronized void restore$kotlinx_coroutines_core() throws InterruptedException {
        shutdown$kotlinx_coroutines_core(0L);
        usePrivatePool = false;
        pool = null;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return "CommonPool";
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }
}
