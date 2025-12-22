package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinDeserializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\t\u0010\nø\u0001��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/UIntDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/UInt;", "()V", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserialize-xfHcF5w", "(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)I", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/UIntDeserializer.class */
public final class UIntDeserializer extends StdDeserializer<UInt> {

    @NotNull
    public static final UIntDeserializer INSTANCE = new UIntDeserializer();

    private UIntDeserializer() {
        super((Class<?>) UInt.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser p0, DeserializationContext p1) {
        return UInt.m2245boximpl(m506deserializexfHcF5w(p0, p1));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.core.exc.InputCoercionException */
    /* renamed from: deserialize-xfHcF5w, reason: not valid java name */
    public int m506deserializexfHcF5w(@NotNull JsonParser p, @NotNull DeserializationContext ctxt) throws InputCoercionException {
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        UInt uIntAsUInt = UnsignedNumbersKt.asUInt(p.getLongValue());
        if (uIntAsUInt == null) {
            throw new InputCoercionException(p, "Numeric value (" + ((Object) p.getText()) + ") out of range of UInt (0 - 4294967295).", JsonToken.VALUE_NUMBER_INT, UInt.class);
        }
        return uIntAsUInt.m2249unboximpl();
    }
}
