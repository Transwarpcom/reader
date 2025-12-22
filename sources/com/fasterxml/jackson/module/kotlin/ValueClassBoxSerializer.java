package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b��\u0018��*\b\b��\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0002\u0010\u0007J'\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00018��2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0013"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassBoxSerializer;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "outerClazz", "Ljava/lang/Class;", "innerClazz", "(Ljava/lang/Class;Ljava/lang/Class;)V", "boxMethod", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "(Ljava/lang/Object;Lcom/fasterxml/jackson/core/JsonGenerator;Lcom/fasterxml/jackson/databind/SerializerProvider;)V", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassBoxSerializer.class */
public final class ValueClassBoxSerializer<T> extends StdSerializer<T> {

    @NotNull
    private final Class<? extends Object> outerClazz;
    private final Method boxMethod;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ValueClassBoxSerializer(@NotNull Class<? extends Object> outerClazz, @NotNull Class<T> innerClazz) {
        super(innerClazz);
        Intrinsics.checkNotNullParameter(outerClazz, "outerClazz");
        Intrinsics.checkNotNullParameter(innerClazz, "innerClazz");
        this.outerClazz = outerClazz;
        this.boxMethod = this.outerClazz.getMethod("box-impl", innerClazz);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@Nullable T t, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Object boxed = this.boxMethod.invoke(null, t);
        provider.findValueSerializer(this.outerClazz).serialize(boxed, gen, provider);
    }
}
