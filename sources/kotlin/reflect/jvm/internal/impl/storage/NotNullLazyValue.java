package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;

/* compiled from: storage.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/NotNullLazyValue.class */
public interface NotNullLazyValue<T> extends Function0<T> {
    boolean isComputed();
}
