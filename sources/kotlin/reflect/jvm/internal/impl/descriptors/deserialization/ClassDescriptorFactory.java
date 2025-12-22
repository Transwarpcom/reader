package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassDescriptorFactory.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/deserialization/ClassDescriptorFactory.class */
public interface ClassDescriptorFactory {
    boolean shouldCreateClass(@NotNull FqName fqName, @NotNull Name name);

    @Nullable
    ClassDescriptor createClass(@NotNull ClassId classId);

    @NotNull
    Collection<ClassDescriptor> getAllContributedClassesIfPossible(@NotNull FqName fqName);
}
