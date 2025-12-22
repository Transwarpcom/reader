package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnchancementUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/TypeEnchancementUtilsKt.class */
public final class TypeEnchancementUtilsKt {
    @NotNull
    public static final JavaTypeQualifiers createJavaTypeQualifiers(@Nullable NullabilityQualifier nullability, @Nullable MutabilityQualifier mutability, boolean forWarning, boolean isAnyNonNullTypeParameter) {
        if (!isAnyNonNullTypeParameter || nullability != NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullability, mutability, false, forWarning);
        }
        return new JavaTypeQualifiers(nullability, mutability, true, forWarning);
    }

    @Nullable
    public static final <T> T select(@NotNull Set<? extends T> set, @NotNull T low, @NotNull T high, @Nullable T t, boolean z) {
        Set<? extends T> set2;
        Intrinsics.checkNotNullParameter(set, "<this>");
        Intrinsics.checkNotNullParameter(low, "low");
        Intrinsics.checkNotNullParameter(high, "high");
        if (z) {
            T t2 = set.contains(low) ? low : set.contains(high) ? high : null;
            if (Intrinsics.areEqual(t2, low) && Intrinsics.areEqual(t, high)) {
                return null;
            }
            return t == null ? t2 : t;
        }
        if (t == null) {
            set2 = set;
        } else {
            Set<? extends T> set3 = CollectionsKt.toSet(SetsKt.plus(set, t));
            set2 = set3 == null ? set : set3;
        }
        return (T) CollectionsKt.singleOrNull(set2);
    }

    @Nullable
    public static final NullabilityQualifier select(@NotNull Set<? extends NullabilityQualifier> set, @Nullable NullabilityQualifier own, boolean isCovariant) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        if (own == NullabilityQualifier.FORCE_FLEXIBILITY) {
            return NullabilityQualifier.FORCE_FLEXIBILITY;
        }
        return (NullabilityQualifier) select(set, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, own, isCovariant);
    }

    public static final boolean hasEnhancedNullability(@NotNull TypeSystemCommonBackendContext $this$hasEnhancedNullability, @NotNull KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter($this$hasEnhancedNullability, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        FqName ENHANCED_NULLABILITY_ANNOTATION = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(ENHANCED_NULLABILITY_ANNOTATION, "ENHANCED_NULLABILITY_ANNOTATION");
        return $this$hasEnhancedNullability.hasAnnotation(type, ENHANCED_NULLABILITY_ANNOTATION);
    }
}
