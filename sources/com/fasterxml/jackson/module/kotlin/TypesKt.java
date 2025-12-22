package com.fasterxml.jackson.module.kotlin;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.KTypesJvm;
import org.jetbrains.annotations.NotNull;

/* compiled from: Types.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\u0012\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H��\u001a\u0014\u0010��\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001*\u00020\u0004H��¨\u0006\u0005"}, d2 = {"erasedType", "Ljava/lang/Class;", "", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/TypesKt.class */
public final class TypesKt {
    @NotNull
    public static final Class<Object> erasedType(@NotNull Type $this$erasedType) throws NegativeArraySizeException {
        Intrinsics.checkNotNullParameter($this$erasedType, "<this>");
        if ($this$erasedType instanceof Class) {
            return (Class) $this$erasedType;
        }
        if ($this$erasedType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) $this$erasedType).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "this.getRawType()");
            return erasedType(rawType);
        }
        if ($this$erasedType instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) $this$erasedType).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "this.getGenericComponentType()");
            Class elementType = erasedType(genericComponentType);
            Object testArray = Array.newInstance((Class<?>) elementType, 0);
            Intrinsics.checkNotNullExpressionValue(testArray, "testArray");
            return testArray.getClass();
        }
        if ($this$erasedType instanceof TypeVariable) {
            throw new IllegalStateException("Not sure what to do here yet");
        }
        if ($this$erasedType instanceof WildcardType) {
            Type type = ((WildcardType) $this$erasedType).getUpperBounds()[0];
            Intrinsics.checkNotNullExpressionValue(type, "this.getUpperBounds()[0]");
            return erasedType(type);
        }
        throw new IllegalStateException("Should not get here.");
    }

    @NotNull
    public static final Class<? extends Object> erasedType(@NotNull KType $this$erasedType) {
        Intrinsics.checkNotNullParameter($this$erasedType, "<this>");
        return JvmClassMappingKt.getJavaClass((KClass) KTypesJvm.getJvmErasure($this$erasedType));
    }
}
