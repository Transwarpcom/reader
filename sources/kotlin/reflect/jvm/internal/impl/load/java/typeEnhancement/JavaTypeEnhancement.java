package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/JavaTypeEnhancement.class */
public final class JavaTypeEnhancement {

    @NotNull
    private final JavaResolverSettings javaResolverSettings;

    public JavaTypeEnhancement(@NotNull JavaResolverSettings javaResolverSettings) {
        Intrinsics.checkNotNullParameter(javaResolverSettings, "javaResolverSettings");
        this.javaResolverSettings = javaResolverSettings;
    }

    /* compiled from: typeEnhancement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/JavaTypeEnhancement$Result.class */
    private static class Result {

        @NotNull
        private final KotlinType type;
        private final int subtreeSize;
        private final boolean wereChanges;

        public Result(@NotNull KotlinType type, int subtreeSize, boolean wereChanges) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.subtreeSize = subtreeSize;
            this.wereChanges = wereChanges;
        }

        @NotNull
        public KotlinType getType() {
            return this.type;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        @Nullable
        public final KotlinType getTypeIfChanged() {
            KotlinType type = getType();
            if (getWereChanges()) {
                return type;
            }
            return null;
        }
    }

    /* compiled from: typeEnhancement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/JavaTypeEnhancement$SimpleResult.class */
    private static final class SimpleResult extends Result {

        @NotNull
        private final SimpleType type;

        @Override // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement.Result
        @NotNull
        public SimpleType getType() {
            return this.type;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SimpleResult(@NotNull SimpleType type, int subtreeSize, boolean wereChanges) {
            super(type, subtreeSize, wereChanges);
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
        }
    }

    @Nullable
    public final KotlinType enhance(@NotNull KotlinType $this$enhance, @NotNull Function1<? super Integer, JavaTypeQualifiers> qualifiers) {
        Intrinsics.checkNotNullParameter($this$enhance, "<this>");
        Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
        return enhancePossiblyFlexible($this$enhance.unwrap(), qualifiers, 0).getTypeIfChanged();
    }

    private final KotlinType buildEnhancementByFlexibleTypeBounds(KotlinType lowerBound, KotlinType upperBound) {
        KotlinType kotlinType;
        KotlinType upperEnhancement = TypeWithEnhancementKt.getEnhancement(upperBound);
        KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(lowerBound);
        if (enhancement != null) {
            kotlinType = enhancement;
        } else {
            if (upperEnhancement == null) {
                return null;
            }
            kotlinType = upperEnhancement;
        }
        KotlinType lowerEnhancement = kotlinType;
        if (upperEnhancement == null) {
            return lowerEnhancement;
        }
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(lowerEnhancement), FlexibleTypesKt.upperIfFlexible(upperEnhancement));
    }

    private final Result enhancePossiblyFlexible(UnwrappedType $this$enhancePossiblyFlexible, Function1<? super Integer, JavaTypeQualifiers> function1, int index) {
        UnwrappedType unwrappedTypeWrapEnhancement;
        RawTypeImpl rawTypeImplFlexibleType;
        if (KotlinTypeKt.isError($this$enhancePossiblyFlexible)) {
            return new Result($this$enhancePossiblyFlexible, 1, false);
        }
        if (!($this$enhancePossiblyFlexible instanceof FlexibleType)) {
            if ($this$enhancePossiblyFlexible instanceof SimpleType) {
                return enhanceInflexible$default(this, (SimpleType) $this$enhancePossiblyFlexible, function1, index, TypeComponentPosition.INFLEXIBLE, false, 8, null);
            }
            throw new NoWhenBranchMatchedException();
        }
        boolean isRawType = $this$enhancePossiblyFlexible instanceof RawType;
        SimpleResult lowerResult = enhanceInflexible(((FlexibleType) $this$enhancePossiblyFlexible).getLowerBound(), function1, index, TypeComponentPosition.FLEXIBLE_LOWER, isRawType);
        SimpleResult upperResult = enhanceInflexible(((FlexibleType) $this$enhancePossiblyFlexible).getUpperBound(), function1, index, TypeComponentPosition.FLEXIBLE_UPPER, isRawType);
        boolean z = lowerResult.getSubtreeSize() == upperResult.getSubtreeSize();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Different tree sizes of bounds: lower = (" + ((FlexibleType) $this$enhancePossiblyFlexible).getLowerBound() + ", " + lowerResult.getSubtreeSize() + "), upper = (" + ((FlexibleType) $this$enhancePossiblyFlexible).getUpperBound() + ", " + upperResult.getSubtreeSize() + ')');
        }
        boolean wereChanges = lowerResult.getWereChanges() || upperResult.getWereChanges();
        KotlinType enhancement = buildEnhancementByFlexibleTypeBounds(lowerResult.getType(), upperResult.getType());
        if (wereChanges) {
            if ($this$enhancePossiblyFlexible instanceof RawTypeImpl) {
                rawTypeImplFlexibleType = new RawTypeImpl(lowerResult.getType(), upperResult.getType());
            } else {
                KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
                rawTypeImplFlexibleType = KotlinTypeFactory.flexibleType(lowerResult.getType(), upperResult.getType());
            }
            unwrappedTypeWrapEnhancement = TypeWithEnhancementKt.wrapEnhancement(rawTypeImplFlexibleType, enhancement);
        } else {
            unwrappedTypeWrapEnhancement = $this$enhancePossiblyFlexible;
        }
        UnwrappedType type = unwrappedTypeWrapEnhancement;
        return new Result(type, lowerResult.getSubtreeSize(), wereChanges);
    }

