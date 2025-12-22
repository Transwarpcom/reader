package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: exceptionUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/ExceptionUtilsKt.class */
public final class ExceptionUtilsKt {
    @NotNull
    public static final RuntimeException rethrow(@NotNull Throwable e) throws Throwable {
        Intrinsics.checkNotNullParameter(e, "e");
        throw e;
    }

    public static final boolean isProcessCanceledException(@NotNull Throwable $this$isProcessCanceledException) {
        Intrinsics.checkNotNullParameter($this$isProcessCanceledException, "<this>");
        Class cls = $this$isProcessCanceledException.getClass();
        while (true) {
            Class klass = cls;
            if (Intrinsics.areEqual(klass.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
                return true;
            }
            Class superclass = klass.getSuperclass();
            if (superclass == null) {
                return false;
            }
            cls = superclass;
        }
    }
}
