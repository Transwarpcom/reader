package com.jayway.jsonpath.internal.function.numeric;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/StandardDeviation.class */
public class StandardDeviation extends AbstractAggregation {
    private Double sumSq = Double.valueOf(0.0d);
    private Double sum = Double.valueOf(0.0d);
    private Double count = Double.valueOf(0.0d);

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected void next(Number value) {
        this.sum = Double.valueOf(this.sum.doubleValue() + value.doubleValue());
        this.sumSq = Double.valueOf(this.sumSq.doubleValue() + (value.doubleValue() * value.doubleValue()));
        Double d = this.count;
        this.count = Double.valueOf(this.count.doubleValue() + 1.0d);
    }

    @Override // com.jayway.jsonpath.internal.function.numeric.AbstractAggregation
    protected Number getValue() {
        return Double.valueOf(Math.sqrt((this.sumSq.doubleValue() / this.count.doubleValue()) - (((this.sum.doubleValue() * this.sum.doubleValue()) / this.count.doubleValue()) / this.count.doubleValue())));
    }
}
