package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RawType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/types/RawSubstitution.class */
public final class RawSubstitution extends TypeSubstitution {

    @NotNull
    public static final RawSubstitution INSTANCE = new RawSubstitution();

    @NotNull
    private static final JavaTypeAttributes lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);

    @NotNull
    private static final JavaTypeAttributes upperTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);

    /* compiled from: RawType.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/types/RawSubstitution$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaTypeFlexibility.values().length];
            iArr[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            iArr[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            iArr[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private RawSubstitution() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @NotNull
    /* renamed from: get */
    public TypeProjectionImpl mo3923get(@NotNull KotlinType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new TypeProjectionImpl(eraseType$default(this, key, null, 2, null));
    }

    static /* synthetic */ KotlinType eraseType$default(RawSubstitution rawSubstitution, KotlinType kotlinType, JavaTypeAttributes javaTypeAttributes, int i, Object obj) {
        if ((i & 2) != 0) {
            javaTypeAttributes = new JavaTypeAttributes(TypeUsage.COMMON, null, false, null, 14, null);
        }
        return rawSubstitution.eraseType(kotlinType, javaTypeAttributes);
    }

    private final KotlinType eraseType(KotlinType type, JavaTypeAttributes attr) {
        RawTypeImpl rawTypeImpl;
        ClassifierDescriptor declaration = type.getConstructor().mo3831getDeclarationDescriptor();
        if (declaration instanceof TypeParameterDescriptor) {
            return eraseType(JavaTypeResolverKt.getErasedUpperBound$default((TypeParameterDescriptor) declaration, true, attr, null, 4, null), attr);
        }
        if (!(declaration instanceof ClassDescriptor)) {
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected declaration kind: ", declaration).toString());
        }
        ClassifierDescriptor declarationForUpper = FlexibleTypesKt.upperIfFlexible(type).getConstructor().mo3831getDeclarationDescriptor();
        if (!(declarationForUpper instanceof ClassDescriptor)) {
            throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + declarationForUpper + "\" while for lower it's \"" + declaration + '\"').toString());
        }
        Pair<SimpleType, Boolean> pairEraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(type), (ClassDescriptor) declaration, lowerTypeAttr);
        SimpleType lower = pairEraseInflexibleBasedOnClassDescriptor.component1();
        boolean isRawL = pairEraseInflexibleBasedOnClassDescriptor.component2().booleanValue();
        Pair<SimpleType, Boolean> pairEraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(type), (ClassDescriptor) declarationForUpper, upperTypeAttr);
        SimpleType upper = pairEraseInflexibleBasedOnClassDescriptor2.component1();
        boolean isRawU = pairEraseInflexibleBasedOnClassDescriptor2.component2().booleanValue();
        if (isRawL || isRawU) {
            rawTypeImpl = new RawTypeImpl(lower, upper);
        } else {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            rawTypeImpl = KotlinTypeFactory.flexibleType(lower, upper);
        }
        return rawTypeImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(final SimpleType type, final ClassDescriptor declaration, final JavaTypeAttributes attr) {
        if (type.getConstructor().getParameters().isEmpty()) {
            return TuplesKt.to(type, false);
        }
        if (KotlinBuiltIns.isArray(type)) {
            TypeProjection componentTypeProjection = type.getArguments().get(0);
            Variance projectionKind = componentTypeProjection.getProjectionKind();
            KotlinType type2 = componentTypeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "componentTypeProjection.type");
            List arguments = CollectionsKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type2, attr)));
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return TuplesKt.to(KotlinTypeFactory.simpleType$default(type.getAnnotations(), type.getConstructor(), arguments, type.isMarkedNullable(), null, 16, null), false);
        }
        if (KotlinTypeKt.isError(type)) {
            SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType(Intrinsics.stringPlus("Raw error type: ", type.getConstructor()));
            Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "createErrorType(\"Raw error type: ${type.constructor}\")");
            return TuplesKt.to(simpleTypeCreateErrorType, false);
        }
        MemberScope memberScope = declaration.getMemberScope(INSTANCE);
        Intrinsics.checkNotNullExpressionValue(memberScope, "declaration.getMemberScope(RawSubstitution)");
        KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
        Annotations annotations = type.getAnnotations();
        TypeConstructor typeConstructor = declaration.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "declaration.typeConstructor");
        Iterable parameters = declaration.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "declaration.typeConstructor.parameters");
        Iterable $this$map$iv = parameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeParameterDescriptor parameter = (TypeParameterDescriptor) item$iv$iv;
            RawSubstitution rawSubstitution = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
            destination$iv$iv.add(computeProjection$default(rawSubstitution, parameter, attr, null, 4, null));
        }
        return TuplesKt.to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, (List) destination$iv$iv, type.isMarkedNullable(), memberScope, new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution.eraseInflexibleBasedOnClassDescriptor.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final SimpleType invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                ClassDescriptor refinedClassDescriptor;
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                ClassDescriptor classDescriptor = declaration;
                ClassDescriptor classDescriptor2 = classDescriptor instanceof ClassDescriptor ? classDescriptor : null;
                ClassId classId = classDescriptor2 == null ? null : DescriptorUtilsKt.getClassId(classDescriptor2);
                if (classId == null || (refinedClassDescriptor = kotlinTypeRefiner.findClassAcrossModuleDependencies(classId)) == null || Intrinsics.areEqual(refinedClassDescriptor, declaration)) {
                    return null;
                }
                return (SimpleType) RawSubstitution.INSTANCE.eraseInflexibleBasedOnClassDescriptor(type, refinedClassDescriptor, attr).getFirst();
            }
        }), true);
    }

    public static /* synthetic */ TypeProjection computeProjection$default(RawSubstitution rawSubstitution, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int i, Object obj) {
        if ((i & 4) != 0) {
            kotlinType = JavaTypeResolverKt.getErasedUpperBound$default(typeParameterDescriptor, true, javaTypeAttributes, null, 4, null);
        }
        return rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    @NotNull
    public final TypeProjection computeProjection(@NotNull TypeParameterDescriptor parameter, @NotNull JavaTypeAttributes attr, @NotNull KotlinType erasedUpperBound) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(attr, "attr");
        Intrinsics.checkNotNullParameter(erasedUpperBound, "erasedUpperBound");
        switch (WhenMappings.$EnumSwitchMapping$0[attr.getFlexibility().ordinal()]) {
            case 1:
                return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
            case 2:
            case 3:
                if (!parameter.getVariance().getAllowsOutPosition()) {
                    return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(parameter).getNothingType());
                }
                List<TypeParameterDescriptor> parameters = erasedUpperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "erasedUpperBound.constructor.parameters");
                if (!parameters.isEmpty()) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound);
                }
                return JavaTypeResolverKt.makeStarProjection(parameter, attr);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }
}
