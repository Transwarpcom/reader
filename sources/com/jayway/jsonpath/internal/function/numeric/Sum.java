package com.jayway.jsonpath.internal.function.numeric;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/Sum.class */
public class Sum extends AbstractAggregation {
    private Double summation = Double.valueOf(0.0d);

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected void next(Number value) {
        this.summation = Double.valueOf(this.summation.doubleValue() + value.doubleValue());
    }

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected Number getValue() {
        return this.summation;
    }
}
