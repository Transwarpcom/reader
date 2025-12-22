package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.JsonDeserializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinObjectSingletonDeserializer.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\u001a\u0018\u0010��\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H��¨\u0006\u0005"}, d2 = {"asSingletonDeserializer", "Lcom/fasterxml/jackson/module/kotlin/KotlinObjectSingletonDeserializer;", "Lcom/fasterxml/jackson/databind/JsonDeserializer;", "singleton", "", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinObjectSingletonDeserializerKt.class */
public final class KotlinObjectSingletonDeserializerKt {
    @NotNull
    public static final KotlinObjectSingletonDeserializer asSingletonDeserializer(@NotNull JsonDeserializer<?> jsonDeserializer, @NotNull Object singleton) {
        Intrinsics.checkNotNullParameter(jsonDeserializer, "<this>");
        Intrinsics.checkNotNullParameter(singleton, "singleton");
        return new KotlinObjectSingletonDeserializer(singleton, jsonDeserializer);
    }
}
