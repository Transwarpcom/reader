package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.MapperFeature;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KCallables;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ValueCreator.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b0\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J!\u0010\u0013\u001a\u00028��2\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0015¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u001b\u001c¨\u0006\u001d"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueCreator;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "()V", "accessible", "", "getAccessible", "()Z", "callable", "Lkotlin/reflect/KFunction;", "getCallable", "()Lkotlin/reflect/KFunction;", "valueParameters", "", "Lkotlin/reflect/KParameter;", "getValueParameters", "()Ljava/util/List;", "valueParameters$delegate", "Lkotlin/Lazy;", "callBy", "args", "", "(Ljava/util/Map;)Ljava/lang/Object;", "checkAccessibility", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "Lcom/fasterxml/jackson/module/kotlin/ConstructorValueCreator;", "Lcom/fasterxml/jackson/module/kotlin/MethodValueCreator;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ValueCreator.class */
public abstract class ValueCreator<T> {

    @NotNull
    private final Lazy valueParameters$delegate;

    @NotNull
    protected abstract KFunction<T> getCallable();

    protected abstract boolean getAccessible();

    public /* synthetic */ ValueCreator(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private ValueCreator() {
        this.valueParameters$delegate = LazyKt.lazy(new Function0<List<? extends KParameter>>(this) { // from class: com.fasterxml.jackson.module.kotlin.ValueCreator$valueParameters$2
            final /* synthetic */ ValueCreator<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends KParameter> invoke() {
                return KCallables.getValueParameters(this.this$0.getCallable());
            }
        });
    }

    @NotNull
    public final List<KParameter> getValueParameters() {
        return (List) this.valueParameters$delegate.getValue();
    }

    public final void checkAccessibility(@NotNull DeserializationContext ctxt) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        if (getAccessible() || !ctxt.getConfig().isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            if (getAccessible() && ctxt.getConfig().isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)) {
            } else {
                throw new IllegalAccessException(Intrinsics.stringPlus("Cannot access to function or companion object instance, target: ", getCallable()));
            }
        }
    }

    public final T callBy(@NotNull Map<KParameter, ? extends Object> args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return getCallable().callBy(args);
    }
}
