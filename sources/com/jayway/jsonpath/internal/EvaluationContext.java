package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.Configuration;
import java.util.Collection;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/EvaluationContext.class */
public interface EvaluationContext {
    Configuration configuration();

    Object rootDocument();

    <T> T getValue();

    <T> T getValue(boolean z);

    <T> T getPath();

    List<String> getPathList();

    Collection<PathRef> updateOperations();
}
