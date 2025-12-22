package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinDeserializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\t\u0010\nø\u0001��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ULongDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/ULong;", "()V", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserialize-ZIaKswc", "(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)J", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ULongDeserializer.class */
public final class ULongDeserializer extends StdDeserializer<ULong> {

    @NotNull
    public static final ULongDeserializer INSTANCE = new ULongDeserializer();

    private ULongDeserializer() {
        super((Class<?>) ULong.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser p0, DeserializationContext p1) {
        return ULong.m2324boximpl(m512deserializeZIaKswc(p0, p1));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.core.exc.InputCoercionException */
    /* renamed from: deserialize-ZIaKswc, reason: not valid java name */
    public long m512deserializeZIaKswc(@NotNull JsonParser p, @NotNull DeserializationContext ctxt) throws IOException, InputCoercionException {
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        BigInteger bigIntegerValue = p.getBigIntegerValue();
        Intrinsics.checkNotNullExpressionValue(bigIntegerValue, "p.bigIntegerValue");
        ULong uLongAsULong = UnsignedNumbersKt.asULong(bigIntegerValue);
        if (uLongAsULong == null) {
            throw new InputCoercionException(p, "Numeric value (" + ((Object) p.getText()) + ") out of range of ULong (0 - 18446744073709551615).", JsonToken.VALUE_NUMBER_INT, ULong.class);
        }
        return uLongAsULong.m2328unboximpl();
    }
}
