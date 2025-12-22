package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReflectJavaArrayType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaArrayType.class */
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {

    @NotNull
    private final Type reflectType;

    @NotNull
    private final ReflectJavaType componentType;

    @NotNull
    private final Collection<JavaAnnotation> annotations;
    private final boolean isDeprecatedInJavaDoc;

    public ReflectJavaArrayType(@NotNull Type reflectType) {
        ReflectJavaType reflectJavaTypeCreate;
        Intrinsics.checkNotNullParameter(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type $this$componentType_u24lambda_u2d0 = getReflectType();
        if ($this$componentType_u24lambda_u2d0 instanceof GenericArrayType) {
            ReflectJavaType.Factory factory = ReflectJavaType.Factory;
            Type genericComponentType = ((GenericArrayType) $this$componentType_u24lambda_u2d0).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "genericComponentType");
            reflectJavaTypeCreate = factory.create(genericComponentType);
        } else {
            if (!($this$componentType_u24lambda_u2d0 instanceof Class) || !((Class) $this$componentType_u24lambda_u2d0).isArray()) {
                throw new IllegalArgumentException("Not an array type (" + getReflectType().getClass() + "): " + getReflectType());
            }
            ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
            Class<?> componentType = ((Class) $this$componentType_u24lambda_u2d0).getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType()");
            reflectJavaTypeCreate = factory2.create(componentType);
        }
        this.componentType = reflectJavaTypeCreate;
        this.annotations = CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    @NotNull
    protected Type getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    @NotNull
    public ReflectJavaType getComponentType() {
        return this.componentType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    public Collection<JavaAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }
}
