package org.antlr.v4.runtime.atn;

import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PredictionContextCache.class */
public class PredictionContextCache {
    protected final Map<PredictionContext, PredictionContext> cache = new HashMap();

    public PredictionContext add(PredictionContext ctx) {
        if (ctx == PredictionContext.EMPTY) {
            return PredictionContext.EMPTY;
        }
        PredictionContext existing = this.cache.get(ctx);
        if (existing != null) {
            return existing;
        }
        this.cache.put(ctx, ctx);
        return ctx;
    }

    public PredictionContext get(PredictionContext ctx) {
        return this.cache.get(ctx);
    }

    public int size() {
        return this.cache.size();
    }
}
