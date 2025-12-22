package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DefaultExecutor.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018��2\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\r\u0010\u001e\u001a\u00020\u001cH��¢\u0006\u0002\b\u001fJ$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\b2\n\u0010#\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0014H\u0002J\b\u0010'\u001a\u00020\u001cH\u0016J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n��R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\b\n��\u0012\u0004\b\u0011\u0010\u0004R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00148@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00108TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "THREAD_NAME", "", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", "debugStatus", "isShutdownRequested", "", "()Z", "isThreadPresent", "isThreadPresent$kotlinx_coroutines_core", "thread", "getThread", "()Ljava/lang/Thread;", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "notifyStartup", "run", "shutdown", RtspHeaders.Values.TIMEOUT, "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DefaultExecutor.class */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {

    @NotNull
    public static final DefaultExecutor INSTANCE = new DefaultExecutor();

    @NotNull
    public static final String THREAD_NAME = "kotlinx.coroutines.DefaultExecutor";
    private static final long DEFAULT_KEEP_ALIVE = 1000;
    private static final long KEEP_ALIVE_NANOS;

    @Nullable
    private static volatile Thread _thread;
    private static final int FRESH = 0;
    private static final int ACTIVE = 1;
    private static final int SHUTDOWN_REQ = 2;
    private static final int SHUTDOWN_ACK = 3;
    private static volatile int debugStatus;

    private static /* synthetic */ void get_thread$annotations() {
    }

    private DefaultExecutor() {
    }

    static {
        TimeUnit timeUnit;
        Long l;
        EventLoop.incrementUseCount$default(INSTANCE, false, 1, null);
        TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
        try {
            timeUnit = timeUnit2;
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException e) {
            timeUnit = timeUnit2;
            l = 1000L;
        }
        KEEP_ALIVE_NANOS = timeUnit.toNanos(l.longValue());
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    @NotNull
    protected Thread getThread() {
        Thread thread = _thread;
        return thread == null ? createThreadSync() : thread;
    }

    private final boolean isShutdownRequested() {
        int debugStatus2 = debugStatus;
        return debugStatus2 == 2 || debugStatus2 == 3;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long timeMillis, @NotNull Runnable block, @NotNull CoroutineContext context) {
        return scheduleInvokeOnTimeout(timeMillis, block);
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.lang.Runnable
    public void run() {
        ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core(this);
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        if (timeSource != null) {
            timeSource.registerTimeLoopThread();
        }
        try {
            long shutdownNanos = Long.MAX_VALUE;
            if (!notifyStartup()) {
                _thread = null;
                acknowledgeShutdownIfNeeded();
                AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
                if (timeSource2 != null) {
                    timeSource2.unregisterTimeLoopThread();
                }
                if (isEmpty()) {
                    return;
                }
                getThread();
                return;
            }
            while (true) {
                Thread.interrupted();
                long parkNanos = processNextEvent();
                if (parkNanos == Long.MAX_VALUE) {
                    AbstractTimeSource timeSource3 = AbstractTimeSourceKt.getTimeSource();
                    long now = timeSource3 == null ? System.nanoTime() : timeSource3.nanoTime();
                    if (shutdownNanos == Long.MAX_VALUE) {
                        shutdownNanos = now + KEEP_ALIVE_NANOS;
                    }
                    long tillShutdown = shutdownNanos - now;
                    if (tillShutdown <= 0) {
                        _thread = null;
                        acknowledgeShutdownIfNeeded();
                        AbstractTimeSource timeSource4 = AbstractTimeSourceKt.getTimeSource();
                        if (timeSource4 != null) {
                            timeSource4.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getThread();
                        return;
                    }
                    parkNanos = RangesKt.coerceAtMost(parkNanos, tillShutdown);
                } else {
                    shutdownNanos = Long.MAX_VALUE;
                }
                if (parkNanos > 0) {
                    if (isShutdownRequested()) {
                        _thread = null;
                        acknowledgeShutdownIfNeeded();
                        AbstractTimeSource timeSource5 = AbstractTimeSourceKt.getTimeSource();
                        if (timeSource5 != null) {
                            timeSource5.unregisterTimeLoopThread();
                        }
                        if (isEmpty()) {
                            return;
                        }
                        getThread();
                        return;
                    }
                    AbstractTimeSource timeSource6 = AbstractTimeSourceKt.getTimeSource();
                    if (timeSource6 == null) {
                        LockSupport.parkNanos(this, parkNanos);
                    } else {
                        timeSource6.parkNanos(this, parkNanos);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            acknowledgeShutdownIfNeeded();
            AbstractTimeSource timeSource7 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource7 != null) {
                timeSource7.unregisterTimeLoopThread();
            }
            if (!isEmpty()) {
                getThread();
            }
            throw th;
        }
    }

    private final synchronized Thread createThreadSync() {
        Thread thread = _thread;
        if (thread != null) {
            return thread;
        }
        Thread $this$createThreadSync_u24lambda_u2d0 = new Thread(this, THREAD_NAME);
        DefaultExecutor defaultExecutor = INSTANCE;
        _thread = $this$createThreadSync_u24lambda_u2d0;
        $this$createThreadSync_u24lambda_u2d0.setDaemon(true);
        $this$createThreadSync_u24lambda_u2d0.start();
        return $this$createThreadSync_u24lambda_u2d0;
    }

    public final synchronized void ensureStarted$kotlinx_coroutines_core() throws InterruptedException {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(_thread == null)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(debugStatus == 0 || debugStatus == 3)) {
                throw new AssertionError();
            }
        }
        debugStatus = 0;
        createThreadSync();
        while (debugStatus == 0) {
            wait();
        }
    }

    private final synchronized boolean notifyStartup() {
        if (isShutdownRequested()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    public final synchronized void shutdown(long timeout) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeout;
        if (!isShutdownRequested()) {
            debugStatus = 2;
        }
        while (debugStatus != 3 && _thread != null) {
            Thread it = _thread;
            if (it != null) {
                AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
                if (timeSource == null) {
                    LockSupport.unpark(it);
                } else {
                    timeSource.unpark(it);
                }
            }
            long remaining = deadline - System.currentTimeMillis();
            if (remaining <= 0) {
                break;
            } else {
                wait(timeout);
            }
        }
        debugStatus = 0;
    }

    private final synchronized void acknowledgeShutdownIfNeeded() {
        if (isShutdownRequested()) {
            debugStatus = 3;
            resetAll();
            notifyAll();
        }
    }

    public final boolean isThreadPresent$kotlinx_coroutines_core() {
        return _thread != null;
    }
}
