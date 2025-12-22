package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinSerializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinSerializers;", "Lcom/fasterxml/jackson/databind/ser/Serializers$Base;", "()V", "findSerializer", "Lcom/fasterxml/jackson/databind/JsonSerializer;", "config", "Lcom/fasterxml/jackson/databind/SerializationConfig;", "type", "Lcom/fasterxml/jackson/databind/JavaType;", "beanDesc", "Lcom/fasterxml/jackson/databind/BeanDescription;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinSerializers.class */
public final class KotlinSerializers extends Serializers.Base {
    @Override // com.fasterxml.jackson.databind.ser.Serializers.Base, com.fasterxml.jackson.databind.ser.Serializers
    @Nullable
    public JsonSerializer<?> findSerializer(@Nullable SerializationConfig config, @NotNull JavaType type, @Nullable BeanDescription beanDesc) {
        Intrinsics.checkNotNullParameter(type, "type");
        Class rawClass = type.getRawClass();
        if (Sequence.class.isAssignableFrom(rawClass)) {
            return SequenceSerializer.INSTANCE;
        }
        if (UByte.class.isAssignableFrom(rawClass)) {
            return UByteSerializer.INSTANCE;
        }
        if (UShort.class.isAssignableFrom(rawClass)) {
            return UShortSerializer.INSTANCE;
        }
        if (UInt.class.isAssignableFrom(rawClass)) {
            return UIntSerializer.INSTANCE;
        }
        if (ULong.class.isAssignableFrom(rawClass)) {
            return ULongSerializer.INSTANCE;
        }
        Intrinsics.checkNotNullExpressionValue(rawClass, "rawClass");
        if (ExtensionsKt.isUnboxableValueClass(rawClass)) {
            return ValueClassSerializer.Companion.from(rawClass);
        }
        return null;
    }
}
