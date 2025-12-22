package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractTypeChecker.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractNullabilityChecker.class */
public final class AbstractNullabilityChecker {

    @NotNull
    public static final AbstractNullabilityChecker INSTANCE = new AbstractNullabilityChecker();

    private AbstractNullabilityChecker() {
    }

    public final boolean isPossibleSubtype(@NotNull AbstractTypeCheckerContext context, @NotNull SimpleTypeMarker subType, @NotNull SimpleTypeMarker superType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return runIsPossibleSubtype(context, subType, superType);
    }

    private final boolean runIsPossibleSubtype(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        TypeSystemContext $this$runIsPossibleSubtype_u24lambda_u2d3 = context.getTypeSystemContext();
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            boolean z = $this$runIsPossibleSubtype_u24lambda_u2d3.isSingleClassifierType(subType) || $this$runIsPossibleSubtype_u24lambda_u2d3.isIntersection($this$runIsPossibleSubtype_u24lambda_u2d3.typeConstructor(subType)) || context.isAllowedTypeVariableBridge(subType);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError(Intrinsics.stringPlus("Not singleClassifierType and not intersection subType: ", subType));
            }
            boolean z2 = $this$runIsPossibleSubtype_u24lambda_u2d3.isSingleClassifierType(superType) || context.isAllowedTypeVariableBridge(superType);
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError(Intrinsics.stringPlus("Not singleClassifierType superType: ", superType));
            }
        }
        if ($this$runIsPossibleSubtype_u24lambda_u2d3.isMarkedNullable(superType) || $this$runIsPossibleSubtype_u24lambda_u2d3.isDefinitelyNotNullType(subType)) {
            return true;
        }
        if (((subType instanceof CapturedTypeMarker) && $this$runIsPossibleSubtype_u24lambda_u2d3.isProjectionNotNull((CapturedTypeMarker) subType)) || INSTANCE.hasNotNullSupertype(context, subType, AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE)) {
            return true;
        }
        if ($this$runIsPossibleSubtype_u24lambda_u2d3.isDefinitelyNotNullType(superType) || INSTANCE.hasNotNullSupertype(context, superType, AbstractTypeCheckerContext.SupertypesPolicy.UpperIfFlexible.INSTANCE) || $this$runIsPossibleSubtype_u24lambda_u2d3.isClassType(subType)) {
            return false;
        }
        return INSTANCE.hasPathByNotMarkedNullableNodes(context, subType, $this$runIsPossibleSubtype_u24lambda_u2d3.typeConstructor(superType));
    }

    public final boolean hasNotNullSupertype(@NotNull AbstractTypeCheckerContext $this$hasNotNullSupertype, @NotNull SimpleTypeMarker type, @NotNull AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy) {
        Intrinsics.checkNotNullParameter($this$hasNotNullSupertype, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(supertypesPolicy, "supertypesPolicy");
        TypeSystemContext $this$hasNotNullSupertype_u24lambda_u2d6 = $this$hasNotNullSupertype.getTypeSystemContext();
        if (($this$hasNotNullSupertype_u24lambda_u2d6.isClassType(type) && !$this$hasNotNullSupertype_u24lambda_u2d6.isMarkedNullable(type)) || $this$hasNotNullSupertype_u24lambda_u2d6.isDefinitelyNotNullType(type)) {
            return true;
        }
        $this$hasNotNullSupertype.initialize();
        ArrayDeque deque$iv = $this$hasNotNullSupertype.getSupertypesDeque();
        Intrinsics.checkNotNull(deque$iv);
        Set visitedSupertypes$iv = $this$hasNotNullSupertype.getSupertypesSet();
        Intrinsics.checkNotNull(visitedSupertypes$iv);
        deque$iv.push(type);
        while (true) {
            if (!deque$iv.isEmpty()) {
                if (visitedSupertypes$iv.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + type + ". Supertypes = " + CollectionsKt.joinToString$default(visitedSupertypes$iv, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = deque$iv.pop();
                Intrinsics.checkNotNullExpressionValue(current, "current");
                if (visitedSupertypes$iv.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy it$iv = $this$hasNotNullSupertype_u24lambda_u2d6.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : supertypesPolicy;
                    AbstractTypeCheckerContext.SupertypesPolicy policy$iv = !Intrinsics.areEqual(it$iv, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE) ? it$iv : null;
                    if (policy$iv == null) {
                        continue;
                    } else {
                        TypeSystemContext $this$anySupertype_u24lambda_u2d2$iv = $this$hasNotNullSupertype.getTypeSystemContext();
                        Collection supertypes$iv = $this$anySupertype_u24lambda_u2d2$iv.supertypes($this$anySupertype_u24lambda_u2d2$iv.typeConstructor(current));
                        for (KotlinTypeMarker supertype$iv : supertypes$iv) {
                            SimpleTypeMarker newType$iv = policy$iv.mo3909transformType($this$hasNotNullSupertype, supertype$iv);
                            if (($this$hasNotNullSupertype_u24lambda_u2d6.isClassType(newType$iv) && !$this$hasNotNullSupertype_u24lambda_u2d6.isMarkedNullable(newType$iv)) || $this$hasNotNullSupertype_u24lambda_u2d6.isDefinitelyNotNullType(newType$iv)) {
                                $this$hasNotNullSupertype.clear();
                                return true;
                            }
                            deque$iv.add(newType$iv);
                        }
                    }
                }
            } else {
                $this$hasNotNullSupertype.clear();
                return false;
            }
        }
    }

    public final boolean hasPathByNotMarkedNullableNodes(@NotNull AbstractTypeCheckerContext context, @NotNull SimpleTypeMarker start, @NotNull TypeConstructorMarker end) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        TypeSystemContext $this$hasPathByNotMarkedNullableNodes_u24lambda_u2d9 = context.getTypeSystemContext();
        if (INSTANCE.isApplicableAsEndNode(context, start, end)) {
            return true;
        }
        context.initialize();
        ArrayDeque deque$iv = context.getSupertypesDeque();
        Intrinsics.checkNotNull(deque$iv);
        Set visitedSupertypes$iv = context.getSupertypesSet();
        Intrinsics.checkNotNull(visitedSupertypes$iv);
        deque$iv.push(start);
        while (true) {
            if (!deque$iv.isEmpty()) {
                if (visitedSupertypes$iv.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + start + ". Supertypes = " + CollectionsKt.joinToString$default(visitedSupertypes$iv, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = deque$iv.pop();
                Intrinsics.checkNotNullExpressionValue(current, "current");
                if (visitedSupertypes$iv.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy it$iv = $this$hasPathByNotMarkedNullableNodes_u24lambda_u2d9.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    AbstractTypeCheckerContext.SupertypesPolicy policy$iv = !Intrinsics.areEqual(it$iv, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE) ? it$iv : null;
                    if (policy$iv == null) {
                        continue;
                    } else {
                        TypeSystemContext $this$anySupertype_u24lambda_u2d2$iv = context.getTypeSystemContext();
                        Collection supertypes$iv = $this$anySupertype_u24lambda_u2d2$iv.supertypes($this$anySupertype_u24lambda_u2d2$iv.typeConstructor(current));
                        for (KotlinTypeMarker supertype$iv : supertypes$iv) {
                            SimpleTypeMarker newType$iv = policy$iv.mo3909transformType(context, supertype$iv);
                            if (INSTANCE.isApplicableAsEndNode(context, newType$iv, end)) {
                                context.clear();
                                return true;
                            }
                            deque$iv.add(newType$iv);
                        }
                    }
                }
            } else {
                context.clear();
                return false;
            }
        }
    }

    private final boolean isApplicableAsEndNode(AbstractTypeCheckerContext context, SimpleTypeMarker type, TypeConstructorMarker end) {
        TypeSystemContext $this$isApplicableAsEndNode_u24lambda_u2d10 = context.getTypeSystemContext();
        if ($this$isApplicableAsEndNode_u24lambda_u2d10.isNothing(type)) {
            return true;
        }
        if ($this$isApplicableAsEndNode_u24lambda_u2d10.isMarkedNullable(type)) {
            return false;
        }
        if (context.isStubTypeEqualsToAnything() && $this$isApplicableAsEndNode_u24lambda_u2d10.isStubType(type)) {
            return true;
        }
        return $this$isApplicableAsEndNode_u24lambda_u2d10.areEqualTypeConstructors($this$isApplicableAsEndNode_u24lambda_u2d10.typeConstructor(type), end);
    }
}
