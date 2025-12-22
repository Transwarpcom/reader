package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor.class */
public interface AnnotationDescriptor {
    @NotNull
    KotlinType getType();

    @Nullable
    FqName getFqName();

    @NotNull
    Map<Name, ConstantValue<?>> getAllValueArguments();

    @NotNull
    SourceElement getSource();

    /* compiled from: AnnotationDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static FqName getFqName(@NotNull AnnotationDescriptor annotationDescriptor) {
            Intrinsics.checkNotNullParameter(annotationDescriptor, "this");
            DeclarationDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
            if (annotationClass == null) {
                return null;
            }
            DeclarationDescriptor p0 = annotationClass;
            DeclarationDescriptor declarationDescriptor = !ErrorUtils.isError(p0) ? annotationClass : null;
            if (declarationDescriptor == null) {
                return null;
            }
            return DescriptorUtilsKt.fqNameOrNull(declarationDescriptor);
        }
    }
}
