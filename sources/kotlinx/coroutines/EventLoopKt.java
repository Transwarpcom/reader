package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: EventLoop.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\u001a\b\u0010��\u001a\u00020\u0001H��\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "processNextEventInCurrentThread", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopKt.class */
public final class EventLoopKt {
    @NotNull
    public static final EventLoop createEventLoop() {
        return new BlockingEventLoop(Thread.currentThread());
    }

    @InternalCoroutinesApi
    public static final long processNextEventInCurrentThread() {
        EventLoop eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        if (eventLoopCurrentOrNull$kotlinx_coroutines_core == null) {
            return Long.MAX_VALUE;
        }
        return eventLoopCurrentOrNull$kotlinx_coroutines_core.processNextEvent();
    }
}
