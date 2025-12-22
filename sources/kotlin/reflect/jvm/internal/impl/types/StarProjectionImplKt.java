package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StarProjectionImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/StarProjectionImplKt.class */
public final class StarProjectionImplKt {
    @NotNull
    public static final KotlinType starProjectionType(@NotNull TypeParameterDescriptor $this$starProjectionType) {
        Intrinsics.checkNotNullParameter($this$starProjectionType, "<this>");
        ClassifierDescriptorWithTypeParameters classDescriptor = (ClassifierDescriptorWithTypeParameters) $this$starProjectionType.getContainingDeclaration();
        Iterable parameters = classDescriptor.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "classDescriptor.typeConstructor.parameters");
        Iterable $this$map$iv = parameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeParameterDescriptor it = (TypeParameterDescriptor) item$iv$iv;
            destination$iv$iv.add(it.getTypeConstructor());
        }
        final List typeParameters = (List) destination$iv$iv;
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt.starProjectionType.1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            @Nullable
            public TypeProjection get(@NotNull TypeConstructor key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (typeParameters.contains(key)) {
                    ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = key.mo3831getDeclarationDescriptor();
                    if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                    }
                    return TypeUtils.makeStarProjection((TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor);
                }
                return null;
            }
        });
        List<KotlinType> upperBounds = $this$starProjectionType.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "this.upperBounds");
        KotlinType kotlinTypeSubstitute = typeSubstitutorCreate.substitute((KotlinType) CollectionsKt.first((List) upperBounds), Variance.OUT_VARIANCE);
        if (kotlinTypeSubstitute != null) {
            return kotlinTypeSubstitute;
        }
        SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns($this$starProjectionType).getDefaultBound();
        Intrinsics.checkNotNullExpressionValue(defaultBound, "builtIns.defaultBound");
        return defaultBound;
    }
}
