package com.fasterxml.jackson.module.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.KCallablesJvm;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ConstructorValueCreator.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b��\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0094\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ConstructorValueCreator;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lcom/fasterxml/jackson/module/kotlin/ValueCreator;", "callable", "Lkotlin/reflect/KFunction;", "(Lkotlin/reflect/KFunction;)V", "accessible", "", "getAccessible", "()Z", "getCallable", "()Lkotlin/reflect/KFunction;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ConstructorValueCreator.class */
public final class ConstructorValueCreator<T> extends ValueCreator<T> {

    @NotNull
    private final KFunction<T> callable;
    private final boolean accessible;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ConstructorValueCreator(@NotNull KFunction<? extends T> callable) throws SecurityException {
        super(null);
        Intrinsics.checkNotNullParameter(callable, "callable");
        this.callable = callable;
        this.accessible = KCallablesJvm.isAccessible(getCallable());
        if (!getAccessible()) {
            KCallablesJvm.setAccessible(getCallable(), true);
        }
    }

    @Override // com.fasterxml.jackson.module.kotlin.ValueCreator
    @NotNull
    protected KFunction<T> getCallable() {
        return this.callable;
    }

    @Override // com.fasterxml.jackson.module.kotlin.ValueCreator
    protected boolean getAccessible() {
        return this.accessible;
    }
}
