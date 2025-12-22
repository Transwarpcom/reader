package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.internal.PathRef;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/RootPathToken.class */
public class RootPathToken extends PathToken {
    private PathToken tail = this;
    private int tokenCount = 1;
    private final String rootToken;

    RootPathToken(char rootToken) {
        this.rootToken = Character.toString(rootToken);
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public int getTokenCount() {
        return this.tokenCount;
    }

    public RootPathToken append(PathToken next) {
        this.tail = this.tail.appendTailToken(next);
        this.tokenCount++;
        return this;
    }

    public PathTokenAppender getPathTokenAppender() {
        return new PathTokenAppender() { // from class: com.jayway.jsonpath.internal.path.RootPathToken.1
            @Override // com.jayway.jsonpath.internal.path.PathTokenAppender
            public PathTokenAppender appendPathToken(PathToken next) {
                RootPathToken.this.append(next);
                return this;
            }
        };
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef pathRef, Object model, EvaluationContextImpl ctx) {
        if (isLeaf()) {
            PathRef op = ctx.forUpdate() ? pathRef : PathRef.NO_OP;
            ctx.addResult(this.rootToken, op, model);
        } else {
            next().evaluate(this.rootToken, pathRef, model, ctx);
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return this.rootToken;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return true;
    }

    public boolean isFunctionPath() {
        return this.tail instanceof FunctionPathToken;
    }

    public void setTail(PathToken token) {
        this.tail = token;
    }
}
