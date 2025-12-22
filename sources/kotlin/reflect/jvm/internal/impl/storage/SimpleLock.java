package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: locks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/SimpleLock.class */
public interface SimpleLock {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    void lock();

    void unlock();

    /* compiled from: locks.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/SimpleLock$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @NotNull
        public final DefaultSimpleLock simpleLock(@Nullable Runnable checkCancelled, @Nullable Function1<? super InterruptedException, Unit> function1) {
            if (checkCancelled != null && function1 != null) {
                return new CancellableSimpleLock(checkCancelled, function1);
            }
            return new DefaultSimpleLock(null, 1, null);
        }
    }
}
