package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Predicate;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/Evaluator.class */
public interface Evaluator {
    boolean evaluate(ValueNode valueNode, ValueNode valueNode2, Predicate.PredicateContext predicateContext);
}
