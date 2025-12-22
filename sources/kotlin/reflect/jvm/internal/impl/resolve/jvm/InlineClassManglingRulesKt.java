package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: inlineClassManglingRules.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/jvm/InlineClassManglingRulesKt.class */
public final class InlineClassManglingRulesKt {
    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(@NotNull CallableMemberDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ClassConstructorDescriptor constructorDescriptor = descriptor instanceof ClassConstructorDescriptor ? (ClassConstructorDescriptor) descriptor : null;
        if (constructorDescriptor == null || DescriptorVisibilities.isPrivate(constructorDescriptor.getVisibility())) {
            return false;
        }
        ClassDescriptor constructedClass = constructorDescriptor.getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(constructedClass, "constructorDescriptor.constructedClass");
        if (InlineClassesUtilsKt.isInlineClass(constructedClass) || DescriptorUtils.isSealedClass(constructorDescriptor.getConstructedClass())) {
            return false;
        }
        Iterable valueParameters = constructorDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "constructorDescriptor.valueParameters");
        Iterable $this$any$iv = valueParameters;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            ValueParameterDescriptor it = (ValueParameterDescriptor) element$iv;
            KotlinType type = it.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            if (requiresFunctionNameManglingInParameterTypes(type)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(@NotNull DeclarationDescriptor $this$isInlineClassThatRequiresMangling) {
        Intrinsics.checkNotNullParameter($this$isInlineClassThatRequiresMangling, "<this>");
        return InlineClassesUtilsKt.isInlineClass($this$isInlineClassThatRequiresMangling) && !isDontMangleClass((ClassDescriptor) $this$isInlineClassThatRequiresMangling);
    }

    public static final boolean isInlineClassThatRequiresMangling(@NotNull KotlinType $this$isInlineClassThatRequiresMangling) {
        Intrinsics.checkNotNullParameter($this$isInlineClassThatRequiresMangling, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isInlineClassThatRequiresMangling.getConstructor().mo3831getDeclarationDescriptor();
        return Intrinsics.areEqual((Object) (classifierDescriptorMo3831getDeclarationDescriptor == null ? null : Boolean.valueOf(isInlineClassThatRequiresMangling(classifierDescriptorMo3831getDeclarationDescriptor))), (Object) true);
    }

    private static final boolean requiresFunctionNameManglingInParameterTypes(KotlinType $this$requiresFunctionNameManglingInParameterTypes) {
        return isInlineClassThatRequiresMangling($this$requiresFunctionNameManglingInParameterTypes) || isTypeParameterWithUpperBoundThatRequiresMangling($this$requiresFunctionNameManglingInParameterTypes);
    }

    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), StandardNames.RESULT_FQ_NAME);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType $this$isTypeParameterWithUpperBoundThatRequiresMangling) {
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isTypeParameterWithUpperBoundThatRequiresMangling.getConstructor().mo3831getDeclarationDescriptor();
        TypeParameterDescriptor descriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
        if (descriptor == null) {
            return false;
        }
        return requiresFunctionNameManglingInParameterTypes(TypeUtilsKt.getRepresentativeUpperBound(descriptor));
    }
}
