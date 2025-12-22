package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* compiled from: storage.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/CacheWithNotNullValues.class */
public interface CacheWithNotNullValues<K, V> {
    @NotNull
    V computeIfAbsent(K k, @NotNull Function0<? extends V> function0);
}
