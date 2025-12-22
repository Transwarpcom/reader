package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.internal.PathRef;
import java.util.Collections;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/WildcardPathToken.class */
public class WildcardPathToken extends PathToken {
    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        if (ctx.jsonProvider().isMap(model)) {
            for (String property : ctx.jsonProvider().getPropertyKeys(model)) {
                handleObjectProperty(currentPath, model, ctx, Collections.singletonList(property));
            }
            return;
        }
        if (ctx.jsonProvider().isArray(model)) {
            for (int idx = 0; idx < ctx.jsonProvider().length(model); idx++) {
                try {
                    handleArrayIndex(idx, currentPath, model, ctx);
                } catch (PathNotFoundException p) {
                    if (ctx.options().contains(Option.REQUIRE_PROPERTIES)) {
                        throw p;
                    }
                }
            }
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return false;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return "[*]";
    }
}
