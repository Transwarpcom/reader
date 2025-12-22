package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerImpl;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil.class */
public class OverridingUtil {
    private static final List<ExternalOverridabilityCondition> EXTERNAL_CONDITIONS;
    public static final OverridingUtil DEFAULT;
    private static final KotlinTypeChecker.TypeConstructorEquality DEFAULT_TYPE_CONSTRUCTOR_EQUALITY;
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final KotlinTypeChecker.TypeConstructorEquality equalityAxioms;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            case 21:
            case 28:
            case 29:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 93:
            case 94:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 102:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 9:
            case 10:
            case 14:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 44:
            case 45:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 90:
            case 91:
            case 92:
            case 95:
            case 98:
            case 103:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            case 21:
            case 28:
            case 29:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 93:
            case 94:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 102:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            default:
                i2 = 3;
                break;
            case 9:
            case 10:
            case 14:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 44:
            case 45:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 90:
            case 91:
            case 92:
            case 95:
            case 98:
            case 103:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 3:
            default:
                objArr[0] = "equalityAxioms";
                break;
            case 1:
            case 2:
            case 5:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 4:
                objArr[0] = "axioms";
                break;
            case 6:
            case 7:
                objArr[0] = "candidateSet";
                break;
            case 8:
                objArr[0] = "transformFirst";
                break;
            case 9:
            case 10:
            case 14:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 44:
            case 45:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 90:
            case 91:
            case 92:
            case 95:
            case 98:
            case 103:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                break;
            case 11:
                objArr[0] = OperatorName.FILL_NON_ZERO;
                break;
            case 12:
                objArr[0] = OperatorName.NON_STROKING_GRAY;
                break;
            case 13:
            case 15:
                objArr[0] = "descriptor";
                break;
            case 16:
                objArr[0] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                break;
            case 17:
            case 20:
            case 28:
            case 38:
                objArr[0] = "superDescriptor";
                break;
            case 18:
            case 21:
            case 29:
            case 39:
                objArr[0] = "subDescriptor";
                break;
            case 40:
            case 42:
                objArr[0] = "firstParameters";
                break;
            case 41:
            case 43:
                objArr[0] = "secondParameters";
                break;
            case 46:
                objArr[0] = "typeInSuper";
                break;
            case 47:
                objArr[0] = "typeInSub";
                break;
            case 48:
            case 51:
            case 77:
                objArr[0] = "typeChecker";
                break;
            case 49:
                objArr[0] = "superTypeParameter";
                break;
            case 50:
                objArr[0] = "subTypeParameter";
                break;
            case 52:
                objArr[0] = "name";
                break;
            case 53:
                objArr[0] = "membersFromSupertypes";
                break;
            case 54:
                objArr[0] = "membersFromCurrent";
                break;
            case 55:
            case 61:
            case 64:
            case 86:
            case 89:
            case 96:
                objArr[0] = "current";
                break;
            case 56:
            case 62:
            case 66:
            case 87:
            case 106:
                objArr[0] = "strategy";
                break;
            case 57:
                objArr[0] = "overriding";
                break;
            case 58:
                objArr[0] = "fromSuper";
                break;
            case 59:
                objArr[0] = "fromCurrent";
                break;
            case 60:
                objArr[0] = "descriptorsFromSuper";
                break;
            case 63:
            case 65:
                objArr[0] = "notOverridden";
                break;
            case 67:
            case 69:
            case 73:
                objArr[0] = "a";
                break;
            case 68:
            case 70:
            case 75:
                objArr[0] = OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE;
                break;
            case 71:
                objArr[0] = "candidate";
                break;
            case 72:
            case 88:
            case 93:
            case 109:
                objArr[0] = "descriptors";
                break;
            case 74:
                objArr[0] = "aReturnType";
                break;
            case 76:
                objArr[0] = "bReturnType";
                break;
            case 78:
            case 85:
                objArr[0] = "overridables";
                break;
            case 79:
            case 101:
                objArr[0] = "descriptorByHandle";
                break;
            case 94:
                objArr[0] = "classModality";
                break;
            case 97:
                objArr[0] = "toFilter";
                break;
            case 99:
            case 104:
                objArr[0] = "overrider";
                break;
            case 100:
            case 105:
                objArr[0] = "extractFrom";
                break;
            case 102:
                objArr[0] = "onConflict";
                break;
            case 107:
            case 108:
                objArr[0] = "memberDescriptor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            case 21:
            case 28:
            case 29:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 93:
            case 94:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 102:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                break;
            case 9:
            case 10:
                objArr[1] = "filterOverrides";
                break;
            case 14:
                objArr[1] = "getOverriddenDeclarations";
                break;
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                objArr[1] = "isOverridableBy";
                break;
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
                objArr[1] = "isOverridableByWithoutExternalConditions";
                break;
            case 44:
            case 45:
                objArr[1] = "createTypeCheckerContext";
                break;
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
                objArr[1] = "selectMostSpecificMember";
                break;
            case 90:
            case 91:
            case 92:
                objArr[1] = "determineModalityForFakeOverride";
                break;
            case 95:
                objArr[1] = "getMinimalModality";
                break;
            case 98:
                objArr[1] = "filterVisibleFakeOverrides";
                break;
            case 103:
                objArr[1] = "extractMembersOverridableInBothWays";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "createWithEqualityAxioms";
                break;
            case 1:
                objArr[2] = "createWithTypeRefiner";
                break;
            case 2:
            case 3:
                objArr[2] = "create";
                break;
            case 4:
            case 5:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 6:
                objArr[2] = "filterOutOverridden";
                break;
            case 7:
            case 8:
                objArr[2] = "filterOverrides";
                break;
            case 9:
            case 10:
            case 14:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 44:
            case 45:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 90:
            case 91:
            case 92:
            case 95:
            case 98:
            case 103:
                break;
            case 11:
            case 12:
                objArr[2] = "overrides";
                break;
            case 13:
                objArr[2] = "getOverriddenDeclarations";
                break;
            case 15:
            case 16:
                objArr[2] = "collectOverriddenDeclarations";
                break;
            case 17:
            case 18:
            case 20:
            case 21:
                objArr[2] = "isOverridableBy";
                break;
            case 28:
            case 29:
                objArr[2] = "isOverridableByWithoutExternalConditions";
                break;
            case 38:
            case 39:
                objArr[2] = "getBasicOverridabilityProblem";
                break;
            case 40:
            case 41:
                objArr[2] = "createTypeChecker";
                break;
            case 42:
            case 43:
                objArr[2] = "createTypeCheckerContext";
                break;
            case 46:
            case 47:
            case 48:
                objArr[2] = "areTypesEquivalent";
                break;
            case 49:
            case 50:
            case 51:
                objArr[2] = "areTypeParametersEquivalent";
                break;
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
                objArr[2] = "generateOverridesInFunctionGroup";
                break;
            case 57:
            case 58:
                objArr[2] = "isVisibleForOverride";
                break;
            case 59:
            case 60:
            case 61:
            case 62:
                objArr[2] = "extractAndBindOverridesForMember";
                break;
            case 63:
                objArr[2] = "allHasSameContainingDeclaration";
                break;
            case 64:
            case 65:
            case 66:
                objArr[2] = "createAndBindFakeOverrides";
                break;
            case 67:
            case 68:
                objArr[2] = "isMoreSpecific";
                break;
            case 69:
            case 70:
                objArr[2] = "isVisibilityMoreSpecific";
                break;
            case 71:
            case 72:
                objArr[2] = "isMoreSpecificThenAllOf";
                break;
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
                objArr[2] = "isReturnTypeMoreSpecific";
                break;
            case 78:
            case 79:
                objArr[2] = "selectMostSpecificMember";
                break;
            case 85:
            case 86:
            case 87:
                objArr[2] = "createAndBindFakeOverride";
                break;
            case 88:
            case 89:
                objArr[2] = "determineModalityForFakeOverride";
                break;
            case 93:
            case 94:
                objArr[2] = "getMinimalModality";
                break;
            case 96:
            case 97:
                objArr[2] = "filterVisibleFakeOverrides";
                break;
            case 99:
            case 100:
            case 101:
            case 102:
            case 104:
            case 105:
            case 106:
                objArr[2] = "extractMembersOverridableInBothWays";
                break;
            case 107:
                objArr[2] = "resolveUnknownVisibilityForMember";
                break;
            case 108:
                objArr[2] = "computeVisibilityToInherit";
                break;
            case 109:
                objArr[2] = "findMaxVisibility";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            case 21:
            case 28:
            case 29:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 93:
            case 94:
            case 96:
            case 97:
            case 99:
            case 100:
            case 101:
            case 102:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            default:
                throw new IllegalArgumentException(str2);
            case 9:
            case 10:
            case 14:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 44:
            case 45:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 90:
            case 91:
            case 92:
            case 95:
            case 98:
            case 103:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !OverridingUtil.class.desiredAssertionStatus();
        EXTERNAL_CONDITIONS = CollectionsKt.toList(ServiceLoader.load(ExternalOverridabilityCondition.class, ExternalOverridabilityCondition.class.getClassLoader()));
        DEFAULT_TYPE_CONSTRUCTOR_EQUALITY = new KotlinTypeChecker.TypeConstructorEquality() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                switch (i) {
                    case 0:
                    default:
                        objArr[0] = "a";
                        break;
                    case 1:
                        objArr[0] = OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE;
                        break;
                }
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
                objArr[2] = "equals";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality
            public boolean equals(@NotNull TypeConstructor a, @NotNull TypeConstructor b) {
                if (a == null) {
                    $$$reportNull$$$0(0);
                }
                if (b == null) {
                    $$$reportNull$$$0(1);
                }
                return a.equals(b);
            }
        };
        DEFAULT = new OverridingUtil(DEFAULT_TYPE_CONSTRUCTOR_EQUALITY, KotlinTypeRefiner.Default.INSTANCE);
    }

    @NotNull
    public static OverridingUtil createWithTypeRefiner(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(1);
        }
        return new OverridingUtil(DEFAULT_TYPE_CONSTRUCTOR_EQUALITY, kotlinTypeRefiner);
    }

    @NotNull
    public static OverridingUtil create(@NotNull KotlinTypeRefiner kotlinTypeRefiner, @NotNull KotlinTypeChecker.TypeConstructorEquality equalityAxioms) {
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(2);
        }
        if (equalityAxioms == null) {
            $$$reportNull$$$0(3);
        }
        return new OverridingUtil(equalityAxioms, kotlinTypeRefiner);
    }

    private OverridingUtil(@NotNull KotlinTypeChecker.TypeConstructorEquality axioms, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        if (axioms == null) {
            $$$reportNull$$$0(4);
        }
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(5);
        }
        this.equalityAxioms = axioms;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @NotNull
    public static <D extends CallableDescriptor> Set<D> filterOutOverridden(@NotNull Set<D> candidateSet) {
        if (candidateSet == null) {
            $$$reportNull$$$0(6);
        }
        boolean allowDescriptorCopies = !candidateSet.isEmpty() && DescriptorUtilsKt.isTypeRefinementEnabled(DescriptorUtilsKt.getModule(candidateSet.iterator().next()));
        return filterOverrides(candidateSet, allowDescriptorCopies, null, new Function2<D, D, Pair<CallableDescriptor, CallableDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.2
            /* JADX WARN: Incorrect types in method signature: (TD;TD;)Lkotlin/Pair<Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;>; */
            @Override // kotlin.jvm.functions.Function2
            public Pair invoke(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
                return new Pair(callableDescriptor, callableDescriptor2);
            }
        });
    }

    @NotNull
    public static <D> Set<D> filterOverrides(@NotNull Set<D> set, boolean z, @Nullable Function0<?> function0, @NotNull Function2<? super D, ? super D, Pair<CallableDescriptor, CallableDescriptor>> function2) {
        if (set == null) {
            $$$reportNull$$$0(7);
        }
        if (function2 == null) {
            $$$reportNull$$$0(8);
        }
        if (set.size() <= 1) {
            if (set == null) {
                $$$reportNull$$$0(9);
            }
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : set) {
            if (function0 != null) {
                function0.invoke();
            }
            Iterator it = linkedHashSet.iterator();
            while (true) {
                if (it.hasNext()) {
                    Pair<CallableDescriptor, CallableDescriptor> pairInvoke = function2.invoke(obj, (D) it.next());
                    CallableDescriptor callableDescriptorComponent1 = pairInvoke.component1();
                    CallableDescriptor callableDescriptorComponent2 = pairInvoke.component2();
                    if (overrides(callableDescriptorComponent1, callableDescriptorComponent2, z, true)) {
                        it.remove();
                    } else if (overrides(callableDescriptorComponent2, callableDescriptorComponent1, z, true)) {
                        break;
                    }
                } else {
                    linkedHashSet.add(obj);
                    break;
                }
            }
        }
        if (!$assertionsDisabled && linkedHashSet.isEmpty()) {
            throw new AssertionError("All candidates filtered out from " + set);
        }
        if (linkedHashSet == null) {
            $$$reportNull$$$0(10);
        }
        return linkedHashSet;
    }

    public static <D extends CallableDescriptor> boolean overrides(@NotNull D f, @NotNull D g, boolean allowDeclarationCopies, boolean distinguishExpectsAndNonExpects) {
        if (f == null) {
            $$$reportNull$$$0(11);
        }
        if (g == null) {
            $$$reportNull$$$0(12);
        }
        if (!f.equals(g) && DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(f.getOriginal(), g.getOriginal(), allowDeclarationCopies, distinguishExpectsAndNonExpects)) {
            return true;
        }
        CallableDescriptor originalG = g.getOriginal();
        Iterator i$ = DescriptorUtils.getAllOverriddenDescriptors(f).iterator();
        while (i$.hasNext()) {
            if (DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(originalG, (CallableDescriptor) i$.next(), allowDeclarationCopies, distinguishExpectsAndNonExpects)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static Set<CallableMemberDescriptor> getOverriddenDeclarations(@NotNull CallableMemberDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(13);
        }
        Set<CallableMemberDescriptor> result = new LinkedHashSet<>();
        collectOverriddenDeclarations(descriptor, result);
        if (result == null) {
            $$$reportNull$$$0(14);
        }
        return result;
    }

    private static void collectOverriddenDeclarations(@NotNull CallableMemberDescriptor descriptor, @NotNull Set<CallableMemberDescriptor> result) {
        if (descriptor == null) {
            $$$reportNull$$$0(15);
        }
        if (result == null) {
            $$$reportNull$$$0(16);
        }
        if (descriptor.getKind().isReal()) {
            result.add(descriptor);
        } else {
            if (descriptor.getOverriddenDescriptors().isEmpty()) {
                throw new IllegalStateException("No overridden descriptors found for (fake override) " + descriptor);
            }
            for (CallableMemberDescriptor overridden : descriptor.getOverriddenDescriptors()) {
                collectOverriddenDeclarations(overridden, result);
            }
        }
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableBy(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor subClassDescriptor) {
        if (superDescriptor == null) {
            $$$reportNull$$$0(17);
        }
        if (subDescriptor == null) {
            $$$reportNull$$$0(18);
        }
        OverrideCompatibilityInfo overrideCompatibilityInfoIsOverridableBy = isOverridableBy(superDescriptor, subDescriptor, subClassDescriptor, false);
        if (overrideCompatibilityInfoIsOverridableBy == null) {
            $$$reportNull$$$0(19);
        }
        return overrideCompatibilityInfoIsOverridableBy;
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableBy(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor subClassDescriptor, boolean checkReturnType) {
        if (superDescriptor == null) {
            $$$reportNull$$$0(20);
        }
        if (subDescriptor == null) {
            $$$reportNull$$$0(21);
        }
        OverrideCompatibilityInfo basicResult = isOverridableByWithoutExternalConditions(superDescriptor, subDescriptor, checkReturnType);
        boolean wasSuccess = basicResult.getResult() == OverrideCompatibilityInfo.Result.OVERRIDABLE;
        for (ExternalOverridabilityCondition externalCondition : EXTERNAL_CONDITIONS) {
            if (externalCondition.getContract() != ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY && (!wasSuccess || externalCondition.getContract() != ExternalOverridabilityCondition.Contract.SUCCESS_ONLY)) {
                ExternalOverridabilityCondition.Result result = externalCondition.isOverridable(superDescriptor, subDescriptor, subClassDescriptor);
                switch (result) {
                    case OVERRIDABLE:
                        wasSuccess = true;
                        break;
                    case CONFLICT:
                        OverrideCompatibilityInfo overrideCompatibilityInfoConflict = OverrideCompatibilityInfo.conflict("External condition failed");
                        if (overrideCompatibilityInfoConflict == null) {
                            $$$reportNull$$$0(22);
                        }
                        return overrideCompatibilityInfoConflict;
                    case INCOMPATIBLE:
                        OverrideCompatibilityInfo overrideCompatibilityInfoIncompatible = OverrideCompatibilityInfo.incompatible("External condition");
                        if (overrideCompatibilityInfoIncompatible == null) {
                            $$$reportNull$$$0(23);
                        }
                        return overrideCompatibilityInfoIncompatible;
                }
            }
        }
        if (!wasSuccess) {
            if (basicResult == null) {
                $$$reportNull$$$0(24);
            }
            return basicResult;
        }
        for (ExternalOverridabilityCondition externalCondition2 : EXTERNAL_CONDITIONS) {
            if (externalCondition2.getContract() == ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY) {
                ExternalOverridabilityCondition.Result result2 = externalCondition2.isOverridable(superDescriptor, subDescriptor, subClassDescriptor);
                switch (result2) {
                    case OVERRIDABLE:
                        throw new IllegalStateException("Contract violation in " + externalCondition2.getClass().getName() + " condition. It's not supposed to end with success");
                    case CONFLICT:
                        OverrideCompatibilityInfo overrideCompatibilityInfoConflict2 = OverrideCompatibilityInfo.conflict("External condition failed");
                        if (overrideCompatibilityInfoConflict2 == null) {
                            $$$reportNull$$$0(25);
                        }
                        return overrideCompatibilityInfoConflict2;
                    case INCOMPATIBLE:
                        OverrideCompatibilityInfo overrideCompatibilityInfoIncompatible2 = OverrideCompatibilityInfo.incompatible("External condition");
                        if (overrideCompatibilityInfoIncompatible2 == null) {
                            $$$reportNull$$$0(26);
                        }
                        return overrideCompatibilityInfoIncompatible2;
                }
            }
        }
        OverrideCompatibilityInfo overrideCompatibilityInfoSuccess = OverrideCompatibilityInfo.success();
        if (overrideCompatibilityInfoSuccess == null) {
            $$$reportNull$$$0(27);
        }
        return overrideCompatibilityInfoSuccess;
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableByWithoutExternalConditions(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, boolean checkReturnType) {
        if (superDescriptor == null) {
            $$$reportNull$$$0(28);
        }
        if (subDescriptor == null) {
            $$$reportNull$$$0(29);
        }
        OverrideCompatibilityInfo basicOverridability = getBasicOverridabilityProblem(superDescriptor, subDescriptor);
        if (basicOverridability != null) {
            if (basicOverridability == null) {
                $$$reportNull$$$0(30);
            }
            return basicOverridability;
        }
        List<KotlinType> superValueParameters = compiledValueParameters(superDescriptor);
        List<KotlinType> subValueParameters = compiledValueParameters(subDescriptor);
        List<TypeParameterDescriptor> superTypeParameters = superDescriptor.getTypeParameters();
        List<TypeParameterDescriptor> subTypeParameters = subDescriptor.getTypeParameters();
        if (superTypeParameters.size() != subTypeParameters.size()) {
            for (int i = 0; i < superValueParameters.size(); i++) {
                if (!KotlinTypeChecker.DEFAULT.equalTypes(superValueParameters.get(i), subValueParameters.get(i))) {
                    OverrideCompatibilityInfo overrideCompatibilityInfoIncompatible = OverrideCompatibilityInfo.incompatible("Type parameter number mismatch");
                    if (overrideCompatibilityInfoIncompatible == null) {
                        $$$reportNull$$$0(31);
                    }
                    return overrideCompatibilityInfoIncompatible;
                }
            }
            OverrideCompatibilityInfo overrideCompatibilityInfoConflict = OverrideCompatibilityInfo.conflict("Type parameter number mismatch");
            if (overrideCompatibilityInfoConflict == null) {
                $$$reportNull$$$0(32);
            }
            return overrideCompatibilityInfoConflict;
        }
        Pair<NewKotlinTypeCheckerImpl, ClassicTypeCheckerContext> typeChecker = createTypeChecker(superTypeParameters, subTypeParameters);
        for (int i2 = 0; i2 < superTypeParameters.size(); i2++) {
            if (!areTypeParametersEquivalent(superTypeParameters.get(i2), subTypeParameters.get(i2), typeChecker)) {
                OverrideCompatibilityInfo overrideCompatibilityInfoIncompatible2 = OverrideCompatibilityInfo.incompatible("Type parameter bounds mismatch");
                if (overrideCompatibilityInfoIncompatible2 == null) {
                    $$$reportNull$$$0(33);
                }
                return overrideCompatibilityInfoIncompatible2;
            }
        }
        for (int i3 = 0; i3 < superValueParameters.size(); i3++) {
            if (!areTypesEquivalent(superValueParameters.get(i3), subValueParameters.get(i3), typeChecker)) {
                OverrideCompatibilityInfo overrideCompatibilityInfoIncompatible3 = OverrideCompatibilityInfo.incompatible("Value parameter type mismatch");
                if (overrideCompatibilityInfoIncompatible3 == null) {
                    $$$reportNull$$$0(34);
                }
                return overrideCompatibilityInfoIncompatible3;
            }
        }
        if ((superDescriptor instanceof FunctionDescriptor) && (subDescriptor instanceof FunctionDescriptor) && ((FunctionDescriptor) superDescriptor).isSuspend() != ((FunctionDescriptor) subDescriptor).isSuspend()) {
            OverrideCompatibilityInfo overrideCompatibilityInfoConflict2 = OverrideCompatibilityInfo.conflict("Incompatible suspendability");
            if (overrideCompatibilityInfoConflict2 == null) {
                $$$reportNull$$$0(35);
            }
            return overrideCompatibilityInfoConflict2;
        }
        if (checkReturnType) {
            KotlinType superReturnType = superDescriptor.getReturnType();
            KotlinType subReturnType = subDescriptor.getReturnType();
            if (superReturnType != null && subReturnType != null) {
                boolean bothErrors = KotlinTypeKt.isError(subReturnType) && KotlinTypeKt.isError(superReturnType);
                if (!bothErrors && !typeChecker.getFirst().isSubtypeOf(typeChecker.getSecond(), subReturnType.unwrap(), superReturnType.unwrap())) {
                    OverrideCompatibilityInfo overrideCompatibilityInfoConflict3 = OverrideCompatibilityInfo.conflict("Return type mismatch");
                    if (overrideCompatibilityInfoConflict3 == null) {
                        $$$reportNull$$$0(36);
                    }
                    return overrideCompatibilityInfoConflict3;
                }
            }
        }
        OverrideCompatibilityInfo overrideCompatibilityInfoSuccess = OverrideCompatibilityInfo.success();
        if (overrideCompatibilityInfoSuccess == null) {
            $$$reportNull$$$0(37);
        }
        return overrideCompatibilityInfoSuccess;
    }

    @Nullable
    public static OverrideCompatibilityInfo getBasicOverridabilityProblem(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor) {
        if (superDescriptor == null) {
            $$$reportNull$$$0(38);
        }
        if (subDescriptor == null) {
            $$$reportNull$$$0(39);
        }
        if (((superDescriptor instanceof FunctionDescriptor) && !(subDescriptor instanceof FunctionDescriptor)) || ((superDescriptor instanceof PropertyDescriptor) && !(subDescriptor instanceof PropertyDescriptor))) {
            return OverrideCompatibilityInfo.incompatible("Member kind mismatch");
        }
        if (!(superDescriptor instanceof FunctionDescriptor) && !(superDescriptor instanceof PropertyDescriptor)) {
            throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + superDescriptor);
        }
        if (!superDescriptor.getName().equals(subDescriptor.getName())) {
            return OverrideCompatibilityInfo.incompatible("Name mismatch");
        }
        OverrideCompatibilityInfo receiverAndParameterResult = checkReceiverAndParameterCount(superDescriptor, subDescriptor);
        if (receiverAndParameterResult != null) {
            return receiverAndParameterResult;
        }
        return null;
    }

    @NotNull
    private Pair<NewKotlinTypeCheckerImpl, ClassicTypeCheckerContext> createTypeChecker(@NotNull List<TypeParameterDescriptor> firstParameters, @NotNull List<TypeParameterDescriptor> secondParameters) {
        if (firstParameters == null) {
            $$$reportNull$$$0(40);
        }
        if (secondParameters == null) {
            $$$reportNull$$$0(41);
        }
        if (!$assertionsDisabled && firstParameters.size() != secondParameters.size()) {
            throw new AssertionError("Should be the same number of type parameters: " + firstParameters + " vs " + secondParameters);
        }
        NewKotlinTypeCheckerImpl typeChecker = new NewKotlinTypeCheckerImpl(this.kotlinTypeRefiner, KotlinTypePreparator.Default.INSTANCE);
        ClassicTypeCheckerContext context = createTypeCheckerContext(firstParameters, secondParameters);
        return new Pair<>(typeChecker, context);
    }

    @NotNull
    private ClassicTypeCheckerContext createTypeCheckerContext(@NotNull List<TypeParameterDescriptor> firstParameters, @NotNull List<TypeParameterDescriptor> secondParameters) {
        if (firstParameters == null) {
            $$$reportNull$$$0(42);
        }
        if (secondParameters == null) {
            $$$reportNull$$$0(43);
        }
        if (firstParameters.isEmpty()) {
            ClassicTypeCheckerContext classicTypeCheckerContext = (ClassicTypeCheckerContext) new OverridingUtilTypeSystemContext(null, this.equalityAxioms, this.kotlinTypeRefiner).newBaseTypeCheckerContext(true, true);
            if (classicTypeCheckerContext == null) {
                $$$reportNull$$$0(44);
            }
            return classicTypeCheckerContext;
        }
        Map<TypeConstructor, TypeConstructor> matchingTypeConstructors = new HashMap<>();
        for (int i = 0; i < firstParameters.size(); i++) {
            matchingTypeConstructors.put(firstParameters.get(i).getTypeConstructor(), secondParameters.get(i).getTypeConstructor());
        }
        ClassicTypeCheckerContext classicTypeCheckerContext2 = (ClassicTypeCheckerContext) new OverridingUtilTypeSystemContext(matchingTypeConstructors, this.equalityAxioms, this.kotlinTypeRefiner).newBaseTypeCheckerContext(true, true);
        if (classicTypeCheckerContext2 == null) {
            $$$reportNull$$$0(45);
        }
        return classicTypeCheckerContext2;
    }

    @Nullable
    private static OverrideCompatibilityInfo checkReceiverAndParameterCount(CallableDescriptor superDescriptor, CallableDescriptor subDescriptor) {
        if ((superDescriptor.getExtensionReceiverParameter() == null) != (subDescriptor.getExtensionReceiverParameter() == null)) {
            return OverrideCompatibilityInfo.incompatible("Receiver presence mismatch");
        }
        if (superDescriptor.getValueParameters().size() != subDescriptor.getValueParameters().size()) {
            return OverrideCompatibilityInfo.incompatible("Value parameter number mismatch");
        }
        return null;
    }

    private boolean areTypesEquivalent(@NotNull KotlinType typeInSuper, @NotNull KotlinType typeInSub, @NotNull Pair<NewKotlinTypeCheckerImpl, ClassicTypeCheckerContext> typeChecker) {
        if (typeInSuper == null) {
            $$$reportNull$$$0(46);
        }
        if (typeInSub == null) {
            $$$reportNull$$$0(47);
        }
        if (typeChecker == null) {
            $$$reportNull$$$0(48);
        }
        boolean bothErrors = KotlinTypeKt.isError(typeInSuper) && KotlinTypeKt.isError(typeInSub);
        if (bothErrors) {
            return true;
        }
        return typeChecker.getFirst().equalTypes(typeChecker.getSecond(), typeInSuper.unwrap(), typeInSub.unwrap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008f, code lost:
    
        r0.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean areTypeParametersEquivalent(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r6, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r7, @org.jetbrains.annotations.NotNull kotlin.Pair<kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerImpl, kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext> r8) {
        /*
            r5 = this;
            r0 = r6
            if (r0 != 0) goto L9
            r0 = 49
            $$$reportNull$$$0(r0)
        L9:
            r0 = r7
            if (r0 != 0) goto L12
            r0 = 50
            $$$reportNull$$$0(r0)
        L12:
            r0 = r8
            if (r0 != 0) goto L1b
            r0 = 51
            $$$reportNull$$$0(r0)
        L1b:
            r0 = r6
            java.util.List r0 = r0.getUpperBounds()
            r9 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r2 = r7
            java.util.List r2 = r2.getUpperBounds()
            r1.<init>(r2)
            r10 = r0
            r0 = r9
            int r0 = r0.size()
            r1 = r10
            int r1 = r1.size()
            if (r0 == r1) goto L45
            r0 = 0
            return r0
        L45:
            r0 = r9
            java.util.Iterator r0 = r0.iterator()
            r11 = r0
        L4e:
            r0 = r11
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L9e
            r0 = r11
            java.lang.Object r0 = r0.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            r12 = r0
            r0 = r10
            java.util.ListIterator r0 = r0.listIterator()
            r13 = r0
        L6d:
            r0 = r13
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L9c
            r0 = r13
            java.lang.Object r0 = r0.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            r14 = r0
            r0 = r5
            r1 = r12
            r2 = r14
            r3 = r8
            boolean r0 = r0.areTypesEquivalent(r1, r2, r3)
            if (r0 == 0) goto L99
            r0 = r13
            r0.remove()
            goto L4e
        L99:
            goto L6d
        L9c:
            r0 = 0
            return r0
        L9e:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.areTypeParametersEquivalent(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, kotlin.Pair):boolean");
    }

    private static List<KotlinType> compiledValueParameters(CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor receiverParameter = callableDescriptor.getExtensionReceiverParameter();
        List<KotlinType> parameters = new ArrayList<>();
        if (receiverParameter != null) {
            parameters.add(receiverParameter.getType());
        }
        for (ValueParameterDescriptor valueParameterDescriptor : callableDescriptor.getValueParameters()) {
            parameters.add(valueParameterDescriptor.getType());
        }
        return parameters;
    }

    public void generateOverridesInFunctionGroup(@NotNull Name name, @NotNull Collection<? extends CallableMemberDescriptor> membersFromSupertypes, @NotNull Collection<? extends CallableMemberDescriptor> membersFromCurrent, @NotNull ClassDescriptor current, @NotNull OverridingStrategy strategy) {
        if (name == null) {
            $$$reportNull$$$0(52);
        }
        if (membersFromSupertypes == null) {
            $$$reportNull$$$0(53);
        }
        if (membersFromCurrent == null) {
            $$$reportNull$$$0(54);
        }
        if (current == null) {
            $$$reportNull$$$0(55);
        }
        if (strategy == null) {
            $$$reportNull$$$0(56);
        }
        Collection<CallableMemberDescriptor> notOverridden = new LinkedHashSet<>(membersFromSupertypes);
        for (CallableMemberDescriptor fromCurrent : membersFromCurrent) {
            Collection<CallableMemberDescriptor> bound = extractAndBindOverridesForMember(fromCurrent, membersFromSupertypes, current, strategy);
            notOverridden.removeAll(bound);
        }
        createAndBindFakeOverrides(current, notOverridden, strategy);
    }

    public static boolean isVisibleForOverride(@NotNull MemberDescriptor overriding, @NotNull MemberDescriptor fromSuper) {
        if (overriding == null) {
            $$$reportNull$$$0(57);
        }
        if (fromSuper == null) {
            $$$reportNull$$$0(58);
        }
        return !DescriptorVisibilities.isPrivate(fromSuper.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(fromSuper, overriding);
    }

    private Collection<CallableMemberDescriptor> extractAndBindOverridesForMember(@NotNull CallableMemberDescriptor fromCurrent, @NotNull Collection<? extends CallableMemberDescriptor> descriptorsFromSuper, @NotNull ClassDescriptor current, @NotNull OverridingStrategy strategy) {
        if (fromCurrent == null) {
            $$$reportNull$$$0(59);
        }
        if (descriptorsFromSuper == null) {
            $$$reportNull$$$0(60);
        }
        if (current == null) {
            $$$reportNull$$$0(61);
        }
        if (strategy == null) {
            $$$reportNull$$$0(62);
        }
        Collection<CallableMemberDescriptor> bound = new ArrayList<>(descriptorsFromSuper.size());
        Collection<CallableMemberDescriptor> overridden = SmartSet.create();
        for (CallableMemberDescriptor fromSupertype : descriptorsFromSuper) {
            OverrideCompatibilityInfo.Result result = isOverridableBy(fromSupertype, fromCurrent, current).getResult();
            boolean isVisibleForOverride = isVisibleForOverride(fromCurrent, fromSupertype);
            switch (result) {
                case OVERRIDABLE:
                    if (isVisibleForOverride) {
                        overridden.add(fromSupertype);
                    }
                    bound.add(fromSupertype);
                    break;
                case CONFLICT:
                    if (isVisibleForOverride) {
                        strategy.overrideConflict(fromSupertype, fromCurrent);
                    }
                    bound.add(fromSupertype);
                    break;
            }
        }
        strategy.setOverriddenDescriptors(fromCurrent, overridden);
        return bound;
    }

    private static boolean allHasSameContainingDeclaration(@NotNull Collection<CallableMemberDescriptor> notOverridden) {
        if (notOverridden == null) {
            $$$reportNull$$$0(63);
        }
        if (notOverridden.size() < 2) {
            return true;
        }
        final DeclarationDescriptor containingDeclaration = notOverridden.iterator().next().getContainingDeclaration();
        return CollectionsKt.all(notOverridden, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.3
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(CallableMemberDescriptor descriptor) {
                return Boolean.valueOf(descriptor.getContainingDeclaration() == containingDeclaration);
            }
        });
    }

    private static void createAndBindFakeOverrides(@NotNull ClassDescriptor current, @NotNull Collection<CallableMemberDescriptor> notOverridden, @NotNull OverridingStrategy strategy) {
        if (current == null) {
            $$$reportNull$$$0(64);
        }
        if (notOverridden == null) {
            $$$reportNull$$$0(65);
        }
        if (strategy == null) {
            $$$reportNull$$$0(66);
        }
        if (allHasSameContainingDeclaration(notOverridden)) {
            for (CallableMemberDescriptor descriptor : notOverridden) {
                createAndBindFakeOverride(Collections.singleton(descriptor), current, strategy);
            }
            return;
        }
        Queue<CallableMemberDescriptor> fromSuperQueue = new LinkedList<>(notOverridden);
        while (!fromSuperQueue.isEmpty()) {
            CallableMemberDescriptor notOverriddenFromSuper = VisibilityUtilKt.findMemberWithMaxVisibility(fromSuperQueue);
            Collection<CallableMemberDescriptor> overridables = extractMembersOverridableInBothWays(notOverriddenFromSuper, fromSuperQueue, strategy);
            createAndBindFakeOverride(overridables, current, strategy);
        }
    }

    public static boolean isMoreSpecific(@NotNull CallableDescriptor a, @NotNull CallableDescriptor b) {
        if (a == null) {
            $$$reportNull$$$0(67);
        }
        if (b == null) {
            $$$reportNull$$$0(68);
        }
        KotlinType aReturnType = a.getReturnType();
        KotlinType bReturnType = b.getReturnType();
        if (!$assertionsDisabled && aReturnType == null) {
            throw new AssertionError("Return type of " + a + " is null");
        }
        if (!$assertionsDisabled && bReturnType == null) {
            throw new AssertionError("Return type of " + b + " is null");
        }
        if (!isVisibilityMoreSpecific(a, b)) {
            return false;
        }
        Pair<NewKotlinTypeCheckerImpl, ClassicTypeCheckerContext> checker = DEFAULT.createTypeChecker(a.getTypeParameters(), b.getTypeParameters());
        if (a instanceof FunctionDescriptor) {
            if ($assertionsDisabled || (b instanceof FunctionDescriptor)) {
                return isReturnTypeMoreSpecific(a, aReturnType, b, bReturnType, checker);
            }
            throw new AssertionError("b is " + b.getClass());
        }
        if (a instanceof PropertyDescriptor) {
            if (!$assertionsDisabled && !(b instanceof PropertyDescriptor)) {
                throw new AssertionError("b is " + b.getClass());
            }
            PropertyDescriptor pa = (PropertyDescriptor) a;
            PropertyDescriptor pb = (PropertyDescriptor) b;
            if (!isAccessorMoreSpecific(pa.getSetter(), pb.getSetter())) {
                return false;
            }
            if (pa.isVar() && pb.isVar()) {
                return checker.getFirst().equalTypes(checker.getSecond(), aReturnType.unwrap(), bReturnType.unwrap());
            }
            return (pa.isVar() || !pb.isVar()) && isReturnTypeMoreSpecific(a, aReturnType, b, bReturnType, checker);
        }
        throw new IllegalArgumentException("Unexpected callable: " + a.getClass());
    }

    private static boolean isVisibilityMoreSpecific(@NotNull DeclarationDescriptorWithVisibility a, @NotNull DeclarationDescriptorWithVisibility b) {
        if (a == null) {
            $$$reportNull$$$0(69);
        }
        if (b == null) {
            $$$reportNull$$$0(70);
        }
        Integer result = DescriptorVisibilities.compare(a.getVisibility(), b.getVisibility());
        return result == null || result.intValue() >= 0;
    }

    private static boolean isAccessorMoreSpecific(@Nullable PropertyAccessorDescriptor a, @Nullable PropertyAccessorDescriptor b) {
        if (a == null || b == null) {
            return true;
        }
        return isVisibilityMoreSpecific(a, b);
    }

    private static boolean isMoreSpecificThenAllOf(@NotNull CallableDescriptor candidate, @NotNull Collection<CallableDescriptor> descriptors) {
        if (candidate == null) {
            $$$reportNull$$$0(71);
        }
        if (descriptors == null) {
            $$$reportNull$$$0(72);
        }
        for (CallableDescriptor descriptor : descriptors) {
            if (!isMoreSpecific(candidate, descriptor)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReturnTypeMoreSpecific(@NotNull CallableDescriptor a, @NotNull KotlinType aReturnType, @NotNull CallableDescriptor b, @NotNull KotlinType bReturnType, @NotNull Pair<NewKotlinTypeCheckerImpl, ClassicTypeCheckerContext> typeChecker) {
        if (a == null) {
            $$$reportNull$$$0(73);
        }
        if (aReturnType == null) {
            $$$reportNull$$$0(74);
        }
        if (b == null) {
            $$$reportNull$$$0(75);
        }
        if (bReturnType == null) {
            $$$reportNull$$$0(76);
        }
        if (typeChecker == null) {
            $$$reportNull$$$0(77);
        }
        return typeChecker.getFirst().isSubtypeOf(typeChecker.getSecond(), aReturnType.unwrap(), bReturnType.unwrap());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static <H> H selectMostSpecificMember(@NotNull Collection<H> collection, @NotNull Function1<H, CallableDescriptor> function1) {
        if (collection == null) {
            $$$reportNull$$$0(78);
        }
        if (function1 == 0) {
            $$$reportNull$$$0(79);
        }
        if (!$assertionsDisabled && collection.isEmpty()) {
            throw new AssertionError("Should have at least one overridable descriptor");
        }
        if (collection.size() == 1) {
            H h = (H) CollectionsKt.first(collection);
            if (h == null) {
                $$$reportNull$$$0(80);
            }
            return h;
        }
        ArrayList arrayList = new ArrayList(2);
        List map = CollectionsKt.map(collection, function1);
        H hFirst = CollectionsKt.first(collection);
        CallableDescriptor callableDescriptor = (CallableDescriptor) function1.invoke(hFirst);
        for (H h2 : collection) {
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) function1.invoke(h2);
            if (isMoreSpecificThenAllOf(callableDescriptor2, map)) {
                arrayList.add(h2);
            }
            if (isMoreSpecific(callableDescriptor2, callableDescriptor) && !isMoreSpecific(callableDescriptor, callableDescriptor2)) {
                hFirst = h2;
            }
        }
        if (arrayList.isEmpty()) {
            H h3 = hFirst;
            if (h3 == null) {
                $$$reportNull$$$0(81);
            }
            return h3;
        }
        if (arrayList.size() == 1) {
            H h4 = (H) CollectionsKt.first((Iterable) arrayList);
            if (h4 == null) {
                $$$reportNull$$$0(82);
            }
            return h4;
        }
        H h5 = null;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!FlexibleTypesKt.isFlexible(((CallableDescriptor) function1.invoke(next)).getReturnType())) {
                h5 = next;
                break;
            }
        }
        if (h5 != null) {
            H h6 = h5;
            if (h6 == null) {
                $$$reportNull$$$0(83);
            }
            return h6;
        }
        H h7 = (H) CollectionsKt.first((Iterable) arrayList);
        if (h7 == null) {
            $$$reportNull$$$0(84);
        }
        return h7;
    }

    private static void createAndBindFakeOverride(@NotNull Collection<CallableMemberDescriptor> overridables, @NotNull ClassDescriptor current, @NotNull OverridingStrategy strategy) {
        if (overridables == null) {
            $$$reportNull$$$0(85);
        }
        if (current == null) {
            $$$reportNull$$$0(86);
        }
        if (strategy == null) {
            $$$reportNull$$$0(87);
        }
        Collection<CallableMemberDescriptor> visibleOverridables = filterVisibleFakeOverrides(current, overridables);
        boolean allInvisible = visibleOverridables.isEmpty();
        Collection<CallableMemberDescriptor> effectiveOverridden = allInvisible ? overridables : visibleOverridables;
        Modality modality = determineModalityForFakeOverride(effectiveOverridden, current);
        DescriptorVisibility visibility = allInvisible ? DescriptorVisibilities.INVISIBLE_FAKE : DescriptorVisibilities.INHERITED;
        CallableMemberDescriptor mostSpecific = (CallableMemberDescriptor) selectMostSpecificMember(effectiveOverridden, new Function1<CallableMemberDescriptor, CallableDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.4
            @Override // kotlin.jvm.functions.Function1
            public CallableMemberDescriptor invoke(CallableMemberDescriptor descriptor) {
                return descriptor;
            }
        });
        CallableMemberDescriptor fakeOverride = mostSpecific.copy(current, modality, visibility, CallableMemberDescriptor.Kind.FAKE_OVERRIDE, false);
        strategy.setOverriddenDescriptors(fakeOverride, effectiveOverridden);
        if (!$assertionsDisabled && fakeOverride.getOverriddenDescriptors().isEmpty()) {
            throw new AssertionError("Overridden descriptors should be set for " + CallableMemberDescriptor.Kind.FAKE_OVERRIDE);
        }
        strategy.addFakeOverride(fakeOverride);
    }

    @NotNull
    private static Modality determineModalityForFakeOverride(@NotNull Collection<CallableMemberDescriptor> descriptors, @NotNull ClassDescriptor current) {
        if (descriptors == null) {
            $$$reportNull$$$0(88);
        }
        if (current == null) {
            $$$reportNull$$$0(89);
        }
        boolean hasOpen = false;
        boolean hasAbstract = false;
        for (CallableMemberDescriptor descriptor : descriptors) {
            switch (descriptor.getModality()) {
                case FINAL:
                    Modality modality = Modality.FINAL;
                    if (modality == null) {
                        $$$reportNull$$$0(90);
                    }
                    return modality;
                case SEALED:
                    throw new IllegalStateException("Member cannot have SEALED modality: " + descriptor);
                case OPEN:
                    hasOpen = true;
                    break;
                case ABSTRACT:
                    hasAbstract = true;
                    break;
            }
        }
        boolean transformAbstractToClassModality = (!current.isExpect() || current.getModality() == Modality.ABSTRACT || current.getModality() == Modality.SEALED) ? false : true;
        if (hasOpen && !hasAbstract) {
            Modality modality2 = Modality.OPEN;
            if (modality2 == null) {
                $$$reportNull$$$0(91);
            }
            return modality2;
        }
        if (!hasOpen && hasAbstract) {
            Modality modality3 = transformAbstractToClassModality ? current.getModality() : Modality.ABSTRACT;
            if (modality3 == null) {
                $$$reportNull$$$0(92);
            }
            return modality3;
        }
        Set<CallableMemberDescriptor> allOverriddenDeclarations = new HashSet<>();
        Iterator i$ = descriptors.iterator();
        while (i$.hasNext()) {
            allOverriddenDeclarations.addAll(getOverriddenDeclarations(i$.next()));
        }
        return getMinimalModality(filterOutOverridden(allOverriddenDeclarations), transformAbstractToClassModality, current.getModality());
    }

    @NotNull
    private static Modality getMinimalModality(@NotNull Collection<CallableMemberDescriptor> descriptors, boolean transformAbstractToClassModality, @NotNull Modality classModality) {
        if (descriptors == null) {
            $$$reportNull$$$0(93);
        }
        if (classModality == null) {
            $$$reportNull$$$0(94);
        }
        Modality result = Modality.ABSTRACT;
        for (CallableMemberDescriptor descriptor : descriptors) {
            Modality effectiveModality = (transformAbstractToClassModality && descriptor.getModality() == Modality.ABSTRACT) ? classModality : descriptor.getModality();
            if (effectiveModality.compareTo(result) < 0) {
                result = effectiveModality;
            }
        }
        Modality modality = result;
        if (modality == null) {
            $$$reportNull$$$0(95);
        }
        return modality;
    }

    @NotNull
    private static Collection<CallableMemberDescriptor> filterVisibleFakeOverrides(@NotNull final ClassDescriptor current, @NotNull Collection<CallableMemberDescriptor> toFilter) {
        if (current == null) {
            $$$reportNull$$$0(96);
        }
        if (toFilter == null) {
            $$$reportNull$$$0(97);
        }
        List listFilter = CollectionsKt.filter(toFilter, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.5
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(CallableMemberDescriptor descriptor) {
                return Boolean.valueOf(!DescriptorVisibilities.isPrivate(descriptor.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(descriptor, current));
            }
        });
        if (listFilter == null) {
            $$$reportNull$$$0(98);
        }
        return listFilter;
    }

    @NotNull
    public static <H> Collection<H> extractMembersOverridableInBothWays(@NotNull H overrider, @NotNull Collection<H> extractFrom, @NotNull Function1<H, CallableDescriptor> descriptorByHandle, @NotNull Function1<H, Unit> onConflict) {
        if (overrider == null) {
            $$$reportNull$$$0(99);
        }
        if (extractFrom == null) {
            $$$reportNull$$$0(100);
        }
        if (descriptorByHandle == null) {
            $$$reportNull$$$0(101);
        }
        if (onConflict == null) {
            $$$reportNull$$$0(102);
        }
        Collection<H> overridable = new ArrayList<>();
        overridable.add(overrider);
        CallableDescriptor overriderDescriptor = descriptorByHandle.invoke(overrider);
        Iterator<H> iterator = extractFrom.iterator();
        while (iterator.hasNext()) {
            H candidate = iterator.next();
            CallableDescriptor candidateDescriptor = descriptorByHandle.invoke(candidate);
            if (overrider == candidate) {
                iterator.remove();
            } else {
                OverrideCompatibilityInfo.Result finalResult = getBothWaysOverridability(overriderDescriptor, candidateDescriptor);
                if (finalResult == OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                    overridable.add(candidate);
                    iterator.remove();
                } else if (finalResult == OverrideCompatibilityInfo.Result.CONFLICT) {
                    onConflict.invoke(candidate);
                    iterator.remove();
                }
            }
        }
        if (overridable == null) {
            $$$reportNull$$$0(103);
        }
        return overridable;
    }

    @Nullable
    public static OverrideCompatibilityInfo.Result getBothWaysOverridability(CallableDescriptor overriderDescriptor, CallableDescriptor candidateDescriptor) {
        OverrideCompatibilityInfo.Result result1 = DEFAULT.isOverridableBy(candidateDescriptor, overriderDescriptor, null).getResult();
        OverrideCompatibilityInfo.Result result2 = DEFAULT.isOverridableBy(overriderDescriptor, candidateDescriptor, null).getResult();
        return (result1 == OverrideCompatibilityInfo.Result.OVERRIDABLE && result2 == OverrideCompatibilityInfo.Result.OVERRIDABLE) ? OverrideCompatibilityInfo.Result.OVERRIDABLE : (result1 == OverrideCompatibilityInfo.Result.CONFLICT || result2 == OverrideCompatibilityInfo.Result.CONFLICT) ? OverrideCompatibilityInfo.Result.CONFLICT : OverrideCompatibilityInfo.Result.INCOMPATIBLE;
    }

    @NotNull
    private static Collection<CallableMemberDescriptor> extractMembersOverridableInBothWays(@NotNull final CallableMemberDescriptor overrider, @NotNull Queue<CallableMemberDescriptor> extractFrom, @NotNull final OverridingStrategy strategy) {
        if (overrider == null) {
            $$$reportNull$$$0(104);
        }
        if (extractFrom == null) {
            $$$reportNull$$$0(105);
        }
        if (strategy == null) {
            $$$reportNull$$$0(106);
        }
        return extractMembersOverridableInBothWays(overrider, extractFrom, new Function1<CallableMemberDescriptor, CallableDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.6
            @Override // kotlin.jvm.functions.Function1
            public CallableDescriptor invoke(CallableMemberDescriptor descriptor) {
                return descriptor;
            }
        }, new Function1<CallableMemberDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.7
            @Override // kotlin.jvm.functions.Function1
            public Unit invoke(CallableMemberDescriptor descriptor) {
                strategy.inheritanceConflict(overrider, descriptor);
                return Unit.INSTANCE;
            }
        });
    }

    public static void resolveUnknownVisibilityForMember(@NotNull CallableMemberDescriptor memberDescriptor, @Nullable Function1<CallableMemberDescriptor, Unit> cannotInferVisibility) {
        DescriptorVisibility visibilityToInherit;
        if (memberDescriptor == null) {
            $$$reportNull$$$0(107);
        }
        for (CallableMemberDescriptor descriptor : memberDescriptor.getOverriddenDescriptors()) {
            if (descriptor.getVisibility() == DescriptorVisibilities.INHERITED) {
                resolveUnknownVisibilityForMember(descriptor, cannotInferVisibility);
            }
        }
        if (memberDescriptor.getVisibility() != DescriptorVisibilities.INHERITED) {
            return;
        }
        DescriptorVisibility maxVisibility = computeVisibilityToInherit(memberDescriptor);
        if (maxVisibility == null) {
            if (cannotInferVisibility != null) {
                cannotInferVisibility.invoke(memberDescriptor);
            }
            visibilityToInherit = DescriptorVisibilities.PUBLIC;
        } else {
            visibilityToInherit = maxVisibility;
        }
        if (memberDescriptor instanceof PropertyDescriptorImpl) {
            ((PropertyDescriptorImpl) memberDescriptor).setVisibility(visibilityToInherit);
            for (PropertyAccessorDescriptor accessor : ((PropertyDescriptor) memberDescriptor).getAccessors()) {
                resolveUnknownVisibilityForMember(accessor, maxVisibility == null ? null : cannotInferVisibility);
            }
            return;
        }
        if (memberDescriptor instanceof FunctionDescriptorImpl) {
            ((FunctionDescriptorImpl) memberDescriptor).setVisibility(visibilityToInherit);
            return;
        }
        if (!$assertionsDisabled && !(memberDescriptor instanceof PropertyAccessorDescriptorImpl)) {
            throw new AssertionError();
        }
        PropertyAccessorDescriptorImpl propertyAccessorDescriptor = (PropertyAccessorDescriptorImpl) memberDescriptor;
        propertyAccessorDescriptor.setVisibility(visibilityToInherit);
        if (visibilityToInherit != propertyAccessorDescriptor.getCorrespondingProperty().getVisibility()) {
            propertyAccessorDescriptor.setDefault(false);
        }
    }

    @Nullable
    private static DescriptorVisibility computeVisibilityToInherit(@NotNull CallableMemberDescriptor memberDescriptor) {
        if (memberDescriptor == null) {
            $$$reportNull$$$0(108);
        }
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = memberDescriptor.getOverriddenDescriptors();
        DescriptorVisibility maxVisibility = findMaxVisibility(overriddenDescriptors);
        if (maxVisibility == null) {
            return null;
        }
        if (memberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            for (CallableMemberDescriptor overridden : overriddenDescriptors) {
                if (overridden.getModality() != Modality.ABSTRACT && !overridden.getVisibility().equals(maxVisibility)) {
                    return null;
                }
            }
            return maxVisibility;
        }
        return maxVisibility.normalize();
    }

    @Nullable
    public static DescriptorVisibility findMaxVisibility(@NotNull Collection<? extends CallableMemberDescriptor> descriptors) {
        if (descriptors == null) {
            $$$reportNull$$$0(109);
        }
        if (descriptors.isEmpty()) {
            return DescriptorVisibilities.DEFAULT_VISIBILITY;
        }
        DescriptorVisibility maxVisibility = null;
        for (CallableMemberDescriptor descriptor : descriptors) {
            DescriptorVisibility visibility = descriptor.getVisibility();
            if (!$assertionsDisabled && visibility == DescriptorVisibilities.INHERITED) {
                throw new AssertionError("Visibility should have been computed for " + descriptor);
            }
            if (maxVisibility == null) {
                maxVisibility = visibility;
            } else {
                Integer compareResult = DescriptorVisibilities.compare(visibility, maxVisibility);
                if (compareResult == null) {
                    maxVisibility = null;
                } else if (compareResult.intValue() > 0) {
                    maxVisibility = visibility;
                }
            }
        }
        if (maxVisibility == null) {
            return null;
        }
        Iterator i$ = descriptors.iterator();
        while (i$.hasNext()) {
            Integer compareResult2 = DescriptorVisibilities.compare(maxVisibility, i$.next().getVisibility());
            if (compareResult2 == null || compareResult2.intValue() < 0) {
                return null;
            }
        }
        return maxVisibility;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo.class */
    public static class OverrideCompatibilityInfo {
        private static final OverrideCompatibilityInfo SUCCESS = new OverrideCompatibilityInfo(Result.OVERRIDABLE, "SUCCESS");
        private final Result overridable;
        private final String debugMessage;

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo$Result.class */
        public enum Result {
            OVERRIDABLE,
            INCOMPATIBLE,
            CONFLICT
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 5:
                case 6:
                default:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
            switch (i) {
                case 0:
                case 5:
                case 6:
                default:
                    i2 = 2;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    i2 = 3;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                case 5:
                case 6:
                default:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo";
                    break;
                case 1:
                case 2:
                case 4:
                    objArr[0] = "debugMessage";
                    break;
                case 3:
                    objArr[0] = "success";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[1] = "success";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo";
                    break;
                case 5:
                    objArr[1] = "getResult";
                    break;
                case 6:
                    objArr[1] = "getDebugMessage";
                    break;
            }
            switch (i) {
                case 1:
                    objArr[2] = "incompatible";
                    break;
                case 2:
                    objArr[2] = "conflict";
                    break;
                case 3:
                case 4:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 5:
                case 6:
                default:
                    throw new IllegalStateException(str2);
                case 1:
                case 2:
                case 3:
                case 4:
                    throw new IllegalArgumentException(str2);
            }
        }

        @NotNull
        public static OverrideCompatibilityInfo success() {
            OverrideCompatibilityInfo overrideCompatibilityInfo = SUCCESS;
            if (overrideCompatibilityInfo == null) {
                $$$reportNull$$$0(0);
            }
            return overrideCompatibilityInfo;
        }

        @NotNull
        public static OverrideCompatibilityInfo incompatible(@NotNull String debugMessage) {
            if (debugMessage == null) {
                $$$reportNull$$$0(1);
            }
            return new OverrideCompatibilityInfo(Result.INCOMPATIBLE, debugMessage);
        }

        @NotNull
        public static OverrideCompatibilityInfo conflict(@NotNull String debugMessage) {
            if (debugMessage == null) {
                $$$reportNull$$$0(2);
            }
            return new OverrideCompatibilityInfo(Result.CONFLICT, debugMessage);
        }

        public OverrideCompatibilityInfo(@NotNull Result success, @NotNull String debugMessage) {
            if (success == null) {
                $$$reportNull$$$0(3);
            }
            if (debugMessage == null) {
                $$$reportNull$$$0(4);
            }
            this.overridable = success;
            this.debugMessage = debugMessage;
        }

        @NotNull
        public Result getResult() {
            Result result = this.overridable;
            if (result == null) {
                $$$reportNull$$$0(5);
            }
            return result;
        }
    }
}
