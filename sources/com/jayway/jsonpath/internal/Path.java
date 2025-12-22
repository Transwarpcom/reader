package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.Configuration;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/Path.class */
public interface Path {
    EvaluationContext evaluate(Object obj, Object obj2, Configuration configuration);

    EvaluationContext evaluate(Object obj, Object obj2, Configuration configuration, boolean z);

    boolean isDefinite();

    boolean isFunctionPath();

    boolean isRootPath();
}
