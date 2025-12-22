package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJavaType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaType.class */
public abstract class ReflectJavaType implements JavaType {

    @NotNull
    public static final Factory Factory = new Factory(null);

    @NotNull
    protected abstract Type getReflectType();

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    public JavaAnnotation findAnnotation(@NotNull FqName fqName) {
        return JavaType.DefaultImpls.findAnnotation(this, fqName);
    }

    /* compiled from: ReflectJavaType.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaType$Factory.class */
    public static final class Factory {
        public /* synthetic */ Factory(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Factory() {
        }

        @NotNull
        public final ReflectJavaType create(@NotNull Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return ((type instanceof Class) && ((Class) type).isPrimitive()) ? new ReflectJavaPrimitiveType((Class) type) : ((type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray())) ? new ReflectJavaArrayType(type) : type instanceof WildcardType ? new ReflectJavaWildcardType((WildcardType) type) : new ReflectJavaClassifierType(type);
        }
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof ReflectJavaType) && Intrinsics.areEqual(getReflectType(), ((ReflectJavaType) other).getReflectType());
    }

    public int hashCode() {
        return getReflectType().hashCode();
    }

    @NotNull
    public String toString() {
        return getClass().getName() + ": " + getReflectType();
    }
}
