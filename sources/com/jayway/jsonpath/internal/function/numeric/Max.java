package com.jayway.jsonpath.internal.function.numeric;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/Max.class */
public class Max extends AbstractAggregation {
    private Double max = Double.valueOf(Double.MIN_VALUE);

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected void next(Number value) {
        if (this.max.doubleValue() < value.doubleValue()) {
            this.max = Double.valueOf(value.doubleValue());
        }
    }

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected Number getValue() {
        return this.max;
    }
}
