package com.jayway.jsonpath.internal.function.numeric;

import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/numeric/AbstractAggregation.class */
public abstract class AbstractAggregation implements PathFunction {
    protected abstract void next(Number number);

    protected abstract Number getValue();

    @Override // com.jayway.jsonpath.internal.function.PathFunction
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        int count = 0;
        if (ctx.configuration().jsonProvider().isArray(model)) {
            Iterable<?> objects = ctx.configuration().jsonProvider().toIterable(model);
            for (Object obj : objects) {
                if (obj instanceof Number) {
                    Number value = (Number) obj;
                    count++;
                    next(value);
                }
            }
        }
        if (parameters != null) {
            for (Number value2 : Parameter.toList(Number.class, ctx, parameters)) {
                count++;
                next(value2);
            }
        }
        if (count != 0) {
            return getValue();
        }
        throw new JsonPathException("Aggregation function attempted to calculate value using empty array");
    }
}
