package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractTypeChecker.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeChecker.class */
public final class AbstractTypeChecker {

    @NotNull
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();

    @JvmField
    public static boolean RUN_SLOW_ASSERTIONS;

    /* compiled from: AbstractTypeChecker.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractTypeChecker$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            iArr[TypeVariance.INV.ordinal()] = 1;
            iArr[TypeVariance.OUT.ordinal()] = 2;
            iArr[TypeVariance.IN.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AbstractTypeCheckerContext.LowerCapturedTypePolicy.values().length];
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private AbstractTypeChecker() {
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(abstractTypeCheckerContext, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean isSubtypeOf(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType, boolean isFromNullabilityConstraint) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        if (subType == superType) {
            return true;
        }
        if (context.customIsSubtypeOf(subType, superType)) {
            return completeIsSubTypeOf(context, subType, superType, isFromNullabilityConstraint);
        }
        return false;
    }

    public final boolean equalTypes(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker a, @NotNull KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        TypeSystemContext $this$equalTypes_u24lambda_u2d1 = context.getTypeSystemContext();
        if (a == b) {
            return true;
        }
        if (INSTANCE.isCommonDenotableType($this$equalTypes_u24lambda_u2d1, a) && INSTANCE.isCommonDenotableType($this$equalTypes_u24lambda_u2d1, b)) {
            KotlinTypeMarker refinedA = context.refineType(a);
            KotlinTypeMarker refinedB = context.refineType(b);
            SimpleTypeMarker simpleA = $this$equalTypes_u24lambda_u2d1.lowerBoundIfFlexible(refinedA);
            if (!$this$equalTypes_u24lambda_u2d1.areEqualTypeConstructors($this$equalTypes_u24lambda_u2d1.typeConstructor(refinedA), $this$equalTypes_u24lambda_u2d1.typeConstructor(refinedB))) {
                return false;
            }
            if ($this$equalTypes_u24lambda_u2d1.argumentsCount(simpleA) == 0) {
                return $this$equalTypes_u24lambda_u2d1.hasFlexibleNullability(refinedA) || $this$equalTypes_u24lambda_u2d1.hasFlexibleNullability(refinedB) || $this$equalTypes_u24lambda_u2d1.isMarkedNullable(simpleA) == $this$equalTypes_u24lambda_u2d1.isMarkedNullable($this$equalTypes_u24lambda_u2d1.lowerBoundIfFlexible(refinedB));
            }
        }
        return isSubtypeOf$default(INSTANCE, context, a, b, false, 8, null) && isSubtypeOf$default(INSTANCE, context, b, a, false, 8, null);
    }

    private final boolean completeIsSubTypeOf(AbstractTypeCheckerContext context, KotlinTypeMarker subType, KotlinTypeMarker superType, boolean isFromNullabilityConstraint) {
        TypeSystemContext $this$completeIsSubTypeOf_u24lambda_u2d4 = context.getTypeSystemContext();
        KotlinTypeMarker preparedSubType = context.prepareType(context.refineType(subType));
        KotlinTypeMarker preparedSuperType = context.prepareType(context.refineType(superType));
        Boolean boolCheckSubtypeForSpecialCases = INSTANCE.checkSubtypeForSpecialCases(context, $this$completeIsSubTypeOf_u24lambda_u2d4.lowerBoundIfFlexible(preparedSubType), $this$completeIsSubTypeOf_u24lambda_u2d4.upperBoundIfFlexible(preparedSuperType));
        if (boolCheckSubtypeForSpecialCases != null) {
            boolean it = boolCheckSubtypeForSpecialCases.booleanValue();
            context.addSubtypeConstraint(preparedSubType, preparedSuperType, isFromNullabilityConstraint);
            return it;
        }
        Boolean boolAddSubtypeConstraint = context.addSubtypeConstraint(preparedSubType, preparedSuperType, isFromNullabilityConstraint);
        if (boolAddSubtypeConstraint != null) {
            boolean it2 = boolAddSubtypeConstraint.booleanValue();
            return it2;
        }
        return INSTANCE.isSubtypeOfForSingleClassifierType(context, $this$completeIsSubTypeOf_u24lambda_u2d4.lowerBoundIfFlexible(preparedSubType), $this$completeIsSubTypeOf_u24lambda_u2d4.upperBoundIfFlexible(preparedSuperType));
    }

    private final Boolean checkSubtypeForIntegerLiteralType(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        TypeSystemContext $this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7 = context.getTypeSystemContext();
        if (!$this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(subType) && !$this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(superType)) {
            return null;
        }
        if ($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(subType) && $this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(superType)) {
            return true;
        }
        if ($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(subType)) {
            if (m3902xd35c7e25($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7, context, subType, superType, false)) {
                return true;
            }
            return null;
        }
        if ($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7.isIntegerLiteralType(superType)) {
            if (m3903xabd2962a($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7, subType) || m3902xd35c7e25($this$checkSubtypeForIntegerLiteralType_u24lambda_u2d7, context, superType, subType, true)) {
                return true;
            }
            return null;
        }
        return null;
    }

    /* renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isTypeInIntegerLiteralType, reason: not valid java name */
    private static final boolean m3902xd35c7e25(TypeSystemContext $this_with, AbstractTypeCheckerContext $context, SimpleTypeMarker integerLiteralType, SimpleTypeMarker type, boolean checkSupertypes) {
        Iterable $this$any$iv = $this_with.possibleIntegerTypes(integerLiteralType);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            KotlinTypeMarker possibleType = (KotlinTypeMarker) element$iv;
            if (Intrinsics.areEqual($this_with.typeConstructor(possibleType), $this_with.typeConstructor(type)) || (checkSupertypes && isSubtypeOf$default(INSTANCE, $context, type, possibleType, false, 8, null))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isIntegerLiteralTypeInIntersectionComponents, reason: not valid java name */
    private static final boolean m3903xabd2962a(TypeSystemContext $this_with, SimpleTypeMarker type) {
        boolean z;
        TypeConstructorMarker typeConstructor = $this_with.typeConstructor(type);
        if (typeConstructor instanceof IntersectionTypeConstructorMarker) {
            Iterable $this$any$iv = $this_with.supertypes(typeConstructor);
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        KotlinTypeMarker it2 = (KotlinTypeMarker) element$iv;
                        SimpleTypeMarker simpleTypeMarkerAsSimpleType = $this_with.asSimpleType(it2);
                        if (Intrinsics.areEqual((Object) (simpleTypeMarkerAsSimpleType == null ? null : Boolean.valueOf($this_with.isIntegerLiteralType(simpleTypeMarkerAsSimpleType))), (Object) true)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private final boolean hasNothingSupertype(AbstractTypeCheckerContext context, SimpleTypeMarker type) {
        TypeSystemContext $this$hasNothingSupertype_u24lambda_u2d10 = context.getTypeSystemContext();
        TypeConstructorMarker typeConstructor = $this$hasNothingSupertype_u24lambda_u2d10.typeConstructor(type);
        if ($this$hasNothingSupertype_u24lambda_u2d10.isClassTypeConstructor(typeConstructor)) {
            return $this$hasNothingSupertype_u24lambda_u2d10.isNothingConstructor(typeConstructor);
        }
        if ($this$hasNothingSupertype_u24lambda_u2d10.isNothingConstructor($this$hasNothingSupertype_u24lambda_u2d10.typeConstructor(type))) {
            return true;
        }
        context.initialize();
        ArrayDeque deque$iv = context.getSupertypesDeque();
        Intrinsics.checkNotNull(deque$iv);
        Set visitedSupertypes$iv = context.getSupertypesSet();
        Intrinsics.checkNotNull(visitedSupertypes$iv);
        deque$iv.push(type);
        while (true) {
            if (!(!deque$iv.isEmpty())) {
                context.clear();
                return false;
            }
            if (visitedSupertypes$iv.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + type + ". Supertypes = " + CollectionsKt.joinToString$default(visitedSupertypes$iv, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = deque$iv.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (visitedSupertypes$iv.add(current)) {
                AbstractTypeCheckerContext.SupertypesPolicy it$iv = $this$hasNothingSupertype_u24lambda_u2d10.isClassType(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                AbstractTypeCheckerContext.SupertypesPolicy policy$iv = !Intrinsics.areEqual(it$iv, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE) ? it$iv : null;
                if (policy$iv == null) {
                    continue;
                } else {
                    TypeSystemContext $this$anySupertype_u24lambda_u2d2$iv = context.getTypeSystemContext();
                    Collection supertypes$iv = $this$anySupertype_u24lambda_u2d2$iv.supertypes($this$anySupertype_u24lambda_u2d2$iv.typeConstructor(current));
                    for (KotlinTypeMarker supertype$iv : supertypes$iv) {
                        SimpleTypeMarker newType$iv = policy$iv.mo3909transformType(context, supertype$iv);
                        if ($this$hasNothingSupertype_u24lambda_u2d10.isNothingConstructor($this$hasNothingSupertype_u24lambda_u2d10.typeConstructor(newType$iv))) {
                            context.clear();
                            return true;
                        }
                        deque$iv.add(newType$iv);
                    }
                }
            }
        }
    }

    private final boolean isSubtypeOfForSingleClassifierType(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        KotlinTypeMarker type;
        TypeSystemContext $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18 = context.getTypeSystemContext();
        if (RUN_SLOW_ASSERTIONS) {
            boolean z = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.isSingleClassifierType(subType) || $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.isIntersection($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.typeConstructor(subType)) || context.isAllowedTypeVariableBridge(subType);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError(Intrinsics.stringPlus("Not singleClassifierType and not intersection subType: ", subType));
            }
            boolean z2 = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.isSingleClassifierType(superType) || context.isAllowedTypeVariableBridge(superType);
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError(Intrinsics.stringPlus("Not singleClassifierType superType: ", superType));
            }
        }
        if (!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(context, subType, superType)) {
            return false;
        }
        Boolean boolCheckSubtypeForIntegerLiteralType = INSTANCE.checkSubtypeForIntegerLiteralType(context, $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.lowerBoundIfFlexible(subType), $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.upperBoundIfFlexible(superType));
        if (boolCheckSubtypeForIntegerLiteralType != null) {
            boolean it = boolCheckSubtypeForIntegerLiteralType.booleanValue();
            AbstractTypeCheckerContext.addSubtypeConstraint$default(context, subType, superType, false, 4, null);
            return it;
        }
        TypeConstructorMarker superConstructor = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.typeConstructor(superType);
        if (($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.areEqualTypeConstructors($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.typeConstructor(subType), superConstructor) && $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.parametersCount(superConstructor) == 0) || $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.isAnyConstructor($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.typeConstructor(superType))) {
            return true;
        }
        Iterable $this$map$iv = INSTANCE.findCorrespondingSupertypes(context, subType, superConstructor);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SimpleTypeMarker it2 = (SimpleTypeMarker) item$iv$iv;
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.asSimpleType(context.prepareType(it2));
            destination$iv$iv.add(simpleTypeMarkerAsSimpleType == null ? it2 : simpleTypeMarkerAsSimpleType);
        }
        List supertypesWithSameConstructor = (List) destination$iv$iv;
        switch (supertypesWithSameConstructor.size()) {
            case 0:
                return INSTANCE.hasNothingSupertype(context, subType);
            case 1:
                return INSTANCE.isSubtypeForSameConstructor(context, $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.asArgumentList((SimpleTypeMarker) CollectionsKt.first(supertypesWithSameConstructor)), superType);
            default:
                ArgumentList newArguments = new ArgumentList($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.parametersCount(superConstructor));
                boolean anyNonOutParameter = false;
                int i = 0;
                int iParametersCount = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.parametersCount(superConstructor);
                if (0 < iParametersCount) {
                    do {
                        int index = i;
                        i++;
                        anyNonOutParameter = anyNonOutParameter || $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.getVariance($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.getParameter(superConstructor, index)) != TypeVariance.OUT;
                        if (!anyNonOutParameter) {
                            List $this$map$iv2 = supertypesWithSameConstructor;
                            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                            for (Object item$iv$iv2 : $this$map$iv2) {
                                SimpleTypeMarker it3 = (SimpleTypeMarker) item$iv$iv2;
                                TypeArgumentMarker it4 = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.getArgumentOrNull(it3, index);
                                if (it4 == null) {
                                    type = null;
                                } else {
                                    TypeArgumentMarker typeArgumentMarker = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.getVariance(it4) == TypeVariance.INV ? it4 : null;
                                    type = typeArgumentMarker == null ? null : $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.getType(typeArgumentMarker);
                                }
                                KotlinTypeMarker kotlinTypeMarker = type;
                                if (kotlinTypeMarker == null) {
                                    throw new IllegalStateException(("Incorrect type: " + it3 + ", subType: " + subType + ", superType: " + superType).toString());
                                }
                                destination$iv$iv2.add(kotlinTypeMarker);
                            }
                            List allProjections = (List) destination$iv$iv2;
                            TypeArgumentMarker intersection = $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.asTypeArgument($this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.intersectTypes(allProjections));
                            newArguments.add(intersection);
                        }
                    } while (i < iParametersCount);
                }
                if (!anyNonOutParameter && INSTANCE.isSubtypeForSameConstructor(context, newArguments, superType)) {
                    return true;
                }
                List $this$any$iv = supertypesWithSameConstructor;
                if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                    return false;
                }
                for (Object element$iv : $this$any$iv) {
                    if (INSTANCE.isSubtypeForSameConstructor(context, $this$isSubtypeOfForSingleClassifierType_u24lambda_u2d18.asArgumentList((SimpleTypeMarker) element$iv), superType)) {
                        return true;
                    }
                }
                return false;
        }
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext $this$isTypeVariableAgainstStarProjectionForSelfType, KotlinTypeMarker subArgumentType, KotlinTypeMarker superArgumentType, TypeConstructorMarker selfConstructor) {
        SimpleTypeMarker simpleSubArgumentType = $this$isTypeVariableAgainstStarProjectionForSelfType.asSimpleType(subArgumentType);
        if (!(simpleSubArgumentType instanceof CapturedTypeMarker) || !$this$isTypeVariableAgainstStarProjectionForSelfType.isStarProjection($this$isTypeVariableAgainstStarProjectionForSelfType.projection($this$isTypeVariableAgainstStarProjectionForSelfType.typeConstructor((CapturedTypeMarker) simpleSubArgumentType))) || $this$isTypeVariableAgainstStarProjectionForSelfType.captureStatus((CapturedTypeMarker) simpleSubArgumentType) != CaptureStatus.FOR_SUBTYPING) {
            return false;
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = $this$isTypeVariableAgainstStarProjectionForSelfType.typeConstructor(superArgumentType);
        TypeVariableTypeConstructorMarker typeVariableConstructor = typeConstructorMarkerTypeConstructor instanceof TypeVariableTypeConstructorMarker ? (TypeVariableTypeConstructorMarker) typeConstructorMarkerTypeConstructor : null;
        if (typeVariableConstructor == null) {
            return false;
        }
        TypeParameterMarker typeParameter = $this$isTypeVariableAgainstStarProjectionForSelfType.getTypeParameter(typeVariableConstructor);
        return Intrinsics.areEqual((Object) (typeParameter == null ? null : Boolean.valueOf($this$isTypeVariableAgainstStarProjectionForSelfType.hasRecursiveBounds(typeParameter, selfConstructor))), (Object) true);
    }

    public final boolean isSubtypeForSameConstructor(@NotNull AbstractTypeCheckerContext $this$isSubtypeForSameConstructor, @NotNull TypeArgumentListMarker capturedSubArguments, @NotNull SimpleTypeMarker superType) {
        boolean z;
        boolean zIsSubtypeOf$default;
        Intrinsics.checkNotNullParameter($this$isSubtypeForSameConstructor, "<this>");
        Intrinsics.checkNotNullParameter(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(superType, "superType");
        TypeSystemContext $this$isSubtypeForSameConstructor_u24lambda_u2d22 = $this$isSubtypeForSameConstructor.getTypeSystemContext();
        TypeConstructorMarker superTypeConstructor = $this$isSubtypeForSameConstructor_u24lambda_u2d22.typeConstructor(superType);
        int argumentsCount = $this$isSubtypeForSameConstructor_u24lambda_u2d22.size(capturedSubArguments);
        int parametersCount = $this$isSubtypeForSameConstructor_u24lambda_u2d22.parametersCount(superTypeConstructor);
        if (argumentsCount != parametersCount || argumentsCount != $this$isSubtypeForSameConstructor_u24lambda_u2d22.argumentsCount(superType)) {
            return false;
        }
        int i = 0;
        if (0 < parametersCount) {
            do {
                int index = i;
                i++;
                TypeArgumentMarker superProjection = $this$isSubtypeForSameConstructor_u24lambda_u2d22.getArgument(superType, index);
                if (!$this$isSubtypeForSameConstructor_u24lambda_u2d22.isStarProjection(superProjection)) {
                    KotlinTypeMarker superArgumentType = $this$isSubtypeForSameConstructor_u24lambda_u2d22.getType(superProjection);
                    TypeArgumentMarker it = $this$isSubtypeForSameConstructor_u24lambda_u2d22.get(capturedSubArguments, index);
                    boolean z2 = $this$isSubtypeForSameConstructor_u24lambda_u2d22.getVariance(it) == TypeVariance.INV;
                    if (_Assertions.ENABLED && !z2) {
                        throw new AssertionError(Intrinsics.stringPlus("Incorrect sub argument: ", it));
                    }
                    KotlinTypeMarker subArgumentType = $this$isSubtypeForSameConstructor_u24lambda_u2d22.getType(it);
                    TypeVariance variance = INSTANCE.effectiveVariance($this$isSubtypeForSameConstructor_u24lambda_u2d22.getVariance($this$isSubtypeForSameConstructor_u24lambda_u2d22.getParameter(superTypeConstructor, index)), $this$isSubtypeForSameConstructor_u24lambda_u2d22.getVariance(superProjection));
                    if (variance == null) {
                        return $this$isSubtypeForSameConstructor.isErrorTypeEqualsToAnything();
                    }
                    if (variance == TypeVariance.INV) {
                        z = INSTANCE.isTypeVariableAgainstStarProjectionForSelfType($this$isSubtypeForSameConstructor_u24lambda_u2d22, subArgumentType, superArgumentType, superTypeConstructor) || INSTANCE.isTypeVariableAgainstStarProjectionForSelfType($this$isSubtypeForSameConstructor_u24lambda_u2d22, superArgumentType, subArgumentType, superTypeConstructor);
                    } else {
                        z = false;
                    }
                    boolean isTypeVariableAgainstStarProjectionForSelfType = z;
                    if (!isTypeVariableAgainstStarProjectionForSelfType) {
                        if ($this$isSubtypeForSameConstructor.argumentsDepth <= 100) {
                            $this$isSubtypeForSameConstructor.argumentsDepth++;
                            switch (WhenMappings.$EnumSwitchMapping$0[variance.ordinal()]) {
                                case 1:
                                    zIsSubtypeOf$default = INSTANCE.equalTypes($this$isSubtypeForSameConstructor, subArgumentType, superArgumentType);
                                    break;
                                case 2:
                                    zIsSubtypeOf$default = isSubtypeOf$default(INSTANCE, $this$isSubtypeForSameConstructor, subArgumentType, superArgumentType, false, 8, null);
                                    break;
                                case 3:
                                    zIsSubtypeOf$default = isSubtypeOf$default(INSTANCE, $this$isSubtypeForSameConstructor, superArgumentType, subArgumentType, false, 8, null);
                                    break;
                                default:
                                    throw new NoWhenBranchMatchedException();
                            }
                            boolean result$iv = zIsSubtypeOf$default;
                            $this$isSubtypeForSameConstructor.argumentsDepth--;
                            if (!result$iv) {
                                return false;
                            }
                        } else {
                            throw new IllegalStateException(Intrinsics.stringPlus("Arguments depth is too high. Some related argument: ", subArgumentType).toString());
                        }
                    }
                }
            } while (i < parametersCount);
            return true;
        }
        return true;
    }

    private final boolean isCommonDenotableType(TypeSystemContext $this$isCommonDenotableType, KotlinTypeMarker type) {
        return $this$isCommonDenotableType.isDenotable($this$isCommonDenotableType.typeConstructor(type)) && !$this$isCommonDenotableType.isDynamic(type) && !$this$isCommonDenotableType.isDefinitelyNotNullType(type) && Intrinsics.areEqual($this$isCommonDenotableType.typeConstructor($this$isCommonDenotableType.lowerBoundIfFlexible(type)), $this$isCommonDenotableType.typeConstructor($this$isCommonDenotableType.upperBoundIfFlexible(type)));
    }

    @Nullable
    public final TypeVariance effectiveVariance(@NotNull TypeVariance declared, @NotNull TypeVariance useSite) {
        Intrinsics.checkNotNullParameter(declared, "declared");
        Intrinsics.checkNotNullParameter(useSite, "useSite");
        if (declared == TypeVariance.INV) {
            return useSite;
        }
        if (useSite == TypeVariance.INV || declared == useSite) {
            return declared;
        }
        return null;
    }

    private final Boolean checkSubtypeForSpecialCases(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        SimpleTypeMarker simpleTypeMarker;
        TypeParameterMarker typeParameter;
        boolean z;
        KotlinTypeMarker kotlinTypeMarkerMakeDefinitelyNotNullOrNotNull;
        TypeSystemContext $this$checkSubtypeForSpecialCases_u24lambda_u2d25 = context.getTypeSystemContext();
        if ($this$checkSubtypeForSpecialCases_u24lambda_u2d25.isError(subType) || $this$checkSubtypeForSpecialCases_u24lambda_u2d25.isError(superType)) {
            if (context.isErrorTypeEqualsToAnything()) {
                return true;
            }
            if (!$this$checkSubtypeForSpecialCases_u24lambda_u2d25.isMarkedNullable(subType) || $this$checkSubtypeForSpecialCases_u24lambda_u2d25.isMarkedNullable(superType)) {
                return Boolean.valueOf(AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes($this$checkSubtypeForSpecialCases_u24lambda_u2d25, $this$checkSubtypeForSpecialCases_u24lambda_u2d25.withNullability(subType, false), $this$checkSubtypeForSpecialCases_u24lambda_u2d25.withNullability(superType, false)));
            }
            return false;
        }
        if ($this$checkSubtypeForSpecialCases_u24lambda_u2d25.isStubType(subType) || $this$checkSubtypeForSpecialCases_u24lambda_u2d25.isStubType(superType)) {
            return Boolean.valueOf(context.isStubTypeEqualsToAnything());
        }
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.asDefinitelyNotNullType(superType);
        if (definitelyNotNullTypeMarkerAsDefinitelyNotNullType == null) {
            simpleTypeMarker = superType;
        } else {
            SimpleTypeMarker simpleTypeMarkerOriginal = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType);
            simpleTypeMarker = simpleTypeMarkerOriginal == null ? superType : simpleTypeMarkerOriginal;
        }
        SimpleTypeMarker superOriginalType = simpleTypeMarker;
        CapturedTypeMarker superTypeCaptured = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.asCapturedType(superOriginalType);
        KotlinTypeMarker lowerType = superTypeCaptured == null ? null : $this$checkSubtypeForSpecialCases_u24lambda_u2d25.lowerType(superTypeCaptured);
        if (superTypeCaptured != null && lowerType != null) {
            if ($this$checkSubtypeForSpecialCases_u24lambda_u2d25.isMarkedNullable(superType)) {
                kotlinTypeMarkerMakeDefinitelyNotNullOrNotNull = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.withNullability(lowerType, true);
            } else {
                kotlinTypeMarkerMakeDefinitelyNotNullOrNotNull = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.isDefinitelyNotNullType(superType) ? $this$checkSubtypeForSpecialCases_u24lambda_u2d25.makeDefinitelyNotNullOrNotNull(lowerType) : lowerType;
            }
            KotlinTypeMarker nullableLowerType = kotlinTypeMarkerMakeDefinitelyNotNullOrNotNull;
            switch (WhenMappings.$EnumSwitchMapping$1[context.getLowerCapturedTypePolicy(subType, superTypeCaptured).ordinal()]) {
                case 1:
                    return Boolean.valueOf(isSubtypeOf$default(INSTANCE, context, subType, nullableLowerType, false, 8, null));
                case 2:
                    if (isSubtypeOf$default(INSTANCE, context, subType, nullableLowerType, false, 8, null)) {
                        return true;
                    }
                    break;
            }
        }
        TypeConstructorMarker superTypeConstructor = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.typeConstructor(superType);
        if ($this$checkSubtypeForSpecialCases_u24lambda_u2d25.isIntersection(superTypeConstructor)) {
            boolean z2 = !$this$checkSubtypeForSpecialCases_u24lambda_u2d25.isMarkedNullable(superType);
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError(Intrinsics.stringPlus("Intersection type should not be marked nullable!: ", superType));
            }
            Iterable $this$all$iv = $this$checkSubtypeForSpecialCases_u24lambda_u2d25.supertypes(superTypeConstructor);
            if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
                z = true;
            } else {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        KotlinTypeMarker it2 = (KotlinTypeMarker) element$iv;
                        if (!isSubtypeOf$default(INSTANCE, context, subType, it2, false, 8, null)) {
                            z = false;
                        }
                    } else {
                        z = true;
                    }
                }
            }
            return Boolean.valueOf(z);
        }
        if ((subType instanceof CapturedTypeMarker) && (typeParameter = INSTANCE.getTypeParameterForArgumentInBaseIfItEqualToTarget(context.getTypeSystemContext(), superType, subType)) != null && $this$checkSubtypeForSpecialCases_u24lambda_u2d25.hasRecursiveBounds(typeParameter, $this$checkSubtypeForSpecialCases_u24lambda_u2d25.typeConstructor(superType))) {
            return true;
        }
        return null;
    }

    private final TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(TypeSystemContext $this$getTypeParameterForArgumentInBaseIfItEqualToTarget, KotlinTypeMarker baseType, KotlinTypeMarker targetType) {
        int i = 0;
        int iArgumentsCount = $this$getTypeParameterForArgumentInBaseIfItEqualToTarget.argumentsCount(baseType);
        if (0 < iArgumentsCount) {
            do {
                int i2 = i;
                i++;
                TypeArgumentMarker it = $this$getTypeParameterForArgumentInBaseIfItEqualToTarget.getArgument(baseType, i2);
                TypeArgumentMarker typeArgument = !$this$getTypeParameterForArgumentInBaseIfItEqualToTarget.isStarProjection(it) ? it : null;
                if (typeArgument != null) {
                    if (Intrinsics.areEqual($this$getTypeParameterForArgumentInBaseIfItEqualToTarget.getType(typeArgument), targetType)) {
                        return $this$getTypeParameterForArgumentInBaseIfItEqualToTarget.getParameter($this$getTypeParameterForArgumentInBaseIfItEqualToTarget.typeConstructor(baseType), i2);
                    }
                    TypeParameterMarker it2 = getTypeParameterForArgumentInBaseIfItEqualToTarget($this$getTypeParameterForArgumentInBaseIfItEqualToTarget, $this$getTypeParameterForArgumentInBaseIfItEqualToTarget.getType(typeArgument), targetType);
                    if (it2 != null) {
                        return it2;
                    }
                }
            } while (i < iArgumentsCount);
            return null;
        }
        return null;
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(AbstractTypeCheckerContext context, SimpleTypeMarker subType, TypeConstructorMarker superConstructor) {
        AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexibleSubstitutionSupertypePolicy;
        TypeSystemContext $this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31 = context.getTypeSystemContext();
        List it = $this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.fastCorrespondingSupertypes(subType, superConstructor);
        if (it != null) {
            return it;
        }
        if (!$this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.isClassTypeConstructor(superConstructor) && $this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.isClassType(subType)) {
            return CollectionsKt.emptyList();
        }
        if ($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.isCommonFinalClassConstructor(superConstructor)) {
            if ($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.areEqualTypeConstructors($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.typeConstructor(subType), superConstructor)) {
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments = $this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.captureFromArguments(subType, CaptureStatus.FOR_SUBTYPING);
                return CollectionsKt.listOf(simpleTypeMarkerCaptureFromArguments == null ? subType : simpleTypeMarkerCaptureFromArguments);
            }
            return CollectionsKt.emptyList();
        }
        List result = new SmartList();
        if (0 == 0) {
            context.initialize();
            ArrayDeque deque$iv = context.getSupertypesDeque();
            Intrinsics.checkNotNull(deque$iv);
            Set visitedSupertypes$iv = context.getSupertypesSet();
            Intrinsics.checkNotNull(visitedSupertypes$iv);
            deque$iv.push(subType);
            loop0: while (true) {
                if (!deque$iv.isEmpty()) {
                    if (visitedSupertypes$iv.size() > 1000) {
                        throw new IllegalStateException(("Too many supertypes for type: " + subType + ". Supertypes = " + CollectionsKt.joinToString$default(visitedSupertypes$iv, null, null, null, 0, null, null, 63, null)).toString());
                    }
                    SimpleTypeMarker current = deque$iv.pop();
                    Intrinsics.checkNotNullExpressionValue(current, "current");
                    if (visitedSupertypes$iv.add(current)) {
                        SimpleTypeMarker simpleTypeMarkerCaptureFromArguments2 = $this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.captureFromArguments(current, CaptureStatus.FOR_SUBTYPING);
                        SimpleTypeMarker current2 = simpleTypeMarkerCaptureFromArguments2 == null ? current : simpleTypeMarkerCaptureFromArguments2;
                        if ($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.areEqualTypeConstructors($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.typeConstructor(current2), superConstructor)) {
                            result.add(current2);
                            lowerIfFlexibleSubstitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                        } else if ($this$collectAllSupertypesWithGivenTypeConstructor_u24lambda_u2d31.argumentsCount(current2) == 0) {
                            lowerIfFlexibleSubstitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                        } else {
                            lowerIfFlexibleSubstitutionSupertypePolicy = context.substitutionSupertypePolicy(current2);
                        }
                        AbstractTypeCheckerContext.SupertypesPolicy it$iv = lowerIfFlexibleSubstitutionSupertypePolicy;
                        AbstractTypeCheckerContext.SupertypesPolicy policy$iv = !Intrinsics.areEqual(it$iv, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE) ? it$iv : null;
                        if (policy$iv == null) {
                            continue;
                        } else {
                            TypeSystemContext $this$anySupertype_u24lambda_u2d2$iv = context.getTypeSystemContext();
                            Collection supertypes$iv = $this$anySupertype_u24lambda_u2d2$iv.supertypes($this$anySupertype_u24lambda_u2d2$iv.typeConstructor(current));
                            for (KotlinTypeMarker supertype$iv : supertypes$iv) {
                                SimpleTypeMarker newType$iv = policy$iv.mo3909transformType(context, supertype$iv);
                                if (0 != 0) {
                                    context.clear();
                                    break loop0;
                                }
                                deque$iv.add(newType$iv);
                            }
                        }
                    }
                } else {
                    context.clear();
                    break;
                }
            }
        }
        return result;
    }

    private final List<SimpleTypeMarker> collectAndFilter(AbstractTypeCheckerContext context, SimpleTypeMarker classType, TypeConstructorMarker constructor) {
        return selectOnlyPureKotlinSupertypes(context, collectAllSupertypesWithGivenTypeConstructor(context, classType, constructor));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(AbstractTypeCheckerContext context, List<? extends SimpleTypeMarker> list) {
        boolean z;
        TypeSystemContext $this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34 = context.getTypeSystemContext();
        if (list.size() < 2) {
            return list;
        }
        List<? extends SimpleTypeMarker> $this$filter$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SimpleTypeMarker it = (SimpleTypeMarker) element$iv$iv;
            TypeArgumentListMarker $this$all$iv = $this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34.asArgumentList(it);
            int size = $this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34.size($this$all$iv);
            int i = 0;
            while (true) {
                if (i < size) {
                    int index$iv = i;
                    TypeArgumentMarker it2 = $this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34.get($this$all$iv, index$iv);
                    if (!($this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34.asFlexibleType($this$selectOnlyPureKotlinSupertypes_u24lambda_u2d34.getType(it2)) == null)) {
                        z = false;
                        break;
                    }
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List allPureSupertypes = (List) destination$iv$iv;
        return !allPureSupertypes.isEmpty() ? allPureSupertypes : list;
    }

    @NotNull
    public final List<SimpleTypeMarker> findCorrespondingSupertypes(@NotNull AbstractTypeCheckerContext context, @NotNull SimpleTypeMarker subType, @NotNull TypeConstructorMarker superConstructor) {
        AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexible;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superConstructor, "superConstructor");
        TypeSystemContext $this$findCorrespondingSupertypes_u24lambda_u2d38 = context.getTypeSystemContext();
        if ($this$findCorrespondingSupertypes_u24lambda_u2d38.isClassType(subType)) {
            return INSTANCE.collectAndFilter(context, subType, superConstructor);
        }
        if (!$this$findCorrespondingSupertypes_u24lambda_u2d38.isClassTypeConstructor(superConstructor) && !$this$findCorrespondingSupertypes_u24lambda_u2d38.isIntegerLiteralTypeConstructor(superConstructor)) {
            return INSTANCE.collectAllSupertypesWithGivenTypeConstructor(context, subType, superConstructor);
        }
        SmartList classTypeSupertypes = new SmartList();
        if (0 == 0) {
            context.initialize();
            ArrayDeque deque$iv = context.getSupertypesDeque();
            Intrinsics.checkNotNull(deque$iv);
            Set visitedSupertypes$iv = context.getSupertypesSet();
            Intrinsics.checkNotNull(visitedSupertypes$iv);
            deque$iv.push(subType);
            loop0: while (true) {
                if (!deque$iv.isEmpty()) {
                    if (visitedSupertypes$iv.size() > 1000) {
                        throw new IllegalStateException(("Too many supertypes for type: " + subType + ". Supertypes = " + CollectionsKt.joinToString$default(visitedSupertypes$iv, null, null, null, 0, null, null, 63, null)).toString());
                    }
                    SimpleTypeMarker current = deque$iv.pop();
                    Intrinsics.checkNotNullExpressionValue(current, "current");
                    if (visitedSupertypes$iv.add(current)) {
                        if ($this$findCorrespondingSupertypes_u24lambda_u2d38.isClassType(current)) {
                            classTypeSupertypes.add(current);
                            lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                        } else {
                            lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                        }
                        AbstractTypeCheckerContext.SupertypesPolicy it$iv = lowerIfFlexible;
                        AbstractTypeCheckerContext.SupertypesPolicy policy$iv = !Intrinsics.areEqual(it$iv, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE) ? it$iv : null;
                        if (policy$iv == null) {
                            continue;
                        } else {
                            TypeSystemContext $this$anySupertype_u24lambda_u2d2$iv = context.getTypeSystemContext();
                            Collection supertypes$iv = $this$anySupertype_u24lambda_u2d2$iv.supertypes($this$anySupertype_u24lambda_u2d2$iv.typeConstructor(current));
                            for (KotlinTypeMarker supertype$iv : supertypes$iv) {
                                SimpleTypeMarker newType$iv = policy$iv.mo3909transformType(context, supertype$iv);
                                if (0 != 0) {
                                    context.clear();
                                    break loop0;
                                }
                                deque$iv.add(newType$iv);
                            }
                        }
                    }
                } else {
                    context.clear();
                    break;
                }
            }
        }
        SmartList $this$flatMap$iv = classTypeSupertypes;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            SimpleTypeMarker it = (SimpleTypeMarker) element$iv$iv;
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Iterable list$iv$iv = abstractTypeChecker.collectAndFilter(context, it, superConstructor);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }
}
