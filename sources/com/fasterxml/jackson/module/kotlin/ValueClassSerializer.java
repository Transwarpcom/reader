package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.vertx.core.cli.converters.FromBasedConverter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b0\u0018�� \u0007*\b\b��\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002\u0007\bB\u0015\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0002\u0010\u0006\u0082\u0001\u0001\t¨\u0006\n"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassSerializer;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "t", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "Companion", "StaticJsonValue", "Lcom/fasterxml/jackson/module/kotlin/ValueClassSerializer$StaticJsonValue;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassSerializer.class */
public abstract class ValueClassSerializer<T> extends StdSerializer<T> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ ValueClassSerializer(Class t, DefaultConstructorMarker $constructor_marker) {
        this(t);
    }

    private ValueClassSerializer(Class<T> cls) {
        super(cls);
    }

    /* compiled from: KotlinSerializers.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassSerializer$StaticJsonValue;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lcom/fasterxml/jackson/module/kotlin/ValueClassSerializer;", "t", "Ljava/lang/Class;", "staticJsonValueGetter", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/lang/reflect/Method;)V", "unboxMethod", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "(Ljava/lang/Object;Lcom/fasterxml/jackson/core/JsonGenerator;Lcom/fasterxml/jackson/databind/SerializerProvider;)V", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassSerializer$StaticJsonValue.class */
    public static final class StaticJsonValue<T> extends ValueClassSerializer<T> {

        @NotNull
        private final Method staticJsonValueGetter;

        @NotNull
        private final Method unboxMethod;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StaticJsonValue(@NotNull Class<T> t, @NotNull Method staticJsonValueGetter) throws NoSuchMethodException, SecurityException {
            super(t, null);
            Intrinsics.checkNotNullParameter(t, "t");
            Intrinsics.checkNotNullParameter(staticJsonValueGetter, "staticJsonValueGetter");
            this.staticJsonValueGetter = staticJsonValueGetter;
            Method method = t.getMethod("unbox-impl", new Class[0]);
            Intrinsics.checkNotNullExpressionValue(method, "t.getMethod(\"unbox-impl\")");
            this.unboxMethod = method;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(@NotNull T value, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
            Unit unit;
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(gen, "gen");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Object unboxed = this.unboxMethod.invoke(value, new Object[0]);
            Object jsonValue = this.staticJsonValueGetter.invoke(null, unboxed);
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
    }

    /* compiled from: KotlinSerializers.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¨\u0006\u0007"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassSerializer$Companion;", "", "()V", FromBasedConverter.FROM, "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "t", "Ljava/lang/Class;", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueClassSerializer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final StdSerializer<?> from(@NotNull Class<?> t) throws SecurityException {
            Intrinsics.checkNotNullParameter(t, "t");
            Method it = KotlinSerializersKt.getStaticJsonValueGetter(t);
            StaticJsonValue staticJsonValue = it == null ? null : new StaticJsonValue(t, it);
            return staticJsonValue == null ? ValueClassUnboxSerializer.INSTANCE : staticJsonValue;
        }
    }
}
