package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: descriptorBasedTypeSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/TypeMappingConfiguration.class */
public interface TypeMappingConfiguration<T> {
    @NotNull
    KotlinType commonSupertype(@NotNull Collection<KotlinType> collection);

    @Nullable
    T getPredefinedTypeForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    String getPredefinedInternalNameForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    String getPredefinedFullInternalNameForClass(@NotNull ClassDescriptor classDescriptor);

    void processErrorType(@NotNull KotlinType kotlinType, @NotNull ClassDescriptor classDescriptor);

    @Nullable
    KotlinType preprocessType(@NotNull KotlinType kotlinType);

    boolean releaseCoroutines();

    /* compiled from: descriptorBasedTypeSignatureMapping.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/TypeMappingConfiguration$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <T> String getPredefinedFullInternalNameForClass(@NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration, @NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkNotNullParameter(typeMappingConfiguration, "this");
            Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
            return null;
        }

        @Nullable
        public static <T> KotlinType preprocessType(@NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration, @NotNull KotlinType kotlinType) {
            Intrinsics.checkNotNullParameter(typeMappingConfiguration, "this");
            Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
            return null;
        }

        public static <T> boolean releaseCoroutines(@NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration) {
            Intrinsics.checkNotNullParameter(typeMappingConfiguration, "this");
            return true;
        }
    }
}
