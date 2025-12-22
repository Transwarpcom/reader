package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SpecialTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/SpecialTypesKt.class */
public final class SpecialTypesKt {
    @Nullable
    public static final AbbreviatedType getAbbreviatedType(@NotNull KotlinType $this$getAbbreviatedType) {
        Intrinsics.checkNotNullParameter($this$getAbbreviatedType, "<this>");
        UnwrappedType unwrappedTypeUnwrap = $this$getAbbreviatedType.unwrap();
        if (unwrappedTypeUnwrap instanceof AbbreviatedType) {
            return (AbbreviatedType) unwrappedTypeUnwrap;
        }
        return null;
    }

    @Nullable
    public static final SimpleType getAbbreviation(@NotNull KotlinType $this$getAbbreviation) {
        Intrinsics.checkNotNullParameter($this$getAbbreviation, "<this>");
        AbbreviatedType abbreviatedType = getAbbreviatedType($this$getAbbreviation);
        if (abbreviatedType == null) {
            return null;
        }
        return abbreviatedType.getAbbreviation();
    }

    @NotNull
    public static final SimpleType withAbbreviation(@NotNull SimpleType $this$withAbbreviation, @NotNull SimpleType abbreviatedType) {
        Intrinsics.checkNotNullParameter($this$withAbbreviation, "<this>");
        Intrinsics.checkNotNullParameter(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError($this$withAbbreviation) ? $this$withAbbreviation : new AbbreviatedType($this$withAbbreviation, abbreviatedType);
    }

    public static final boolean isDefinitelyNotNullType(@NotNull KotlinType $this$isDefinitelyNotNullType) {
        Intrinsics.checkNotNullParameter($this$isDefinitelyNotNullType, "<this>");
        return $this$isDefinitelyNotNullType.unwrap() instanceof DefinitelyNotNullType;
    }

    public static /* synthetic */ SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull$default(SimpleType simpleType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, z);
    }

    @NotNull
    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(@NotNull SimpleType $this$makeSimpleTypeDefinitelyNotNullOrNotNull, boolean useCorrectedNullabilityForTypeParameters) {
        Intrinsics.checkNotNullParameter($this$makeSimpleTypeDefinitelyNotNullOrNotNull, "<this>");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull = DefinitelyNotNullType.Companion.makeDefinitelyNotNull($this$makeSimpleTypeDefinitelyNotNullOrNotNull, useCorrectedNullabilityForTypeParameters);
        if (definitelyNotNullTypeMakeDefinitelyNotNull != null) {
            return definitelyNotNullTypeMakeDefinitelyNotNull;
        }
        SimpleType simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull = makeIntersectionTypeDefinitelyNotNullOrNotNull($this$makeSimpleTypeDefinitelyNotNullOrNotNull);
        if (simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull != null) {
            return simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull;
        }
        return $this$makeSimpleTypeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    @NotNull
    public static final NewCapturedType withNotNullProjection(@NotNull NewCapturedType $this$withNotNullProjection) {
        Intrinsics.checkNotNullParameter($this$withNotNullProjection, "<this>");
        return new NewCapturedType($this$withNotNullProjection.getCaptureStatus(), $this$withNotNullProjection.getConstructor(), $this$withNotNullProjection.getLowerType(), $this$withNotNullProjection.getAnnotations(), $this$withNotNullProjection.isMarkedNullable(), true);
    }

    public static /* synthetic */ UnwrappedType makeDefinitelyNotNullOrNotNull$default(UnwrappedType unwrappedType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeDefinitelyNotNullOrNotNull(unwrappedType, z);
    }

    @NotNull
    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(@NotNull UnwrappedType $this$makeDefinitelyNotNullOrNotNull, boolean useCorrectedNullabilityForTypeParameters) {
        Intrinsics.checkNotNullParameter($this$makeDefinitelyNotNullOrNotNull, "<this>");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull = DefinitelyNotNullType.Companion.makeDefinitelyNotNull($this$makeDefinitelyNotNullOrNotNull, useCorrectedNullabilityForTypeParameters);
        if (definitelyNotNullTypeMakeDefinitelyNotNull != null) {
            return definitelyNotNullTypeMakeDefinitelyNotNull;
        }
        SimpleType simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull = makeIntersectionTypeDefinitelyNotNullOrNotNull($this$makeDefinitelyNotNullOrNotNull);
        if (simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull != null) {
            return simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull;
        }
        return $this$makeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType $this$makeIntersectionTypeDefinitelyNotNullOrNotNull) {
        IntersectionTypeConstructor definitelyNotNullConstructor;
        TypeConstructor constructor = $this$makeIntersectionTypeDefinitelyNotNullOrNotNull.getConstructor();
        IntersectionTypeConstructor typeConstructor = constructor instanceof IntersectionTypeConstructor ? (IntersectionTypeConstructor) constructor : null;
        if (typeConstructor == null || (definitelyNotNullConstructor = makeDefinitelyNotNullOrNotNull(typeConstructor)) == null) {
            return null;
        }
        return definitelyNotNullConstructor.createType();
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor $this$makeDefinitelyNotNullOrNotNull) {
        UnwrappedType unwrappedTypeMakeDefinitelyNotNullOrNotNull$default;
        boolean changed$iv = false;
        Iterable $this$map$iv$iv = $this$makeDefinitelyNotNullOrNotNull.mo3835getSupertypes();
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            KotlinType it$iv = (KotlinType) item$iv$iv$iv;
            if (TypeUtils.isNullableType(it$iv)) {
                changed$iv = true;
                unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = makeDefinitelyNotNullOrNotNull$default(it$iv.unwrap(), false, 1, null);
            } else {
                unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = it$iv;
            }
            destination$iv$iv$iv.add(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default);
        }
        List newSupertypes$iv = (List) destination$iv$iv$iv;
        if (!changed$iv) {
            return null;
        }
        KotlinType alternative$iv = $this$makeDefinitelyNotNullOrNotNull.getAlternativeType();
        KotlinType updatedAlternative$iv = alternative$iv == null ? null : TypeUtils.isNullableType(alternative$iv) ? makeDefinitelyNotNullOrNotNull$default(alternative$iv.unwrap(), false, 1, null) : alternative$iv;
        return new IntersectionTypeConstructor(newSupertypes$iv).setAlternative(updatedAlternative$iv);
    }
}
