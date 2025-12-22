package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeAliasDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/TypeAliasDescriptor.class */
public interface TypeAliasDescriptor extends ClassifierDescriptorWithTypeParameters {
    @NotNull
    SimpleType getUnderlyingType();

    @NotNull
    SimpleType getExpandedType();

    @Nullable
    ClassDescriptor getClassDescriptor();
}
