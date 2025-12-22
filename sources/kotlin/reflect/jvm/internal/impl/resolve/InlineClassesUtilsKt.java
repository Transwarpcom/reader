package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: inlineClassesUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/InlineClassesUtilsKt.class */
public final class InlineClassesUtilsKt {

    @NotNull
    private static final FqName JVM_INLINE_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmInline");

    @Nullable
    public static final ValueParameterDescriptor underlyingRepresentation(@NotNull ClassDescriptor $this$underlyingRepresentation) {
        ClassConstructorDescriptor classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        Intrinsics.checkNotNullParameter($this$underlyingRepresentation, "<this>");
        if (!isInlineClass($this$underlyingRepresentation) || (classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor = $this$underlyingRepresentation.mo3498getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = classConstructorDescriptorMo3498getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            return null;
        }
        return (ValueParameterDescriptor) CollectionsKt.singleOrNull((List) valueParameters);
    }

    public static final boolean isInlineClass(@NotNull DeclarationDescriptor $this$isInlineClass) {
        Intrinsics.checkNotNullParameter($this$isInlineClass, "<this>");
        return ($this$isInlineClass instanceof ClassDescriptor) && (((ClassDescriptor) $this$isInlineClass).isInline() || ((ClassDescriptor) $this$isInlineClass).isValue());
    }

    @Nullable
    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(@NotNull KotlinType $this$unsubstitutedUnderlyingParameter) {
        Intrinsics.checkNotNullParameter($this$unsubstitutedUnderlyingParameter, "<this>");
        Object $this$safeAs$iv = $this$unsubstitutedUnderlyingParameter.getConstructor().mo3831getDeclarationDescriptor();
        Object obj = $this$safeAs$iv;
        if (!(obj instanceof ClassDescriptor)) {
            obj = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) obj;
        if (classDescriptor == null) {
            return null;
        }
        return underlyingRepresentation(classDescriptor);
    }

    public static final boolean isInlineClassType(@NotNull KotlinType $this$isInlineClassType) {
        Intrinsics.checkNotNullParameter($this$isInlineClassType, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$isInlineClassType.getConstructor().mo3831getDeclarationDescriptor();
        if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
            return false;
        }
        return isInlineClass(classifierDescriptorMo3831getDeclarationDescriptor);
    }

    @Nullable
    public static final KotlinType substitutedUnderlyingType(@NotNull KotlinType $this$substitutedUnderlyingType) {
        Intrinsics.checkNotNullParameter($this$substitutedUnderlyingType, "<this>");
        ValueParameterDescriptor parameter = unsubstitutedUnderlyingParameter($this$substitutedUnderlyingType);
        if (parameter == null) {
            return null;
        }
        return TypeSubstitutor.create($this$substitutedUnderlyingType).substitute(parameter.getType(), Variance.INVARIANT);
    }

    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(@NotNull CallableDescriptor $this$isGetterOfUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkNotNullParameter($this$isGetterOfUnderlyingPropertyOfInlineClass, "<this>");
        if ($this$isGetterOfUnderlyingPropertyOfInlineClass instanceof PropertyGetterDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyGetterDescriptor) $this$isGetterOfUnderlyingPropertyOfInlineClass).getCorrespondingProperty();
            Intrinsics.checkNotNullExpressionValue(correspondingProperty, "correspondingProperty");
            if (isUnderlyingPropertyOfInlineClass(correspondingProperty)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(@NotNull VariableDescriptor $this$isUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkNotNullParameter($this$isUnderlyingPropertyOfInlineClass, "<this>");
        if ($this$isUnderlyingPropertyOfInlineClass.getExtensionReceiverParameter() != null) {
            return false;
        }
        DeclarationDescriptor containingDeclaration = $this$isUnderlyingPropertyOfInlineClass.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "this.containingDeclaration");
        if (!isInlineClass(containingDeclaration)) {
            return false;
        }
        ValueParameterDescriptor valueParameterDescriptorUnderlyingRepresentation = underlyingRepresentation((ClassDescriptor) containingDeclaration);
        return Intrinsics.areEqual(valueParameterDescriptorUnderlyingRepresentation == null ? null : valueParameterDescriptorUnderlyingRepresentation.getName(), $this$isUnderlyingPropertyOfInlineClass.getName());
    }
}
