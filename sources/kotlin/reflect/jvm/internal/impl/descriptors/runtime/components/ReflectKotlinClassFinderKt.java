package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;

/* compiled from: ReflectKotlinClassFinder.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClassFinderKt.class */
public final class ReflectKotlinClassFinderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId $this$toRuntimeFqName) {
        String strAsString = $this$toRuntimeFqName.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "relativeClassName.asString()");
        String className = StringsKt.replace$default(strAsString, '.', '$', false, 4, (Object) null);
        return $this$toRuntimeFqName.getPackageFqName().isRoot() ? className : $this$toRuntimeFqName.getPackageFqName() + '.' + className;
    }
}
