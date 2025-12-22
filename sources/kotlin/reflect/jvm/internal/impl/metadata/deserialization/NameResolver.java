package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import org.jetbrains.annotations.NotNull;

/* compiled from: NameResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver.class */
public interface NameResolver {
    @NotNull
    String getString(int i);

    @NotNull
    String getQualifiedClassName(int i);

    boolean isLocalClassName(int i);
}
