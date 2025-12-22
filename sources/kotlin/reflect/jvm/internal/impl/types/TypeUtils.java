package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeUtils.class */
public class TypeUtils {
    public static final SimpleType DONT_CARE;
    public static final SimpleType CANT_INFER_FUNCTION_PARAM_TYPE;

    @NotNull
    public static final SimpleType NO_EXPECTED_TYPE;
    public static final SimpleType UNIT_EXPECTED_TYPE;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 13:
            case 14:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 59:
            case 60:
            case 61:
            case 62:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 47:
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 13:
            case 14:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 59:
            case 60:
            case 61:
            case 62:
            default:
                i2 = 3;
                break;
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 47:
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
            case 18:
            case 23:
            case 25:
            case 27:
            case 28:
            case 29:
            case 30:
            case 38:
            case 40:
            case 59:
            case 60:
            case 61:
            case 62:
            default:
                objArr[0] = "type";
                break;
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 47:
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                break;
            case 12:
                objArr[0] = "typeConstructor";
                break;
            case 13:
                objArr[0] = "unsubstitutedMemberScope";
                break;
            case 14:
                objArr[0] = "refinedTypeFactory";
                break;
            case 16:
                objArr[0] = "parameters";
                break;
            case 20:
                objArr[0] = "subType";
                break;
            case 21:
                objArr[0] = "superType";
                break;
            case 22:
                objArr[0] = "substitutor";
                break;
            case 24:
                objArr[0] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                break;
            case 31:
            case 33:
                objArr[0] = "clazz";
                break;
            case 32:
                objArr[0] = "typeArguments";
                break;
            case 34:
                objArr[0] = "projections";
                break;
            case 36:
                objArr[0] = "a";
                break;
            case 37:
                objArr[0] = OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE;
                break;
            case 39:
                objArr[0] = "typeParameters";
                break;
            case 41:
                objArr[0] = "typeParameterConstructors";
                break;
            case 42:
                objArr[0] = "specialType";
                break;
            case 43:
            case 44:
                objArr[0] = "isSpecialType";
                break;
            case 45:
                objArr[0] = "parameterDescriptor";
                break;
            case 46:
            case 50:
                objArr[0] = "numberValueTypeConstructor";
                break;
            case 48:
            case 49:
                objArr[0] = "supertypes";
                break;
            case 51:
            case 54:
                objArr[0] = "expectedType";
                break;
            case 53:
                objArr[0] = "literalTypeConstructor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 13:
            case 14:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 59:
            case 60:
            case 61:
            case 62:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                break;
            case 4:
                objArr[1] = "makeNullableAsSpecified";
                break;
            case 6:
            case 7:
            case 9:
                objArr[1] = "makeNullableIfNeeded";
                break;
            case 11:
            case 15:
                objArr[1] = "makeUnsubstitutedType";
                break;
            case 17:
                objArr[1] = "getDefaultTypeProjections";
                break;
            case 19:
                objArr[1] = "getImmediateSupertypes";
                break;
            case 26:
                objArr[1] = "getAllSupertypes";
                break;
            case 35:
                objArr[1] = "substituteProjectionsForParameters";
                break;
            case 47:
                objArr[1] = "getDefaultPrimitiveNumberType";
                break;
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                objArr[1] = "getPrimitiveNumberType";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "noExpectedType";
                break;
            case 1:
                objArr[2] = "makeNullable";
                break;
            case 2:
                objArr[2] = "makeNotNullable";
                break;
            case 3:
                objArr[2] = "makeNullableAsSpecified";
                break;
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 47:
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                break;
            case 5:
            case 8:
                objArr[2] = "makeNullableIfNeeded";
                break;
            case 10:
                objArr[2] = "canHaveSubtypes";
                break;
            case 12:
            case 13:
            case 14:
                objArr[2] = "makeUnsubstitutedType";
                break;
            case 16:
                objArr[2] = "getDefaultTypeProjections";
                break;
            case 18:
                objArr[2] = "getImmediateSupertypes";
                break;
            case 20:
            case 21:
            case 22:
                objArr[2] = "createSubstitutedSupertype";
                break;
            case 23:
            case 24:
                objArr[2] = "collectAllSupertypes";
                break;
            case 25:
                objArr[2] = "getAllSupertypes";
                break;
            case 27:
                objArr[2] = "isNullableType";
                break;
            case 28:
                objArr[2] = "acceptsNullable";
                break;
            case 29:
                objArr[2] = "hasNullableSuperType";
                break;
            case 30:
                objArr[2] = "getClassDescriptor";
                break;
            case 31:
            case 32:
                objArr[2] = "substituteParameters";
                break;
            case 33:
            case 34:
                objArr[2] = "substituteProjectionsForParameters";
                break;
            case 36:
            case 37:
                objArr[2] = "equalTypes";
                break;
            case 38:
            case 39:
                objArr[2] = "dependsOnTypeParameters";
                break;
            case 40:
            case 41:
                objArr[2] = "dependsOnTypeConstructors";
                break;
            case 42:
            case 43:
            case 44:
                objArr[2] = "contains";
                break;
            case 45:
                objArr[2] = "makeStarProjection";
                break;
            case 46:
            case 48:
                objArr[2] = "getDefaultPrimitiveNumberType";
                break;
            case 49:
                objArr[2] = "findByFqName";
                break;
            case 50:
            case 51:
            case 53:
            case 54:
                objArr[2] = "getPrimitiveNumberType";
                break;
            case 59:
                objArr[2] = "isTypeParameter";
                break;
            case 60:
                objArr[2] = "isReifiedTypeParameter";
                break;
            case 61:
                objArr[2] = "isNonReifiedTypeParameter";
                break;
            case 62:
                objArr[2] = "getTypeParameterDescriptorOrNull";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 13:
            case 14:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 59:
            case 60:
            case 61:
            case 62:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 47:
            case 52:
            case 55:
            case 56:
            case 57:
            case 58:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !TypeUtils.class.desiredAssertionStatus();
        DONT_CARE = ErrorUtils.createErrorTypeWithCustomDebugName("DONT_CARE");
        CANT_INFER_FUNCTION_PARAM_TYPE = ErrorUtils.createErrorType("Cannot be inferred");
        NO_EXPECTED_TYPE = new SpecialType("NO_EXPECTED_TYPE");
        UNIT_EXPECTED_TYPE = new SpecialType("UNIT_EXPECTED_TYPE");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType.class */
    public static class SpecialType extends DelegatingSimpleType {
        private final String name;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 2:
                case 3:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 1:
                case 4:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                default:
                    i2 = 3;
                    break;
                case 1:
                case 4:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "newAnnotations";
                    break;
                case 1:
                case 4:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
                    break;
                case 2:
                    objArr[0] = "delegate";
                    break;
                case 3:
                    objArr[0] = "kotlinTypeRefiner";
                    break;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
                    break;
                case 1:
                    objArr[1] = "toString";
                    break;
                case 4:
                    objArr[1] = "refine";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = "replaceAnnotations";
                    break;
                case 1:
                case 4:
                    break;
                case 2:
                    objArr[2] = "replaceDelegate";
                    break;
                case 3:
                    objArr[2] = "refine";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 2:
                case 3:
                default:
                    throw new IllegalArgumentException(str2);
                case 1:
                case 4:
                    throw new IllegalStateException(str2);
            }
        }

        public SpecialType(String name) {
            this.name = name;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        @NotNull
        protected SimpleType getDelegate() {
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        @NotNull
        public SimpleType replaceAnnotations(@NotNull Annotations newAnnotations) {
            if (newAnnotations == null) {
                $$$reportNull$$$0(0);
            }
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        @NotNull
        public SimpleType makeNullableAsSpecified(boolean newNullability) {
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
        @NotNull
        public String toString() {
            String str = this.name;
            if (str == null) {
                $$$reportNull$$$0(1);
            }
            return str;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        @NotNull
        public DelegatingSimpleType replaceDelegate(@NotNull SimpleType delegate) {
            if (delegate == null) {
                $$$reportNull$$$0(2);
            }
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
        @NotNull
        public SpecialType refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            if (kotlinTypeRefiner == null) {
                $$$reportNull$$$0(3);
            }
            if (this == null) {
                $$$reportNull$$$0(4);
            }
            return this;
        }
    }

    public static boolean noExpectedType(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(0);
        }
        return type == NO_EXPECTED_TYPE || type == UNIT_EXPECTED_TYPE;
    }

    public static boolean isDontCarePlaceholder(@Nullable KotlinType type) {
        return type != null && type.getConstructor() == DONT_CARE.getConstructor();
    }

    @NotNull
    public static KotlinType makeNullable(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        return makeNullableAsSpecified(type, true);
    }

    @NotNull
    public static KotlinType makeNotNullable(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(2);
        }
        return makeNullableAsSpecified(type, false);
    }

    @NotNull
    public static KotlinType makeNullableAsSpecified(@NotNull KotlinType type, boolean nullable) {
        if (type == null) {
            $$$reportNull$$$0(3);
        }
        UnwrappedType unwrappedTypeMakeNullableAsSpecified = type.unwrap().makeNullableAsSpecified(nullable);
        if (unwrappedTypeMakeNullableAsSpecified == null) {
            $$$reportNull$$$0(4);
        }
        return unwrappedTypeMakeNullableAsSpecified;
    }

    @NotNull
    public static SimpleType makeNullableIfNeeded(@NotNull SimpleType type, boolean nullable) {
        if (type == null) {
            $$$reportNull$$$0(5);
        }
        if (nullable) {
            SimpleType simpleTypeMakeNullableAsSpecified = type.makeNullableAsSpecified(true);
            if (simpleTypeMakeNullableAsSpecified == null) {
                $$$reportNull$$$0(6);
            }
            return simpleTypeMakeNullableAsSpecified;
        }
        if (type == null) {
            $$$reportNull$$$0(7);
        }
        return type;
    }

    @NotNull
    public static KotlinType makeNullableIfNeeded(@NotNull KotlinType type, boolean nullable) {
        if (type == null) {
            $$$reportNull$$$0(8);
        }
        if (nullable) {
            return makeNullable(type);
        }
        if (type == null) {
            $$$reportNull$$$0(9);
        }
        return type;
    }

    @NotNull
    public static SimpleType makeUnsubstitutedType(ClassifierDescriptor classifierDescriptor, MemberScope unsubstitutedMemberScope, Function1<KotlinTypeRefiner, SimpleType> refinedTypeFactory) {
        if (ErrorUtils.isError(classifierDescriptor)) {
            SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Unsubstituted type for " + classifierDescriptor);
            if (simpleTypeCreateErrorType == null) {
                $$$reportNull$$$0(11);
            }
            return simpleTypeCreateErrorType;
        }
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        return makeUnsubstitutedType(typeConstructor, unsubstitutedMemberScope, refinedTypeFactory);
    }

    @NotNull
    public static SimpleType makeUnsubstitutedType(@NotNull TypeConstructor typeConstructor, @NotNull MemberScope unsubstitutedMemberScope, @NotNull Function1<KotlinTypeRefiner, SimpleType> refinedTypeFactory) {
        if (typeConstructor == null) {
            $$$reportNull$$$0(12);
        }
        if (unsubstitutedMemberScope == null) {
            $$$reportNull$$$0(13);
        }
        if (refinedTypeFactory == null) {
            $$$reportNull$$$0(14);
        }
        List<TypeProjection> arguments = getDefaultTypeProjections(typeConstructor.getParameters());
        SimpleType simpleTypeSimpleTypeWithNonTrivialMemberScope = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), typeConstructor, arguments, false, unsubstitutedMemberScope, refinedTypeFactory);
        if (simpleTypeSimpleTypeWithNonTrivialMemberScope == null) {
            $$$reportNull$$$0(15);
        }
        return simpleTypeSimpleTypeWithNonTrivialMemberScope;
    }

    @NotNull
    public static List<TypeProjection> getDefaultTypeProjections(@NotNull List<TypeParameterDescriptor> parameters) {
        if (parameters == null) {
            $$$reportNull$$$0(16);
        }
        List<TypeProjection> result = new ArrayList<>(parameters.size());
        for (TypeParameterDescriptor parameterDescriptor : parameters) {
            result.add(new TypeProjectionImpl(parameterDescriptor.getDefaultType()));
        }
        List<TypeProjection> list = CollectionsKt.toList(result);
        if (list == null) {
            $$$reportNull$$$0(17);
        }
        return list;
    }

    @NotNull
    public static List<KotlinType> getImmediateSupertypes(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(18);
        }
        TypeSubstitutor substitutor = TypeSubstitutor.create(type);
        Collection<KotlinType> originalSupertypes = type.getConstructor().mo3835getSupertypes();
        List<KotlinType> result = new ArrayList<>(originalSupertypes.size());
        for (KotlinType supertype : originalSupertypes) {
            KotlinType substitutedType = createSubstitutedSupertype(type, supertype, substitutor);
            if (substitutedType != null) {
                result.add(substitutedType);
            }
        }
        if (result == null) {
            $$$reportNull$$$0(19);
        }
        return result;
    }

    @Nullable
    public static KotlinType createSubstitutedSupertype(@NotNull KotlinType subType, @NotNull KotlinType superType, @NotNull TypeSubstitutor substitutor) {
        if (subType == null) {
            $$$reportNull$$$0(20);
        }
        if (superType == null) {
            $$$reportNull$$$0(21);
        }
        if (substitutor == null) {
            $$$reportNull$$$0(22);
        }
        KotlinType substitutedType = substitutor.substitute(superType, Variance.INVARIANT);
        if (substitutedType != null) {
            return makeNullableIfNeeded(substitutedType, subType.isMarkedNullable());
        }
        return null;
    }

    public static boolean isNullableType(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(27);
        }
        if (type.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(type) && isNullableType(FlexibleTypesKt.asFlexibleType(type).getUpperBound())) {
            return true;
        }
        if (SpecialTypesKt.isDefinitelyNotNullType(type)) {
            return false;
        }
        if (isTypeParameter(type)) {
            return hasNullableSuperType(type);
        }
        TypeConstructor constructor = type.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            for (KotlinType supertype : constructor.mo3835getSupertypes()) {
                if (isNullableType(supertype)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean acceptsNullable(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(28);
        }
        if (type.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(type) && acceptsNullable(FlexibleTypesKt.asFlexibleType(type).getUpperBound())) {
            return true;
        }
        return false;
    }

    public static boolean hasNullableSuperType(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(29);
        }
        if (type.getConstructor().mo3831getDeclarationDescriptor() instanceof ClassDescriptor) {
            return false;
        }
        for (KotlinType supertype : getImmediateSupertypes(type)) {
            if (isNullableType(supertype)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static ClassDescriptor getClassDescriptor(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(30);
        }
        DeclarationDescriptor declarationDescriptor = type.getConstructor().mo3831getDeclarationDescriptor();
        if (declarationDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) declarationDescriptor;
        }
        return null;
    }

    public static boolean contains(@Nullable KotlinType type, @NotNull Function1<UnwrappedType, Boolean> isSpecialType) {
        if (isSpecialType == null) {
            $$$reportNull$$$0(43);
        }
        return contains(type, isSpecialType, null);
    }

    private static boolean contains(@Nullable KotlinType type, @NotNull Function1<UnwrappedType, Boolean> isSpecialType, SmartSet<KotlinType> visited) {
        if (isSpecialType == null) {
            $$$reportNull$$$0(44);
        }
        if (type == null) {
            return false;
        }
        UnwrappedType unwrappedType = type.unwrap();
        if (noExpectedType(type)) {
            return isSpecialType.invoke(unwrappedType).booleanValue();
        }
        if (visited != null && visited.contains(type)) {
            return false;
        }
        if (isSpecialType.invoke(unwrappedType).booleanValue()) {
            return true;
        }
        if (visited == null) {
            visited = SmartSet.create();
        }
        visited.add(type);
        FlexibleType flexibleType = unwrappedType instanceof FlexibleType ? (FlexibleType) unwrappedType : null;
        if (flexibleType != null && (contains(flexibleType.getLowerBound(), isSpecialType, visited) || contains(flexibleType.getUpperBound(), isSpecialType, visited))) {
            return true;
        }
        if ((unwrappedType instanceof DefinitelyNotNullType) && contains(((DefinitelyNotNullType) unwrappedType).getOriginal(), isSpecialType, visited)) {
            return true;
        }
        TypeConstructor typeConstructor = type.getConstructor();
        if (typeConstructor instanceof IntersectionTypeConstructor) {
            IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) typeConstructor;
            for (KotlinType supertype : intersectionTypeConstructor.mo3835getSupertypes()) {
                if (contains(supertype, isSpecialType, visited)) {
                    return true;
                }
            }
            return false;
        }
        for (TypeProjection projection : type.getArguments()) {
            if (!projection.isStarProjection() && contains(projection.getType(), isSpecialType, visited)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor parameterDescriptor) {
        if (parameterDescriptor == null) {
            $$$reportNull$$$0(45);
        }
        return new StarProjectionImpl(parameterDescriptor);
    }

    public static boolean isTypeParameter(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(59);
        }
        return getTypeParameterDescriptorOrNull(type) != null || (type.getConstructor() instanceof NewTypeVariableConstructor);
    }

    @Nullable
    public static TypeParameterDescriptor getTypeParameterDescriptorOrNull(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(62);
        }
        if (type.getConstructor().mo3831getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return (TypeParameterDescriptor) type.getConstructor().mo3831getDeclarationDescriptor();
        }
        return null;
    }
}
