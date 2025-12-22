package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fø\u0001��\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/UShortSerializer;", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "Lkotlin/UShort;", "()V", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "serialize-_TFR7lA", "(SLcom/fasterxml/jackson/core/JsonGenerator;Lcom/fasterxml/jackson/databind/SerializerProvider;)V", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/UShortSerializer.class */
public final class UShortSerializer extends StdSerializer<UShort> {

    @NotNull
    public static final UShortSerializer INSTANCE = new UShortSerializer();

    private UShortSerializer() {
        super(UShort.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public /* bridge */ /* synthetic */ void serialize(Object p0, JsonGenerator p1, SerializerProvider p2) throws IOException {
        m522serialize_TFR7lA(((UShort) p0).m2434unboximpl(), p1, p2);
    }

    /* renamed from: serialize-_TFR7lA, reason: not valid java name */
    public void m522serialize_TFR7lA(short value, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IOException {
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        gen.writeNumber(value & 65535);
    }
}
