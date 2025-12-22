package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.internal.PathRef;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArrayIndexToken.class */
public class ArrayIndexToken extends ArrayPathToken {
    private final ArrayIndexOperation arrayIndexOperation;

    ArrayIndexToken(ArrayIndexOperation arrayIndexOperation) {
        this.arrayIndexOperation = arrayIndexOperation;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        if (!checkArrayModel(currentPath, model, ctx)) {
            return;
        }
        if (this.arrayIndexOperation.isSingleIndexOperation()) {
            handleArrayIndex(this.arrayIndexOperation.indexes().get(0).intValue(), currentPath, model, ctx);
            return;
        }
        for (Integer index : this.arrayIndexOperation.indexes()) {
            handleArrayIndex(index.intValue(), currentPath, model, ctx);
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return this.arrayIndexOperation.toString();
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return this.arrayIndexOperation.isSingleIndexOperation();
    }
}
