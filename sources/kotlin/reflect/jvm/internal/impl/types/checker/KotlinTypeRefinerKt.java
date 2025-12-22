package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeRefiner.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/KotlinTypeRefinerKt.class */
public final class KotlinTypeRefinerKt {

    @NotNull
    private static final ModuleCapability<Ref<KotlinTypeRefiner>> REFINER_CAPABILITY = new ModuleCapability<>("KotlinTypeRefiner");

    @NotNull
    public static final List<KotlinType> refineTypes(@NotNull KotlinTypeRefiner $this$refineTypes, @NotNull Iterable<? extends KotlinType> types) {
        Intrinsics.checkNotNullParameter($this$refineTypes, "<this>");
        Intrinsics.checkNotNullParameter(types, "types");
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
        for (Object item$iv$iv : types) {
            KotlinType it = (KotlinType) item$iv$iv;
            destination$iv$iv.add($this$refineTypes.refineType(it));
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final ModuleCapability<Ref<KotlinTypeRefiner>> getREFINER_CAPABILITY() {
        return REFINER_CAPABILITY;
    }
}
