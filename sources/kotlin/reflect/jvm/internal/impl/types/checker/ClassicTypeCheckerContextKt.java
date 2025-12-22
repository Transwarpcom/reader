package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Reflection;

/* compiled from: ClassicTypeCheckerContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeCheckerContextKt.class */
public final class ClassicTypeCheckerContextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String errorMessage(Object $this$errorMessage) {
        return "ClassicTypeCheckerContext couldn't handle " + Reflection.getOrCreateKotlinClass($this$errorMessage.getClass()) + ' ' + $this$errorMessage;
    }
}
