package kotlin.reflect.jvm.internal.impl.storage;

import org.jetbrains.annotations.NotNull;

/* compiled from: locks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/EmptySimpleLock.class */
public final class EmptySimpleLock implements SimpleLock {

    @NotNull
    public static final EmptySimpleLock INSTANCE = new EmptySimpleLock();

    private EmptySimpleLock() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void lock() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void unlock() {
    }
}
