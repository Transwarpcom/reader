package com.jayway.jsonpath.internal.function.numeric;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/Min.class */
public class Min extends AbstractAggregation {
    private Double min = Double.valueOf(Double.MAX_VALUE);

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected void next(Number value) {
        if (this.min.doubleValue() > value.doubleValue()) {
            this.min = Double.valueOf(value.doubleValue());
        }
    }

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected Number getValue() {
        return this.min;
    }
}
