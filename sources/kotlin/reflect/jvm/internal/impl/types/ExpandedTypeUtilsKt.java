package kotlin.reflect.jvm.internal.impl.types;

import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: expandedTypeUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ExpandedTypeUtilsKt.class */
public final class ExpandedTypeUtilsKt {
    @Nullable
    public static final KotlinTypeMarker computeExpandedTypeForInlineClass(@NotNull TypeSystemCommonBackendContext $this$computeExpandedTypeForInlineClass, @NotNull KotlinTypeMarker inlineClassType) {
        Intrinsics.checkNotNullParameter($this$computeExpandedTypeForInlineClass, "<this>");
        Intrinsics.checkNotNullParameter(inlineClassType, "inlineClassType");
        return computeExpandedTypeInner($this$computeExpandedTypeForInlineClass, inlineClassType, new HashSet());
    }

    private static final KotlinTypeMarker computeExpandedTypeInner(TypeSystemCommonBackendContext $this$computeExpandedTypeInner, KotlinTypeMarker kotlinType, HashSet<TypeConstructorMarker> hashSet) {
        KotlinTypeMarker expandedUnderlyingType;
        TypeConstructorMarker classifier = $this$computeExpandedTypeInner.typeConstructor(kotlinType);
        if (!hashSet.add(classifier)) {
            return null;
        }
        TypeParameterMarker typeParameter = $this$computeExpandedTypeInner.getTypeParameterClassifier(classifier);
        if (typeParameter != null) {
            KotlinTypeMarker expandedUpperBound = computeExpandedTypeInner($this$computeExpandedTypeInner, $this$computeExpandedTypeInner.getRepresentativeUpperBound(typeParameter), hashSet);
            if (expandedUpperBound == null) {
                return null;
            }
            if ($this$computeExpandedTypeInner.isNullableType(expandedUpperBound) || !$this$computeExpandedTypeInner.isMarkedNullable(kotlinType)) {
                return expandedUpperBound;
            }
            return $this$computeExpandedTypeInner.makeNullable(expandedUpperBound);
        }
        if ($this$computeExpandedTypeInner.isInlineClass(classifier)) {
            KotlinTypeMarker underlyingType = $this$computeExpandedTypeInner.getSubstitutedUnderlyingType(kotlinType);
            if (underlyingType == null || (expandedUnderlyingType = computeExpandedTypeInner($this$computeExpandedTypeInner, underlyingType, hashSet)) == null) {
                return null;
            }
            return !$this$computeExpandedTypeInner.isNullableType(kotlinType) ? expandedUnderlyingType : $this$computeExpandedTypeInner.isNullableType(expandedUnderlyingType) ? kotlinType : ((expandedUnderlyingType instanceof SimpleTypeMarker) && $this$computeExpandedTypeInner.isPrimitiveType((SimpleTypeMarker) expandedUnderlyingType)) ? kotlinType : $this$computeExpandedTypeInner.makeNullable(expandedUnderlyingType);
        }
        return kotlinType;
    }
}
