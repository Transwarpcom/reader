package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractStrictEqualityTypeChecker.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractStrictEqualityTypeChecker.class */
public final class AbstractStrictEqualityTypeChecker {

    @NotNull
    public static final AbstractStrictEqualityTypeChecker INSTANCE = new AbstractStrictEqualityTypeChecker();

    private AbstractStrictEqualityTypeChecker() {
    }

    public final boolean strictEqualTypes(@NotNull TypeSystemContext context, @NotNull KotlinTypeMarker a, @NotNull KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return strictEqualTypesInternal(context, a, b);
    }

    private final boolean strictEqualTypesInternal(TypeSystemContext $this$strictEqualTypesInternal, KotlinTypeMarker a, KotlinTypeMarker b) {
        if (a == b) {
            return true;
        }
        SimpleTypeMarker simpleA = $this$strictEqualTypesInternal.asSimpleType(a);
        SimpleTypeMarker simpleB = $this$strictEqualTypesInternal.asSimpleType(b);
        if (simpleA != null && simpleB != null) {
            return strictEqualSimpleTypes($this$strictEqualTypesInternal, simpleA, simpleB);
        }
        FlexibleTypeMarker flexibleA = $this$strictEqualTypesInternal.asFlexibleType(a);
        FlexibleTypeMarker flexibleB = $this$strictEqualTypesInternal.asFlexibleType(b);
        return flexibleA != null && flexibleB != null && strictEqualSimpleTypes($this$strictEqualTypesInternal, $this$strictEqualTypesInternal.lowerBound(flexibleA), $this$strictEqualTypesInternal.lowerBound(flexibleB)) && strictEqualSimpleTypes($this$strictEqualTypesInternal, $this$strictEqualTypesInternal.upperBound(flexibleA), $this$strictEqualTypesInternal.upperBound(flexibleB));
    }

    private final boolean strictEqualSimpleTypes(TypeSystemContext $this$strictEqualSimpleTypes, SimpleTypeMarker a, SimpleTypeMarker b) {
        if ($this$strictEqualSimpleTypes.argumentsCount(a) == $this$strictEqualSimpleTypes.argumentsCount(b) && $this$strictEqualSimpleTypes.isMarkedNullable(a) == $this$strictEqualSimpleTypes.isMarkedNullable(b)) {
            if (($this$strictEqualSimpleTypes.asDefinitelyNotNullType(a) == null) != ($this$strictEqualSimpleTypes.asDefinitelyNotNullType(b) == null) || !$this$strictEqualSimpleTypes.areEqualTypeConstructors($this$strictEqualSimpleTypes.typeConstructor(a), $this$strictEqualSimpleTypes.typeConstructor(b))) {
                return false;
            }
            if ($this$strictEqualSimpleTypes.identicalArguments(a, b)) {
                return true;
            }
            int i = 0;
            int iArgumentsCount = $this$strictEqualSimpleTypes.argumentsCount(a);
            if (0 < iArgumentsCount) {
                do {
                    int i2 = i;
                    i++;
                    TypeArgumentMarker aArg = $this$strictEqualSimpleTypes.getArgument(a, i2);
                    TypeArgumentMarker bArg = $this$strictEqualSimpleTypes.getArgument(b, i2);
                    if ($this$strictEqualSimpleTypes.isStarProjection(aArg) != $this$strictEqualSimpleTypes.isStarProjection(bArg)) {
                        return false;
                    }
                    if (!$this$strictEqualSimpleTypes.isStarProjection(aArg) && ($this$strictEqualSimpleTypes.getVariance(aArg) != $this$strictEqualSimpleTypes.getVariance(bArg) || !strictEqualTypesInternal($this$strictEqualSimpleTypes, $this$strictEqualSimpleTypes.getType(aArg), $this$strictEqualSimpleTypes.getType(bArg)))) {
                        return false;
                    }
                } while (i < iArgumentsCount);
                return true;
            }
            return true;
        }
        return false;
    }
}
