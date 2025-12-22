package com.fasterxml.jackson.module.kotlin;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;

/* compiled from: KotlinBeanDeserializerModifier.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\u001a\u0016\u0010��\u001a\u0004\u0018\u00010\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002¨\u0006\u0004"}, d2 = {"objectSingletonInstance", "", "beanClass", "Ljava/lang/Class;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinBeanDeserializerModifierKt.class */
public final class KotlinBeanDeserializerModifierKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object objectSingletonInstance(Class<?> cls) {
        if (!KotlinModuleKt.isKotlinClass(cls)) {
            return null;
        }
        return JvmClassMappingKt.getKotlinClass(cls).getObjectInstance();
    }
}
