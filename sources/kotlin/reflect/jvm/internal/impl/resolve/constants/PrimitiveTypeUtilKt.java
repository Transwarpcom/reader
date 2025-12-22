package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: PrimitiveTypeUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/PrimitiveTypeUtilKt.class */
public final class PrimitiveTypeUtilKt {
    @NotNull
    public static final Collection<KotlinType> getAllSignedLiteralTypes(@NotNull ModuleDescriptor $this$allSignedLiteralTypes) {
        Intrinsics.checkNotNullParameter($this$allSignedLiteralTypes, "<this>");
        return CollectionsKt.listOf((Object[]) new SimpleType[]{$this$allSignedLiteralTypes.getBuiltIns().getIntType(), $this$allSignedLiteralTypes.getBuiltIns().getLongType(), $this$allSignedLiteralTypes.getBuiltIns().getByteType(), $this$allSignedLiteralTypes.getBuiltIns().getShortType()});
    }
}
