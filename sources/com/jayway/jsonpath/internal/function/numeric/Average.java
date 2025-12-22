package com.jayway.jsonpath.internal.function.numeric;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/Average.class */
public class Average extends AbstractAggregation {
    private Double summation = Double.valueOf(0.0d);
    private Double count = Double.valueOf(0.0d);

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected void next(Number value) {
        Double d = this.count;
        this.count = Double.valueOf(this.count.doubleValue() + 1.0d);
        this.summation = Double.valueOf(this.summation.doubleValue() + value.doubleValue());
    }

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected Number getValue() {
        if (this.count.doubleValue() != 0.0d) {
            return Double.valueOf(this.summation.doubleValue() / this.count.doubleValue());
        }
        return Double.valueOf(0.0d);
    }
}
