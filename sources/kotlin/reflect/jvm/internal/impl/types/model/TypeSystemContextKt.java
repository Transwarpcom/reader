package kotlin.reflect.jvm.internal.impl.types.model;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/TypeSystemContextKt.class */
public final class TypeSystemContextKt {

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/TypeSystemContextKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            iArr[Variance.INVARIANT.ordinal()] = 1;
            iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public static final TypeVariance convertVariance(@NotNull Variance $this$convertVariance) {
        Intrinsics.checkNotNullParameter($this$convertVariance, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$convertVariance.ordinal()]) {
            case 1:
                return TypeVariance.INV;
            case 2:
                return TypeVariance.IN;
            case 3:
                return TypeVariance.OUT;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
