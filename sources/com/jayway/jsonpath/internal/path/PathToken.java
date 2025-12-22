package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.function.PathFunction;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/PathToken.class */
public abstract class PathToken {
    private PathToken prev;
    private PathToken next;
    private Boolean definite = null;
    private Boolean upstreamDefinite = null;
    static final /* synthetic */ boolean $assertionsDisabled;

    public abstract void evaluate(String str, PathRef pathRef, Object obj, EvaluationContextImpl evaluationContextImpl);

    public abstract boolean isTokenDefinite();

    protected abstract String getPathFragment();

    static {
        $assertionsDisabled = !PathToken.class.desiredAssertionStatus();
    }

    PathToken appendTailToken(PathToken next) {
        this.next = next;
        this.next.prev = this;
        return next;
    }

    void handleObjectProperty(String currentPath, Object model, EvaluationContextImpl ctx, List<String> properties) {
        Object propertyVal;
        if (properties.size() == 1) {
            String property = properties.get(0);
            String evalPath = Utils.concat(currentPath, "['", property, "']");
            Object propertyVal2 = readObjectProperty(property, model, ctx);
            if (propertyVal2 == JsonProvider.UNDEFINED) {
                if (!$assertionsDisabled && !(this instanceof PropertyPathToken)) {
                    throw new AssertionError("only PropertyPathToken is supported");
                }
                if (isLeaf()) {
                    if (ctx.options().contains(Option.DEFAULT_PATH_LEAF_TO_NULL)) {
                        propertyVal2 = null;
                    } else if (ctx.options().contains(Option.SUPPRESS_EXCEPTIONS) || !ctx.options().contains(Option.REQUIRE_PROPERTIES)) {
                        return;
                    } else {
                        throw new PathNotFoundException("No results for path: " + evalPath);
                    }
                } else if (((!isUpstreamDefinite() || !isTokenDefinite()) && !ctx.options().contains(Option.REQUIRE_PROPERTIES)) || ctx.options().contains(Option.SUPPRESS_EXCEPTIONS)) {
                    return;
                } else {
                    throw new PathNotFoundException("Missing property in path " + evalPath);
                }
            }
            PathRef pathRef = ctx.forUpdate() ? PathRef.create(model, property) : PathRef.NO_OP;
            if (isLeaf()) {
                ctx.addResult(evalPath, pathRef, propertyVal2);
                return;
            } else {
                next().evaluate(evalPath, pathRef, propertyVal2, ctx);
                return;
            }
        }
        String evalPath2 = currentPath + "[" + Utils.join(", ", OperatorName.SHOW_TEXT_LINE, properties) + "]";
        if (!$assertionsDisabled && !isLeaf()) {
            throw new AssertionError("non-leaf multi props handled elsewhere");
        }
        Object merged = ctx.jsonProvider().createMap();
        for (String property2 : properties) {
            if (hasProperty(property2, model, ctx)) {
                propertyVal = readObjectProperty(property2, model, ctx);
                if (propertyVal == JsonProvider.UNDEFINED) {
                    if (ctx.options().contains(Option.DEFAULT_PATH_LEAF_TO_NULL)) {
                        propertyVal = null;
                    }
                }
                ctx.jsonProvider().setProperty(merged, property2, propertyVal);
            } else if (ctx.options().contains(Option.DEFAULT_PATH_LEAF_TO_NULL)) {
                propertyVal = null;
                ctx.jsonProvider().setProperty(merged, property2, propertyVal);
            } else if (ctx.options().contains(Option.REQUIRE_PROPERTIES)) {
                throw new PathNotFoundException("Missing property in path " + evalPath2);
            }
        }
        ctx.addResult(evalPath2, ctx.forUpdate() ? PathRef.create(model, properties) : PathRef.NO_OP, merged);
    }

    private static boolean hasProperty(String property, Object model, EvaluationContextImpl ctx) {
        return ctx.jsonProvider().getPropertyKeys(model).contains(property);
    }

    private static Object readObjectProperty(String property, Object model, EvaluationContextImpl ctx) {
        return ctx.jsonProvider().getMapValue(model, property);
    }

    protected void handleArrayIndex(int index, String currentPath, Object model, EvaluationContextImpl ctx) {
        String evalPath = Utils.concat(currentPath, "[", String.valueOf(index), "]");
        PathRef pathRef = ctx.forUpdate() ? PathRef.create(model, index) : PathRef.NO_OP;
        int effectiveIndex = index < 0 ? ctx.jsonProvider().length(model) + index : index;
        try {
            Object evalHit = ctx.jsonProvider().getArrayIndex(model, effectiveIndex);
            if (isLeaf()) {
                ctx.addResult(evalPath, pathRef, evalHit);
            } else {
                next().evaluate(evalPath, pathRef, evalHit, ctx);
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    PathToken prev() {
        return this.prev;
    }

    PathToken next() {
        if (isLeaf()) {
            throw new IllegalStateException("Current path token is a leaf");
        }
        return this.next;
    }

    boolean isLeaf() {
        return this.next == null;
    }

    boolean isRoot() {
        return this.prev == null;
    }

    boolean isUpstreamDefinite() {
        if (this.upstreamDefinite == null) {
            this.upstreamDefinite = Boolean.valueOf(isRoot() || (this.prev.isTokenDefinite() && this.prev.isUpstreamDefinite()));
        }
        return this.upstreamDefinite.booleanValue();
    }

    public int getTokenCount() {
        int cnt = 1;
        PathToken token = this;
        while (!token.isLeaf()) {
            token = token.next();
            cnt++;
        }
        return cnt;
    }

    public boolean isPathDefinite() {
        if (this.definite != null) {
            return this.definite.booleanValue();
        }
        boolean isDefinite = isTokenDefinite();
        if (isDefinite && !isLeaf()) {
            isDefinite = this.next.isPathDefinite();
        }
        this.definite = Boolean.valueOf(isDefinite);
        return isDefinite;
    }

    public String toString() {
        if (isLeaf()) {
            return getPathFragment();
        }
        return getPathFragment() + next().toString();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void invoke(PathFunction pathFunction, String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        ctx.addResult(currentPath, parent, pathFunction.invoke(currentPath, parent, model, ctx, null));
    }

    public void setNext(PathToken next) {
        this.next = next;
    }

    public PathToken getNext() {
        return this.next;
    }
}
