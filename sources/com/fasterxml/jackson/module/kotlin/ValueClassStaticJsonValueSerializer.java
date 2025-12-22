package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b��\u0018�� \u0010*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0010B\u001d\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J'\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00018��2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassStaticJsonValueSerializer;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "innerClazz", "Ljava/lang/Class;", "staticJsonValueGetter", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/lang/reflect/Method;)V", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "(Ljava/lang/Object;Lcom/fasterxml/jackson/core/JsonGenerator;Lcom/fasterxml/jackson/databind/SerializerProvider;)V", "Companion", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassStaticJsonValueSerializer.class */
public final class ValueClassStaticJsonValueSerializer<T> extends StdSerializer<T> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Method staticJsonValueGetter;

    public /* synthetic */ ValueClassStaticJsonValueSerializer(Class innerClazz, Method staticJsonValueGetter, DefaultConstructorMarker $constructor_marker) {
        this(innerClazz, staticJsonValueGetter);
    }

    private ValueClassStaticJsonValueSerializer(Class<T> cls, Method staticJsonValueGetter) {
        super(cls);
        this.staticJsonValueGetter = staticJsonValueGetter;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@Nullable T t, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Unit unit;
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Object jsonValue = this.staticJsonValueGetter.invoke(null, t);
        if (jsonValue == null) {
            unit = null;
        } else {
            provider.findValueSerializer(jsonValue.getClass()).serialize(jsonValue, gen, provider);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            provider.findNullValueSerializer(null).serialize(null, gen, provider);
        }
    }

    /* compiled from: KotlinSerializers.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\u0004\b\u0001\u0010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007¨\u0006\t"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassStaticJsonValueSerializer$Companion;", "", "()V", "createdOrNull", "Lcom/fasterxml/jackson/module/kotlin/ValueClassStaticJsonValueSerializer;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "outerClazz", "Ljava/lang/Class;", "innerClazz", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassStaticJsonValueSerializer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final <T> ValueClassStaticJsonValueSerializer<T> createdOrNull(@NotNull Class<? extends Object> outerClazz, @NotNull Class<T> innerClazz) throws SecurityException {
            Intrinsics.checkNotNullParameter(outerClazz, "outerClazz");
            Intrinsics.checkNotNullParameter(innerClazz, "innerClazz");
            Method it = KotlinSerializersKt.getStaticJsonValueGetter(outerClazz);
            if (it == null) {
                return null;
            }
            return new ValueClassStaticJsonValueSerializer<>(innerClazz, it, null);
        }
    }
}
