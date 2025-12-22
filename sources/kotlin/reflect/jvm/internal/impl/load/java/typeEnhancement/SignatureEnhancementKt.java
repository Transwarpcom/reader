package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: signatureEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancementKt.class */
public final class SignatureEnhancementKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNullabilityFlexible(KotlinType $this$isNullabilityFlexible) {
        UnwrappedType unwrappedTypeUnwrap = $this$isNullabilityFlexible.unwrap();
        FlexibleType flexibility = unwrappedTypeUnwrap instanceof FlexibleType ? (FlexibleType) unwrappedTypeUnwrap : null;
        return (flexibility == null || flexibility.getLowerBound().isMarkedNullable() == flexibility.getUpperBound().isMarkedNullable()) ? false : true;
    }
}
