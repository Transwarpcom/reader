package com.jayway.jsonpath.internal.function;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/PathFunction.class */
public interface PathFunction {
    Object invoke(String str, PathRef pathRef, Object obj, EvaluationContext evaluationContext, List<Parameter> list);
}
