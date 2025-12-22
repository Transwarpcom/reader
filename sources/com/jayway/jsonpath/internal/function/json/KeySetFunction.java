package com.jayway.jsonpath.internal.function.json;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/json/KeySetFunction.class */
public class KeySetFunction implements PathFunction {
    @Override // com.jayway.jsonpath.internal.function.PathFunction
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        if (ctx.configuration().jsonProvider().isMap(model)) {
            return ctx.configuration().jsonProvider().getPropertyKeys(model);
        }
        return null;
    }
}
