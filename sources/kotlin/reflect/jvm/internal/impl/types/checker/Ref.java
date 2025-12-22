package kotlin.reflect.jvm.internal.impl.types.checker;

import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinTypeRefiner.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/Ref.class */
public final class Ref<T> {

    @Nullable
    private T value;

    public Ref(@Nullable T t) {
        this.value = t;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }
}
