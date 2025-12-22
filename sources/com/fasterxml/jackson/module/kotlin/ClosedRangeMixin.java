package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: KotlinMixins.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u000e\n\u0002\u0010\u000b\n��\b \u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00028��\u0012\u0006\u0010\u0004\u001a\u00028��¢\u0006\u0002\u0010\u0005J\r\u0010\n\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\r\u0010\u000b\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\r\u0010\f\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\r\u0010\r\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\r\u0010\u000e\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\r\u0010\u000f\u001a\u00028��H'¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H'R\u0015\u0010\u0004\u001a\u00028��8\u0007¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0003\u001a\u00028��¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ClosedRangeMixin;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "start", "endInclusive", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getEndInclusive", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getStart", "getEnd", "getEndExclusive", "getFirst", "getIncrement", "getLast", "getStep", "isEmpty", "", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ClosedRangeMixin.class */
public abstract class ClosedRangeMixin<T> {
    private final T start;
    private final T endInclusive;

    @JsonIgnore
    public abstract T getEnd();

    @JsonIgnore
    public abstract T getFirst();

    @JsonIgnore
    public abstract T getLast();

    @JsonIgnore
    public abstract T getIncrement();

    @JsonIgnore
    public abstract boolean isEmpty();

    @JsonIgnore
    public abstract T getStep();

    @JsonIgnore
    public abstract T getEndExclusive();

    @JsonCreator
    public ClosedRangeMixin(T t, T t2) {
        this.start = t;
        this.endInclusive = t2;
    }

    public final T getStart() {
        return this.start;
    }

    @JsonProperty("end")
    public final T getEndInclusive() {
        return this.endInclusive;
    }
}
