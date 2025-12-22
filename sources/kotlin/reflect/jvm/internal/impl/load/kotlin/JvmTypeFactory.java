package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/JvmTypeFactory.class */
public interface JvmTypeFactory<T> {
    @NotNull
    T boxType(@NotNull T t);

    @NotNull
    T createFromString(@NotNull String str);

    @NotNull
    T createPrimitiveType(@NotNull PrimitiveType primitiveType);

    @NotNull
    T createObjectType(@NotNull String str);

    @NotNull
    String toString(@NotNull T t);

    @NotNull
    T getJavaLangClassType();
}
