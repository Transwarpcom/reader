package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinClassFinder.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/KotlinClassFinderKt.class */
public final class KotlinClassFinderKt {
    @Nullable
    public static final KotlinJvmBinaryClass findKotlinClass(@NotNull KotlinClassFinder $this$findKotlinClass, @NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter($this$findKotlinClass, "<this>");
        Intrinsics.checkNotNullParameter(classId, "classId");
        KotlinClassFinder.Result resultFindKotlinClassOrContent = $this$findKotlinClass.findKotlinClassOrContent(classId);
        if (resultFindKotlinClassOrContent == null) {
            return null;
        }
        return resultFindKotlinClassOrContent.toKotlinJvmBinaryClass();
    }

    @Nullable
    public static final KotlinJvmBinaryClass findKotlinClass(@NotNull KotlinClassFinder $this$findKotlinClass, @NotNull JavaClass javaClass) {
        Intrinsics.checkNotNullParameter($this$findKotlinClass, "<this>");
        Intrinsics.checkNotNullParameter(javaClass, "javaClass");
        KotlinClassFinder.Result resultFindKotlinClassOrContent = $this$findKotlinClass.findKotlinClassOrContent(javaClass);
        if (resultFindKotlinClassOrContent == null) {
            return null;
        }
        return resultFindKotlinClassOrContent.toKotlinJvmBinaryClass();
    }
}
