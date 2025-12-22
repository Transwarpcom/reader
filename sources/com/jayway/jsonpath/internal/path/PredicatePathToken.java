package com.jayway.jsonpath.internal.path;

import ch.qos.logback.classic.spi.CallerData;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.PathRef;
import java.util.Collection;
import java.util.Collections;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/PredicatePathToken.class */
public class PredicatePathToken extends PathToken {
    private final Collection<Predicate> predicates;

    PredicatePathToken(Predicate filter) {
        this.predicates = Collections.singletonList(filter);
    }

    PredicatePathToken(Collection<Predicate> predicates) {
        this.predicates = predicates;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef ref, Object model, EvaluationContextImpl ctx) {
        if (ctx.jsonProvider().isMap(model)) {
            if (accept(model, ctx.rootDocument(), ctx.configuration(), ctx)) {
                PathRef op = ctx.forUpdate() ? ref : PathRef.NO_OP;
                if (isLeaf()) {
                    ctx.addResult(currentPath, op, model);
                    return;
                } else {
                    next().evaluate(currentPath, op, model, ctx);
                    return;
                }
            }
            return;
        }
        if (ctx.jsonProvider().isArray(model)) {
            int idx = 0;
            Iterable<?> objects = ctx.jsonProvider().toIterable(model);
            for (Object idxModel : objects) {
                if (accept(idxModel, ctx.rootDocument(), ctx.configuration(), ctx)) {
                    handleArrayIndex(idx, currentPath, model, ctx);
                }
                idx++;
            }
            return;
        }
        if (isUpstreamDefinite()) {
            throw new InvalidPathException(String.format("Filter: %s can not be applied to primitives. Current context is: %s", toString(), model));
        }
    }

    public boolean accept(Object obj, Object root, Configuration configuration, EvaluationContextImpl evaluationContext) {
        Predicate.PredicateContext ctx = new PredicateContextImpl(obj, root, configuration, evaluationContext.documentEvalCache());
        for (Predicate predicate : this.predicates) {
            try {
                if (!predicate.apply(ctx)) {
                    return false;
                }
            } catch (InvalidPathException e) {
                return false;
            }
        }
        return true;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.predicates.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(CallerData.NA);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return false;
    }
}
