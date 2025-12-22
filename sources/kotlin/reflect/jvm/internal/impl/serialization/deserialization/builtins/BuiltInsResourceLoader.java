package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BuiltInsResourceLoader.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/builtins/BuiltInsResourceLoader.class */
public final class BuiltInsResourceLoader {
    @Nullable
    public final InputStream loadResource(@NotNull String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resourceAsStream = classLoader == null ? null : classLoader.getResourceAsStream(path);
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        return ClassLoader.getSystemResourceAsStream(path);
    }
}
