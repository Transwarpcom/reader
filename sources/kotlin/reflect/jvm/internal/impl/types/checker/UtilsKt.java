package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: utils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/UtilsKt.class */
public final class UtilsKt {
    @Nullable
    public static final KotlinType findCorrespondingSupertype(@NotNull KotlinType subtype, @NotNull KotlinType supertype, @NotNull TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        boolean z;
        KotlinType kotlinTypeApproximate;
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        Intrinsics.checkNotNullParameter(supertype, "supertype");
        Intrinsics.checkNotNullParameter(typeCheckingProcedureCallbacks, "typeCheckingProcedureCallbacks");
        ArrayDeque queue = new ArrayDeque();
        queue.add(new SubtypePathNode(subtype, null));
        TypeConstructor supertypeConstructor = supertype.getConstructor();
        while (!queue.isEmpty()) {
            SubtypePathNode lastPathNode = (SubtypePathNode) queue.poll();
            KotlinType currentSubtype = lastPathNode.getType();
            TypeConstructor constructor = currentSubtype.getConstructor();
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor, supertypeConstructor)) {
                KotlinType substituted = currentSubtype;
                boolean isAnyMarkedNullable = currentSubtype.isMarkedNullable();
                SubtypePathNode previous = lastPathNode.getPrevious();
                while (true) {
                    SubtypePathNode currentPathNode = previous;
                    if (currentPathNode == null) {
                        break;
                    }
                    KotlinType currentType = currentPathNode.getType();
                    Iterable $this$any$iv = currentType.getArguments();
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        Iterator it = $this$any$iv.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            Object element$iv = it.next();
                            TypeProjection it2 = (TypeProjection) element$iv;
                            if (it2.getProjectionKind() != Variance.INVARIANT) {
                                z = true;
                                break;
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        KotlinType kotlinTypeSafeSubstitute = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(currentType), false, 1, null).buildSubstitutor().safeSubstitute(substituted, Variance.INVARIANT);
                        Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute, "TypeConstructorSubstitution.create(currentType)\n                            .wrapWithCapturingSubstitution().buildSubstitutor()\n                            .safeSubstitute(substituted, Variance.INVARIANT)");
                        kotlinTypeApproximate = approximate(kotlinTypeSafeSubstitute);
                    } else {
                        KotlinType kotlinTypeSafeSubstitute2 = TypeConstructorSubstitution.Companion.create(currentType).buildSubstitutor().safeSubstitute(substituted, Variance.INVARIANT);
                        Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute2, "{\n                    TypeConstructorSubstitution.create(currentType)\n                            .buildSubstitutor()\n                            .safeSubstitute(substituted, Variance.INVARIANT)\n                }");
                        kotlinTypeApproximate = kotlinTypeSafeSubstitute2;
                    }
                    substituted = kotlinTypeApproximate;
                    isAnyMarkedNullable = isAnyMarkedNullable || currentType.isMarkedNullable();
                    previous = currentPathNode.getPrevious();
                }
                TypeConstructor substitutedConstructor = substituted.getConstructor();
                if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(substitutedConstructor, supertypeConstructor)) {
                    return TypeUtils.makeNullableAsSpecified(substituted, isAnyMarkedNullable);
                }
                throw new AssertionError("Type constructors should be equals!\nsubstitutedSuperType: " + debugInfo(substitutedConstructor) + ", \n\nsupertype: " + debugInfo(supertypeConstructor) + " \n" + typeCheckingProcedureCallbacks.assertEqualTypeConstructors(substitutedConstructor, supertypeConstructor));
            }
            for (KotlinType immediateSupertype : constructor.mo3835getSupertypes()) {
                Intrinsics.checkNotNullExpressionValue(immediateSupertype, "immediateSupertype");
                queue.add(new SubtypePathNode(immediateSupertype, lastPathNode));
            }
        }
        return null;
    }

    private static final KotlinType approximate(KotlinType $this$approximate) {
        return CapturedTypeApproximationKt.approximateCapturedTypes($this$approximate).getUpper();
    }

    private static final String debugInfo(TypeConstructor $this$debugInfo) {
        StringBuilder $this$debugInfo_u24lambda_u2d1 = new StringBuilder();
        m3942debugInfo$lambda1$unaryPlus(Intrinsics.stringPlus("type: ", $this$debugInfo), $this$debugInfo_u24lambda_u2d1);
        m3942debugInfo$lambda1$unaryPlus(Intrinsics.stringPlus("hashCode: ", Integer.valueOf($this$debugInfo.hashCode())), $this$debugInfo_u24lambda_u2d1);
        m3942debugInfo$lambda1$unaryPlus(Intrinsics.stringPlus("javaClass: ", $this$debugInfo.getClass().getCanonicalName()), $this$debugInfo_u24lambda_u2d1);
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$debugInfo.mo3831getDeclarationDescriptor();
        while (true) {
            DeclarationDescriptor declarationDescriptor = classifierDescriptorMo3831getDeclarationDescriptor;
            if (declarationDescriptor == null) {
                String string = $this$debugInfo_u24lambda_u2d1.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
            m3942debugInfo$lambda1$unaryPlus(Intrinsics.stringPlus("fqName: ", DescriptorRenderer.FQ_NAMES_IN_TYPES.render(declarationDescriptor)), $this$debugInfo_u24lambda_u2d1);
            m3942debugInfo$lambda1$unaryPlus(Intrinsics.stringPlus("javaClass: ", declarationDescriptor.getClass().getCanonicalName()), $this$debugInfo_u24lambda_u2d1);
            classifierDescriptorMo3831getDeclarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
    }

    /* renamed from: debugInfo$lambda-1$unaryPlus, reason: not valid java name */
    private static final StringBuilder m3942debugInfo$lambda1$unaryPlus(String $this$debugInfo_u24lambda_u2d1_u24unaryPlus, StringBuilder $this_buildString) {
        Intrinsics.checkNotNullParameter($this$debugInfo_u24lambda_u2d1_u24unaryPlus, "<this>");
        StringBuilder sbAppend = $this_buildString.append($this$debugInfo_u24lambda_u2d1_u24unaryPlus);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }
}
