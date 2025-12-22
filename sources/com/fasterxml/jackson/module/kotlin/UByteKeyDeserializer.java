package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinKeyDeserializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\b\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/UByteKeyDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdKeyDeserializer;", "()V", "deserializeKey", "Lkotlin/UByte;", "key", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserializeKey-lj4SQcc", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/UByteKeyDeserializer.class */
public final class UByteKeyDeserializer extends StdKeyDeserializer {

    @NotNull
    public static final UByteKeyDeserializer INSTANCE = new UByteKeyDeserializer();

    private UByteKeyDeserializer() {
        super(3, UByte.class);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.core.exc.InputCoercionException */
    @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer, com.fasterxml.jackson.databind.KeyDeserializer
    @Nullable
    /* renamed from: deserializeKey-lj4SQcc, reason: not valid java name and merged with bridge method [inline-methods] */
    public UByte deserializeKey(@Nullable String key, @NotNull DeserializationContext ctxt) throws IOException, InputCoercionException {
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        Object it = super.deserializeKey(key, ctxt);
        if (it == null) {
            return null;
        }
        UByte uByteAsUByte = UnsignedNumbersKt.asUByte(((Short) it).shortValue());
        if (uByteAsUByte == null) {
            throw new InputCoercionException((JsonParser) null, "Numeric value (" + ((Object) key) + ") out of range of UByte (0 - 255).", JsonToken.VALUE_NUMBER_INT, UByte.class);
        }
        return UByte.m2166boximpl(uByteAsUByte.m2170unboximpl());
    }
}