    static /* synthetic */ SimpleResult enhanceInflexible$default(JavaTypeEnhancement javaTypeEnhancement, SimpleType simpleType, Function1 function1, int i, TypeComponentPosition typeComponentPosition, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return javaTypeEnhancement.enhanceInflexible(simpleType, function1, i, typeComponentPosition, z);
    }

    private final SimpleResult enhanceInflexible(SimpleType $this$enhanceInflexible, Function1<? super Integer, JavaTypeQualifiers> function1, int index, TypeComponentPosition position, boolean isBoundOfRawType) {
        TypeProjection typeProjectionCreateProjection;
        boolean shouldEnhance = TypeComponentPositionKt.shouldEnhance(position);
        if (!shouldEnhance && $this$enhanceInflexible.getArguments().isEmpty()) {
            return new SimpleResult($this$enhanceInflexible, 1, false);
        }
        ClassifierDescriptor originalClass = $this$enhanceInflexible.getConstructor().mo3831getDeclarationDescriptor();
        if (originalClass == null) {
            return new SimpleResult($this$enhanceInflexible, 1, false);
        }
        JavaTypeQualifiers effectiveQualifiers = function1.invoke(Integer.valueOf(index));
        EnhancementResult enhancementResultEnhanceMutability = TypeEnhancementKt.enhanceMutability(originalClass, effectiveQualifiers, position);
        ClassifierDescriptor enhancedClassifier = (ClassifierDescriptor) enhancementResultEnhanceMutability.component1();
        Annotations enhancedMutabilityAnnotations = enhancementResultEnhanceMutability.component2();
        TypeConstructor typeConstructor = enhancedClassifier.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "enhancedClassifier.typeConstructor");
        int globalArgIndex = index + 1;
        boolean wereChanges = enhancedMutabilityAnnotations != null;
        Iterable $this$mapIndexed$iv = $this$enhanceInflexible.getArguments();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int localArgIndex = index$iv$iv;
            index$iv$iv++;
            if (localArgIndex < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection arg = (TypeProjection) item$iv$iv;
            if (arg.isStarProjection()) {
                JavaTypeQualifiers qualifiersForStarProjection = function1.invoke(Integer.valueOf(globalArgIndex));
                globalArgIndex++;
                if (qualifiersForStarProjection.getNullability() == NullabilityQualifier.NOT_NULL && !isBoundOfRawType) {
                    KotlinType enhanced = TypeUtilsKt.makeNotNullable(arg.getType().unwrap());
                    Variance projectionKind = arg.getProjectionKind();
                    Intrinsics.checkNotNullExpressionValue(projectionKind, "arg.projectionKind");
                    typeProjectionCreateProjection = TypeUtilsKt.createProjection(enhanced, projectionKind, typeConstructor.getParameters().get(localArgIndex));
                } else {
                    TypeProjection typeProjectionMakeStarProjection = TypeUtils.makeStarProjection(enhancedClassifier.getTypeConstructor().getParameters().get(localArgIndex));
                    Intrinsics.checkNotNullExpressionValue(typeProjectionMakeStarProjection, "{\n                    TypeUtils.makeStarProjection(enhancedClassifier.typeConstructor.parameters[localArgIndex])\n                }");
                    typeProjectionCreateProjection = typeProjectionMakeStarProjection;
                }
            } else {
                Result enhanced2 = enhancePossiblyFlexible(arg.getType().unwrap(), function1, globalArgIndex);
                wereChanges = wereChanges || enhanced2.getWereChanges();
                globalArgIndex += enhanced2.getSubtreeSize();
                KotlinType type = enhanced2.getType();
                Variance projectionKind2 = arg.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(projectionKind2, "arg.projectionKind");
                typeProjectionCreateProjection = TypeUtilsKt.createProjection(type, projectionKind2, typeConstructor.getParameters().get(localArgIndex));
            }
            destination$iv$iv.add(typeProjectionCreateProjection);
        }
        List enhancedArguments = (List) destination$iv$iv;
        EnhancementResult enhancedNullability = TypeEnhancementKt.getEnhancedNullability($this$enhanceInflexible, effectiveQualifiers, position);
        boolean enhancedNullability2 = ((Boolean) enhancedNullability.component1()).booleanValue();
        Annotations enhancedNullabilityAnnotations = enhancedNullability.component2();
        boolean wereChanges2 = wereChanges || enhancedNullabilityAnnotations != null;
        int subtreeSize = globalArgIndex - index;
        if (!wereChanges2) {
            return new SimpleResult($this$enhanceInflexible, subtreeSize, false);
        }
        Annotations newAnnotations = TypeEnhancementKt.compositeAnnotationsOrSingle(CollectionsKt.listOfNotNull((Object[]) new Annotations[]{$this$enhanceInflexible.getAnnotations(), enhancedMutabilityAnnotations, enhancedNullabilityAnnotations}));
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        SimpleType enhancedType = KotlinTypeFactory.simpleType$default(newAnnotations, typeConstructor, enhancedArguments, enhancedNullability2, null, 16, null);
        UnwrappedType enhancement = effectiveQualifiers.isNotNullTypeParameter() ? notNullTypeParameter(enhancedType) : enhancedType;
        boolean nullabilityForWarning = enhancedNullabilityAnnotations != null && effectiveQualifiers.isNullabilityQualifierForWarning();
        UnwrappedType result = nullabilityForWarning ? TypeWithEnhancementKt.wrapEnhancement($this$enhanceInflexible, enhancement) : enhancement;
        return new SimpleResult((SimpleType) result, subtreeSize, true);
    }

    private final SimpleType notNullTypeParameter(SimpleType enhancedType) {
        if (this.javaResolverSettings.getCorrectNullabilityForNotNullTypeParameter()) {
            return SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(enhancedType, true);
        }
        return new NotNullTypeParameter(enhancedType);
    }
}
