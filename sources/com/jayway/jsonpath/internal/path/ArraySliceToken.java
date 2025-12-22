package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.internal.PathRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArraySliceToken.class */
public class ArraySliceToken extends ArrayPathToken {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) ArraySliceToken.class);
    private final ArraySliceOperation operation;

    ArraySliceToken(ArraySliceOperation operation) {
        this.operation = operation;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        if (!checkArrayModel(currentPath, model, ctx)) {
        }
        switch (this.operation.operation()) {
            case SLICE_FROM:
                sliceFrom(currentPath, parent, model, ctx);
                break;
            case SLICE_BETWEEN:
                sliceBetween(currentPath, parent, model, ctx);
                break;
            case SLICE_TO:
                sliceTo(currentPath, parent, model, ctx);
                break;
        }
    }

    private void sliceFrom(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        int length = ctx.jsonProvider().length(model);
        int from = this.operation.from().intValue();
        if (from < 0) {
            from = length + from;
        }
        int from2 = Math.max(0, from);
        logger.debug("Slice from index on array with length: {}. From index: {} to: {}. Input: {}", Integer.valueOf(length), Integer.valueOf(from2), Integer.valueOf(length - 1), toString());
        if (length == 0 || from2 >= length) {
            return;
        }
        for (int i = from2; i < length; i++) {
            handleArrayIndex(i, currentPath, model, ctx);
        }
    }

    private void sliceBetween(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        int length = ctx.jsonProvider().length(model);
        int from = this.operation.from().intValue();
        int to = Math.min(length, this.operation.to().intValue());
        if (from >= to || length == 0) {
            return;
        }
        logger.debug("Slice between indexes on array with length: {}. From index: {} to: {}. Input: {}", Integer.valueOf(length), Integer.valueOf(from), Integer.valueOf(to), toString());
        for (int i = from; i < to; i++) {
            handleArrayIndex(i, currentPath, model, ctx);
        }
    }

    private void sliceTo(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        int length = ctx.jsonProvider().length(model);
        if (length == 0) {
            return;
        }
        int to = this.operation.to().intValue();
        if (to < 0) {
            to = length + to;
        }
        int to2 = Math.min(length, to);
        logger.debug("Slice to index on array with length: {}. From index: 0 to: {}. Input: {}", Integer.valueOf(length), Integer.valueOf(to2), toString());
        for (int i = 0; i < to2; i++) {
            handleArrayIndex(i, currentPath, model, ctx);
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return this.operation.toString();
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return false;
    }
}
