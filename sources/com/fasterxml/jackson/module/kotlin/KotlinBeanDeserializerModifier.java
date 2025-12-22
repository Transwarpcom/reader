package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinBeanDeserializerModifier.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinBeanDeserializerModifier;", "Lcom/fasterxml/jackson/databind/deser/BeanDeserializerModifier;", "()V", "modifyDeserializer", "Lcom/fasterxml/jackson/databind/JsonDeserializer;", "", "config", "Lcom/fasterxml/jackson/databind/DeserializationConfig;", "beanDesc", "Lcom/fasterxml/jackson/databind/BeanDescription;", "deserializer", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinBeanDeserializerModifier.class */
public final class KotlinBeanDeserializerModifier extends BeanDeserializerModifier {

    @NotNull
    public static final KotlinBeanDeserializerModifier INSTANCE = new KotlinBeanDeserializerModifier();

    private KotlinBeanDeserializerModifier() {
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
    @NotNull
    public JsonDeserializer<? extends Object> modifyDeserializer(@NotNull DeserializationConfig config, @NotNull BeanDescription beanDesc, @NotNull JsonDeserializer<?> deserializer) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(beanDesc, "beanDesc");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        JsonDeserializer modifiedFromParent = super.modifyDeserializer(config, beanDesc, deserializer);
        Class<?> beanClass = beanDesc.getBeanClass();
        Intrinsics.checkNotNullExpressionValue(beanClass, "beanDesc.beanClass");
        Object objectSingletonInstance = KotlinBeanDeserializerModifierKt.objectSingletonInstance(beanClass);
        if (objectSingletonInstance != null) {
            Intrinsics.checkNotNullExpressionValue(modifiedFromParent, "modifiedFromParent");
            return new KotlinObjectSingletonDeserializer(objectSingletonInstance, modifiedFromParent);
        }
        Intrinsics.checkNotNullExpressionValue(modifiedFromParent, "{\n            modifiedFromParent\n        }");
        return modifiedFromParent;
    }
}
