package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/PropertyAccessorDescriptor.class */
public interface PropertyAccessorDescriptor extends VariableAccessorDescriptor {
    boolean isDefault();

    @NotNull
    PropertyDescriptor getCorrespondingProperty();
}
