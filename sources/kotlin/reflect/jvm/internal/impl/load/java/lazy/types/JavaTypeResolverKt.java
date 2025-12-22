package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaTypeResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/types/JavaTypeResolverKt.class */
public final class JavaTypeResolverKt {

    @NotNull
    private static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    @NotNull
    public static final TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor typeParameter, @NotNull JavaTypeAttributes attr) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(attr, "attr");
        if (attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameter));
        }
        return new StarProjectionImpl(typeParameter);
    }

    public static /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }

    @NotNull
    public static final JavaTypeAttributes toAttributes(@NotNull TypeUsage $this$toAttributes, boolean isForAnnotationParameter, @Nullable TypeParameterDescriptor upperBoundForTypeParameter) {
        Set of;
        Intrinsics.checkNotNullParameter($this$toAttributes, "<this>");
        TypeUsage typeUsage = $this$toAttributes;
        JavaTypeFlexibility javaTypeFlexibility = null;
        boolean z = isForAnnotationParameter;
        if (upperBoundForTypeParameter == null) {
            of = null;
        } else {
            typeUsage = typeUsage;
            javaTypeFlexibility = null;
            z = z;
            of = SetsKt.setOf(upperBoundForTypeParameter);
        }
        return new JavaTypeAttributes(typeUsage, javaTypeFlexibility, z, of, 2, null);
    }

    public static /* synthetic */ KotlinType getErasedUpperBound$default(final TypeParameterDescriptor typeParameterDescriptor, boolean z, JavaTypeAttributes javaTypeAttributes, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt.getErasedUpperBound.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final SimpleType invoke() {
                    SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Can't compute erased upper bound of type parameter `" + typeParameterDescriptor + '`');
                    Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "createErrorType(\"Can't compute erased upper bound of type parameter `$this`\")");
                    return simpleTypeCreateErrorType;
                }
            };
        }
        return getErasedUpperBound(typeParameterDescriptor, z, javaTypeAttributes, function0);
    }

    @NotNull
    public static final KotlinType getErasedUpperBound(@NotNull TypeParameterDescriptor $this$getErasedUpperBound, boolean isRaw, @NotNull JavaTypeAttributes typeAttr, @NotNull Function0<? extends KotlinType> defaultValue) {
        TypeProjection typeProjectionComputeProjection;
        Intrinsics.checkNotNullParameter($this$getErasedUpperBound, "<this>");
        Intrinsics.checkNotNullParameter(typeAttr, "typeAttr");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Set visitedTypeParameters = typeAttr.getVisitedTypeParameters();
        if (visitedTypeParameters != null && visitedTypeParameters.contains($this$getErasedUpperBound.getOriginal())) {
            return defaultValue.invoke();
        }
        SimpleType defaultType = $this$getErasedUpperBound.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "defaultType");
        Iterable $this$associate$iv = TypeUtilsKt.extractTypeParametersFromUpperBounds(defaultType, visitedTypeParameters);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            TypeParameterDescriptor it = (TypeParameterDescriptor) element$iv$iv;
            if (visitedTypeParameters == null || !visitedTypeParameters.contains(it)) {
                typeProjectionComputeProjection = RawSubstitution.INSTANCE.computeProjection(it, isRaw ? typeAttr : typeAttr.withFlexibility(JavaTypeFlexibility.INFLEXIBLE), getErasedUpperBound$default(it, isRaw, typeAttr.withNewVisitedTypeParameter($this$getErasedUpperBound), null, 4, null));
            } else {
                typeProjectionComputeProjection = makeStarProjection(it, typeAttr);
            }
            TypeProjection boundProjection = typeProjectionComputeProjection;
            Pair pair = TuplesKt.to(it.getTypeConstructor(), boundProjection);
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        TypeSubstitutor erasedUpperBoundsSubstitutor = TypeSubstitutor.create(TypeConstructorSubstitution.Companion.createByConstructorsMap$default(TypeConstructorSubstitution.Companion, destination$iv$iv, false, 2, null));
        Intrinsics.checkNotNullExpressionValue(erasedUpperBoundsSubstitutor, "create(TypeConstructorSubstitution.createByConstructorsMap(erasedUpperBounds))");
        List<KotlinType> upperBounds = $this$getErasedUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        KotlinType firstUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds);
        if (firstUpperBound.getConstructor().mo3831getDeclarationDescriptor() instanceof ClassDescriptor) {
            Intrinsics.checkNotNullExpressionValue(firstUpperBound, "firstUpperBound");
            return TypeUtilsKt.replaceArgumentsWithStarProjectionOrMapped(firstUpperBound, erasedUpperBoundsSubstitutor, destination$iv$iv, Variance.OUT_VARIANCE, typeAttr.getVisitedTypeParameters());
        }
        Set visitedTypeParameters2 = typeAttr.getVisitedTypeParameters();
        Set stopAt = visitedTypeParameters2 == null ? SetsKt.setOf($this$getErasedUpperBound) : visitedTypeParameters2;
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = firstUpperBound.getConstructor().mo3831getDeclarationDescriptor();
        if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        }
        ClassifierDescriptor classifierDescriptor = classifierDescriptorMo3831getDeclarationDescriptor;
        while (true) {
            TypeParameterDescriptor current = (TypeParameterDescriptor) classifierDescriptor;
            if (!stopAt.contains(current)) {
                List<KotlinType> upperBounds2 = current.getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds2, "current.upperBounds");
                KotlinType nextUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds2);
                if (nextUpperBound.getConstructor().mo3831getDeclarationDescriptor() instanceof ClassDescriptor) {
                    Intrinsics.checkNotNullExpressionValue(nextUpperBound, "nextUpperBound");
                    return TypeUtilsKt.replaceArgumentsWithStarProjectionOrMapped(nextUpperBound, erasedUpperBoundsSubstitutor, destination$iv$iv, Variance.OUT_VARIANCE, typeAttr.getVisitedTypeParameters());
                }
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor2 = nextUpperBound.getConstructor().mo3831getDeclarationDescriptor();
                if (classifierDescriptorMo3831getDeclarationDescriptor2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                }
                classifierDescriptor = classifierDescriptorMo3831getDeclarationDescriptor2;
            } else {
                return defaultValue.invoke();
            }
        }
    }
}
