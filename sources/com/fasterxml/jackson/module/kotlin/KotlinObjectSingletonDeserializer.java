package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinObjectSingletonDeserializer.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\b��\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0002\u0010\u0007J \u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00012\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinObjectSingletonDeserializer;", "Lcom/fasterxml/jackson/databind/JsonDeserializer;", "", "Lcom/fasterxml/jackson/databind/deser/ContextualDeserializer;", "Lcom/fasterxml/jackson/databind/deser/ResolvableDeserializer;", "singletonInstance", "defaultDeserializer", "(Ljava/lang/Object;Lcom/fasterxml/jackson/databind/JsonDeserializer;)V", "createContextual", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "property", "Lcom/fasterxml/jackson/databind/BeanProperty;", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "resolve", "", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinObjectSingletonDeserializer.class */
public final class KotlinObjectSingletonDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {

    @NotNull
    private final Object singletonInstance;

    @NotNull
    private final JsonDeserializer<?> defaultDeserializer;

    public KotlinObjectSingletonDeserializer(@NotNull Object singletonInstance, @NotNull JsonDeserializer<?> defaultDeserializer) {
        Intrinsics.checkNotNullParameter(singletonInstance, "singletonInstance");
        Intrinsics.checkNotNullParameter(defaultDeserializer, "defaultDeserializer");
        this.singletonInstance = singletonInstance;
        this.defaultDeserializer = defaultDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(@Nullable DeserializationContext ctxt) throws JsonMappingException {
        if (this.defaultDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) this.defaultDeserializer).resolve(ctxt);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    @NotNull
    public JsonDeserializer<?> createContextual(@Nullable DeserializationContext ctxt, @Nullable BeanProperty property) throws JsonMappingException {
        if (this.defaultDeserializer instanceof ContextualDeserializer) {
            JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) this.defaultDeserializer).createContextual(ctxt, property);
            Intrinsics.checkNotNullExpressionValue(jsonDeserializerCreateContextual, "defaultDeserializer.crea…ontextual(ctxt, property)");
            return KotlinObjectSingletonDeserializerKt.asSingletonDeserializer(jsonDeserializerCreateContextual, this.singletonInstance);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @NotNull
    public Object deserialize(@NotNull JsonParser p, @NotNull DeserializationContext ctxt) throws IOException {
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        this.defaultDeserializer.deserialize(p, ctxt);
        return this.singletonInstance;
    }
}
