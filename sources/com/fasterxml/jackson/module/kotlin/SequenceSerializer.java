package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/SequenceSerializer;", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "Lkotlin/sequences/Sequence;", "()V", "serialize", "", "value", "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/SequenceSerializer.class */
public final class SequenceSerializer extends StdSerializer<Sequence<?>> {

    @NotNull
    public static final SequenceSerializer INSTANCE = new SequenceSerializer();

    private SequenceSerializer() {
        super(Sequence.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@NotNull Sequence<?> value, @NotNull JsonGenerator gen, @NotNull SerializerProvider provider) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        List materializedList = SequencesKt.toList(value);
        provider.defaultSerializeValue(materializedList, gen);
    }
}
