package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJavaClassFinder.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectJavaClassFinderKt.class */
public final class ReflectJavaClassFinderKt {
    @Nullable
    public static final Class<?> tryLoadClass(@NotNull ClassLoader $this$tryLoadClass, @NotNull String fqName) throws ClassNotFoundException {
        Class<?> cls;
        Intrinsics.checkNotNullParameter($this$tryLoadClass, "<this>");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        try {
            cls = Class.forName(fqName, false, $this$tryLoadClass);
        } catch (ClassNotFoundException e) {
            cls = (Class) null;
        }
        return cls;
    }
}
