package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinValueInstantiator.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b��\u0018��2\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinInstantiators;", "Lcom/fasterxml/jackson/databind/deser/ValueInstantiators;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "strictNullChecks", "(Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZZ)V", "findValueInstantiator", "Lcom/fasterxml/jackson/databind/deser/ValueInstantiator;", "deserConfig", "Lcom/fasterxml/jackson/databind/DeserializationConfig;", "beanDescriptor", "Lcom/fasterxml/jackson/databind/BeanDescription;", "defaultInstantiator", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinInstantiators.class */
public final class KotlinInstantiators implements ValueInstantiators {

    @NotNull
    private final ReflectionCache cache;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean nullIsSameAsDefault;
    private final boolean strictNullChecks;

    public KotlinInstantiators(@NotNull ReflectionCache cache, boolean nullToEmptyCollection, boolean nullToEmptyMap, boolean nullIsSameAsDefault, boolean strictNullChecks) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.cache = cache;
        this.nullToEmptyCollection = nullToEmptyCollection;
        this.nullToEmptyMap = nullToEmptyMap;
        this.nullIsSameAsDefault = nullIsSameAsDefault;
        this.strictNullChecks = strictNullChecks;
    }

    @Override // com.fasterxml.jackson.databind.deser.ValueInstantiators
    @NotNull
    public ValueInstantiator findValueInstantiator(@NotNull DeserializationConfig deserConfig, @NotNull BeanDescription beanDescriptor, @NotNull ValueInstantiator defaultInstantiator) {
        Intrinsics.checkNotNullParameter(deserConfig, "deserConfig");
        Intrinsics.checkNotNullParameter(beanDescriptor, "beanDescriptor");
        Intrinsics.checkNotNullParameter(defaultInstantiator, "defaultInstantiator");
        Class<?> beanClass = beanDescriptor.getBeanClass();
        Intrinsics.checkNotNullExpressionValue(beanClass, "beanDescriptor.beanClass");
        if (KotlinModuleKt.isKotlinClass(beanClass)) {
            if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(defaultInstantiator.getClass()), Reflection.getOrCreateKotlinClass(StdValueInstantiator.class))) {
                return new KotlinValueInstantiator((StdValueInstantiator) defaultInstantiator, this.cache, this.nullToEmptyCollection, this.nullToEmptyMap, this.nullIsSameAsDefault, this.strictNullChecks);
            }
            throw new IllegalStateException("KotlinValueInstantiator requires that the default ValueInstantiator is StdValueInstantiator");
        }
        return defaultInstantiator;
    }
}
