package kotlin.reflect.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KClassImpl;
import org.jetbrains.annotations.NotNull;

/* compiled from: KClassesJvm.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u000e\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010��\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"jvmName", "", "Lkotlin/reflect/KClass;", "getJvmName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "kotlin-reflection"})
@JvmName(name = "KClassesJvm")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/KClassesJvm.class */
public final class KClassesJvm {
    @NotNull
    public static final String getJvmName(@NotNull KClass<?> jvmName) {
        Intrinsics.checkNotNullParameter(jvmName, "$this$jvmName");
        String name = ((KClassImpl) jvmName).getJClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "(this as KClassImpl).jClass.name");
        return name;
    }
}
