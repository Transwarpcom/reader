package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ErasedOverridabilityCondition.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/ErasedOverridabilityCondition.class */
public final class ErasedOverridabilityCondition implements ExternalOverridabilityCondition {

    /* compiled from: ErasedOverridabilityCondition.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/ErasedOverridabilityCondition$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverridingUtil.OverrideCompatibilityInfo.Result.values().length];
            iArr[OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor subClassDescriptor) {
        boolean z;
        CallableDescriptor callableDescriptorSubstitute;
        Intrinsics.checkNotNullParameter(superDescriptor, "superDescriptor");
        Intrinsics.checkNotNullParameter(subDescriptor, "subDescriptor");
        if (subDescriptor instanceof JavaMethodDescriptor) {
            List<TypeParameterDescriptor> typeParameters = ((JavaMethodDescriptor) subDescriptor).getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(typeParameters, "subDescriptor.typeParameters");
            if (!(!typeParameters.isEmpty())) {
                OverridingUtil.OverrideCompatibilityInfo basicOverridabilityProblem = OverridingUtil.getBasicOverridabilityProblem(superDescriptor, subDescriptor);
                OverridingUtil.OverrideCompatibilityInfo.Result basicOverridability = basicOverridabilityProblem == null ? null : basicOverridabilityProblem.getResult();
                if (basicOverridability != null) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                List<ValueParameterDescriptor> valueParameters = ((JavaMethodDescriptor) subDescriptor).getValueParameters();
                Intrinsics.checkNotNullExpressionValue(valueParameters, "subDescriptor.valueParameters");
                Sequence map = SequencesKt.map(CollectionsKt.asSequence(valueParameters), new Function1<ValueParameterDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition$isOverridable$signatureTypes$1
                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final KotlinType invoke(ValueParameterDescriptor it) {
                        return it.getType();
                    }
                });
                KotlinType returnType = ((JavaMethodDescriptor) subDescriptor).getReturnType();
                Intrinsics.checkNotNull(returnType);
                Sequence sequencePlus = SequencesKt.plus((Sequence<? extends KotlinType>) map, returnType);
                ReceiverParameterDescriptor extensionReceiverParameter = ((JavaMethodDescriptor) subDescriptor).getExtensionReceiverParameter();
                Sequence signatureTypes = SequencesKt.plus(sequencePlus, (Iterable) CollectionsKt.listOfNotNull(extensionReceiverParameter == null ? null : extensionReceiverParameter.getType()));
                Iterator it = signatureTypes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    Object element$iv = it.next();
                    KotlinType it2 = (KotlinType) element$iv;
                    if ((!it2.getArguments().isEmpty()) && !(it2.unwrap() instanceof RawTypeImpl)) {
                        z = true;
                        break;
                    }
                }
                if (!z && (callableDescriptorSubstitute = superDescriptor.substitute(RawSubstitution.INSTANCE.buildSubstitutor())) != null) {
                    CallableDescriptor erasedSuper = callableDescriptorSubstitute;
                    if (erasedSuper instanceof SimpleFunctionDescriptor) {
                        List<TypeParameterDescriptor> typeParameters2 = ((SimpleFunctionDescriptor) erasedSuper).getTypeParameters();
                        Intrinsics.checkNotNullExpressionValue(typeParameters2, "erasedSuper.typeParameters");
                        if (!typeParameters2.isEmpty()) {
                            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) ((SimpleFunctionDescriptor) erasedSuper).newCopyBuilder().setTypeParameters(CollectionsKt.emptyList()).build();
                            Intrinsics.checkNotNull(simpleFunctionDescriptor);
                            erasedSuper = simpleFunctionDescriptor;
                        }
                    }
                    OverridingUtil.OverrideCompatibilityInfo.Result overridabilityResult = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(erasedSuper, subDescriptor, false).getResult();
                    Intrinsics.checkNotNullExpressionValue(overridabilityResult, "DEFAULT.isOverridableByWithoutExternalConditions(erasedSuper, subDescriptor, false).result");
                    return WhenMappings.$EnumSwitchMapping$0[overridabilityResult.ordinal()] == 1 ? ExternalOverridabilityCondition.Result.OVERRIDABLE : ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                return ExternalOverridabilityCondition.Result.UNKNOWN;
            }
        }
        return ExternalOverridabilityCondition.Result.UNKNOWN;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Contract getContract() {
        return ExternalOverridabilityCondition.Contract.SUCCESS_ONLY;
    }
}
