package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinKeyDeserializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\b\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ULongKeyDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdKeyDeserializer;", "()V", "deserializeKey", "Lkotlin/ULong;", "key", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserializeKey-woJcscw", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ULongKeyDeserializer.class */
public final class ULongKeyDeserializer extends StdKeyDeserializer {

    @NotNull
    public static final ULongKeyDeserializer INSTANCE = new ULongKeyDeserializer();

    private ULongKeyDeserializer() {
        super(6, ULong.class);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.core.exc.InputCoercionException */
    @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer, com.fasterxml.jackson.databind.KeyDeserializer
    @Nullable
    /* renamed from: deserializeKey-woJcscw, reason: not valid java name and merged with bridge method [inline-methods] */
    public ULong deserializeKey(@Nullable String key, @NotNull DeserializationContext ctxt) throws InputCoercionException {
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        if (key == null) {
            return null;
        }
        ULong uLongAsULong = UnsignedNumbersKt.asULong(new BigInteger(key));
        if (uLongAsULong == null) {
            throw new InputCoercionException((JsonParser) null, "Numeric value (" + ((Object) key) + ") out of range of ULong (0 - 18446744073709551615).", JsonToken.VALUE_NUMBER_INT, ULong.class);
        }
        return ULong.m2324boximpl(uLongAsULong.m2328unboximpl());
    }
}
