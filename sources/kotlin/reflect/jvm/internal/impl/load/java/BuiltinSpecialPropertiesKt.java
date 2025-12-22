package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: BuiltinSpecialProperties.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/BuiltinSpecialPropertiesKt.class */
public final class BuiltinSpecialPropertiesKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName child(FqName $this$child, String name) {
        FqName fqNameChild = $this$child.child(Name.identifier(name));
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "child(Name.identifier(name))");
        return fqNameChild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName childSafe(FqNameUnsafe $this$childSafe, String name) {
        FqName safe = $this$childSafe.child(Name.identifier(name)).toSafe();
        Intrinsics.checkNotNullExpressionValue(safe, "child(Name.identifier(name)).toSafe()");
        return safe;
    }
}
