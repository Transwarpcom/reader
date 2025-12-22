package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken.class */
public class ScanPathToken extends PathToken {
    private static final Predicate FALSE_PREDICATE = new Predicate() { // from class: com.jayway.jsonpath.internal.path.ScanPathToken.1
        @Override // com.jayway.jsonpath.internal.path.ScanPathToken.Predicate
        public boolean matches(Object model) {
            return false;
        }
    };

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken$Predicate.class */
    private interface Predicate {
        boolean matches(Object obj);
    }

    ScanPathToken() {
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        PathToken pt = next();
        walk(pt, currentPath, parent, model, ctx, createScanPredicate(pt, ctx));
    }

    public static void walk(PathToken pt, String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx, Predicate predicate) {
        if (ctx.jsonProvider().isMap(model)) {
            walkObject(pt, currentPath, parent, model, ctx, predicate);
        } else if (ctx.jsonProvider().isArray(model)) {
            walkArray(pt, currentPath, parent, model, ctx, predicate);
        }
    }

    public static void walkArray(PathToken pt, String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx, Predicate predicate) {
        if (predicate.matches(model)) {
            if (pt.isLeaf()) {
                pt.evaluate(currentPath, parent, model, ctx);
            } else {
                PathToken next = pt.next();
                Iterable<?> models = ctx.jsonProvider().toIterable(model);
                int idx = 0;
                for (Object evalModel : models) {
                    String evalPath = currentPath + "[" + idx + "]";
                    next.evaluate(evalPath, parent, evalModel, ctx);
                    idx++;
                }
            }
        }
        Iterable<?> models2 = ctx.jsonProvider().toIterable(model);
        int idx2 = 0;
        for (Object evalModel2 : models2) {
            String evalPath2 = currentPath + "[" + idx2 + "]";
            walk(pt, evalPath2, PathRef.create(model, idx2), evalModel2, ctx, predicate);
            idx2++;
        }
    }

    public static void walkObject(PathToken pt, String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx, Predicate predicate) {
        if (predicate.matches(model)) {
            pt.evaluate(currentPath, parent, model, ctx);
        }
        Collection<String> properties = ctx.jsonProvider().getPropertyKeys(model);
        for (String property : properties) {
            String evalPath = currentPath + "['" + property + "']";
            Object propertyModel = ctx.jsonProvider().getMapValue(model, property);
            if (propertyModel != JsonProvider.UNDEFINED) {
                walk(pt, evalPath, PathRef.create(model, property), propertyModel, ctx, predicate);
            }
        }
    }

    private static Predicate createScanPredicate(PathToken target, EvaluationContextImpl ctx) {
        if (target instanceof PropertyPathToken) {
            return new PropertyPathTokenPredicate(target, ctx);
        }
        if (target instanceof ArrayPathToken) {
            return new ArrayPathTokenPredicate(ctx);
        }
        if (target instanceof WildcardPathToken) {
            return new WildcardPathTokenPredicate();
        }
        if (target instanceof PredicatePathToken) {
            return new FilterPathTokenPredicate(target, ctx);
        }
        return FALSE_PREDICATE;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return false;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return "..";
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken$FilterPathTokenPredicate.class */
    private static final class FilterPathTokenPredicate implements Predicate {
        private final EvaluationContextImpl ctx;
        private PredicatePathToken predicatePathToken;

        private FilterPathTokenPredicate(PathToken target, EvaluationContextImpl ctx) {
            this.ctx = ctx;
            this.predicatePathToken = (PredicatePathToken) target;
        }

        @Override // com.jayway.jsonpath.internal.path.ScanPathToken.Predicate
        public boolean matches(Object model) {
            return this.predicatePathToken.accept(model, this.ctx.rootDocument(), this.ctx.configuration(), this.ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken$WildcardPathTokenPredicate.class */
    private static final class WildcardPathTokenPredicate implements Predicate {
        private WildcardPathTokenPredicate() {
        }

        @Override // com.jayway.jsonpath.internal.path.ScanPathToken.Predicate
        public boolean matches(Object model) {
            return true;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken$ArrayPathTokenPredicate.class */
    private static final class ArrayPathTokenPredicate implements Predicate {
        private final EvaluationContextImpl ctx;

        private ArrayPathTokenPredicate(EvaluationContextImpl ctx) {
            this.ctx = ctx;
        }

        @Override // com.jayway.jsonpath.internal.path.ScanPathToken.Predicate
        public boolean matches(Object model) {
            return this.ctx.jsonProvider().isArray(model);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ScanPathToken$PropertyPathTokenPredicate.class */
    private static final class PropertyPathTokenPredicate implements Predicate {
        private final EvaluationContextImpl ctx;
        private PropertyPathToken propertyPathToken;

        private PropertyPathTokenPredicate(PathToken target, EvaluationContextImpl ctx) {
            this.ctx = ctx;
            this.propertyPathToken = (PropertyPathToken) target;
        }

        @Override // com.jayway.jsonpath.internal.path.ScanPathToken.Predicate
        public boolean matches(Object model) {
            if (!this.ctx.jsonProvider().isMap(model)) {
                return false;
            }
            if (!this.propertyPathToken.isTokenDefinite()) {
                return true;
            }
            if (this.propertyPathToken.isLeaf() && this.ctx.options().contains(Option.DEFAULT_PATH_LEAF_TO_NULL)) {
                return true;
            }
            Collection<String> keys = this.ctx.jsonProvider().getPropertyKeys(model);
            return keys.containsAll(this.propertyPathToken.getProperties());
        }
    }
}
