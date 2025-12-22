package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassUnboxSerializer;", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "", "()V", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassUnboxSerializer.class */
public final class ValueClassUnboxSerializer extends StdSerializer<Object> {

    @NotNull
    public static final ValueClassUnboxSerializer INSTANCE = new ValueClassUnboxSerializer();

    private ValueClassUnboxSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@NotNull Object value, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Object unboxed = value.getClass().getMethod("unbox-impl", new Class[0]).invoke(value, new Object[0]);
        if (unboxed == null) {
            provider.findNullValueSerializer(null).serialize(null, gen, provider);
        } else {
            provider.findValueSerializer(unboxed.getClass()).serialize(unboxed, gen, provider);
        }
    }
}
