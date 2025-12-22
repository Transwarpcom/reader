package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/PropertyPathToken.class */
class PropertyPathToken extends PathToken {
    private final List<String> properties;
    private final String stringDelimiter;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !PropertyPathToken.class.desiredAssertionStatus();
    }

    public PropertyPathToken(List<String> properties, char stringDelimiter) {
        if (properties.isEmpty()) {
            throw new InvalidPathException("Empty properties");
        }
        this.properties = properties;
        this.stringDelimiter = Character.toString(stringDelimiter);
    }

    public List<String> getProperties() {
        return this.properties;
    }

    public boolean singlePropertyCase() {
        return this.properties.size() == 1;
    }

    public boolean multiPropertyMergeCase() {
        return isLeaf() && this.properties.size() > 1;
    }

    public boolean multiPropertyIterationCase() {
        return !isLeaf() && this.properties.size() > 1;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        if (!$assertionsDisabled && !Utils.onlyOneIsTrueNonThrow(singlePropertyCase(), multiPropertyMergeCase(), multiPropertyIterationCase())) {
            throw new AssertionError();
        }
        if (!ctx.jsonProvider().isMap(model)) {
            if (!isUpstreamDefinite()) {
                return;
            }
            String m = model == null ? "null" : model.getClass().getName();
            throw new PathNotFoundException(String.format("Expected to find an object with property %s in path %s but found '%s'. This is not a json object according to the JsonProvider: '%s'.", getPathFragment(), currentPath, m, ctx.configuration().jsonProvider().getClass().getName()));
        }
        if (singlePropertyCase() || multiPropertyMergeCase()) {
            handleObjectProperty(currentPath, model, ctx, this.properties);
            return;
        }
        if (!$assertionsDisabled && !multiPropertyIterationCase()) {
            throw new AssertionError();
        }
        List<String> currentlyHandledProperty = new ArrayList<>(1);
        currentlyHandledProperty.add(null);
        for (String property : this.properties) {
            currentlyHandledProperty.set(0, property);
            handleObjectProperty(currentPath, model, ctx, currentlyHandledProperty);
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return singlePropertyCase() || multiPropertyMergeCase();
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return "[" + Utils.join(",", this.stringDelimiter, this.properties) + "]";
    }
}
