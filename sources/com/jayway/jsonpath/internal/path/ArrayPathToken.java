package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.PathNotFoundException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArrayPathToken.class */
public abstract class ArrayPathToken extends PathToken {
    protected boolean checkArrayModel(String currentPath, Object model, EvaluationContextImpl ctx) {
        if (model == null) {
            if (!isUpstreamDefinite()) {
                return false;
            }
            throw new PathNotFoundException("The path " + currentPath + " is null");
        }
        if (!ctx.jsonProvider().isArray(model)) {
            if (!isUpstreamDefinite()) {
                return false;
            }
            throw new PathNotFoundException(String.format("Filter: %s can only be applied to arrays. Current context is: %s", toString(), model));
        }
        return true;
    }
}
