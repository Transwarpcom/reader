package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: ClassicTypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/ClassicTypeSystemContextKt.class */
public final class ClassicTypeSystemContextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final UnwrappedType makeDefinitelyNotNullOrNotNullInternal(UnwrappedType type) {
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull$default(type, false, 1, null);
    }
}
