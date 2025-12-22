package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.internal.EvaluationAbortException;
import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.ParamType;
import com.jayway.jsonpath.internal.function.Parameter;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/CompiledPath.class */
public class CompiledPath implements Path {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) CompiledPath.class);
    private final RootPathToken root;
    private final boolean isRootPath;

    public CompiledPath(RootPathToken root, boolean isRootPath) {
        this.root = invertScannerFunctionRelationship(root);
        this.isRootPath = isRootPath;
    }

    @Override // com.jayway.jsonpath.internal.Path
    public boolean isRootPath() {
        return this.isRootPath;
    }

    private RootPathToken invertScannerFunctionRelationship(RootPathToken path) {
        PathToken prior;
        if (path.isFunctionPath() && (path.next() instanceof ScanPathToken)) {
            PathToken token = path;
            PathToken pathToken = null;
            while (true) {
                prior = pathToken;
                PathToken next = token.next();
                token = next;
                if (null == next || (token instanceof FunctionPathToken)) {
                    break;
                }
                pathToken = token;
            }
            if (token instanceof FunctionPathToken) {
                prior.setNext(null);
                path.setTail(prior);
                Parameter parameter = new Parameter();
                parameter.setPath(new CompiledPath(path, true));
                parameter.setType(ParamType.PATH);
                ((FunctionPathToken) token).setParameters(Arrays.asList(parameter));
                RootPathToken functionRoot = new RootPathToken('$');
                functionRoot.setTail(token);
                functionRoot.setNext(token);
                return functionRoot;
            }
        }
        return path;
    }

    @Override // com.jayway.jsonpath.internal.Path
    public EvaluationContext evaluate(Object document, Object rootDocument, Configuration configuration, boolean forUpdate) {
        if (logger.isDebugEnabled()) {
            logger.debug("Evaluating path: {}", toString());
        }
        EvaluationContextImpl ctx = new EvaluationContextImpl(this, rootDocument, configuration, forUpdate);
        try {
            PathRef op = ctx.forUpdate() ? PathRef.createRoot(rootDocument) : PathRef.NO_OP;
            this.root.evaluate("", op, document, ctx);
        } catch (EvaluationAbortException e) {
        }
        return ctx;
    }

    @Override // com.jayway.jsonpath.internal.Path
    public EvaluationContext evaluate(Object document, Object rootDocument, Configuration configuration) {
        return evaluate(document, rootDocument, configuration, false);
    }

    @Override // com.jayway.jsonpath.internal.Path
    public boolean isDefinite() {
        return this.root.isPathDefinite();
    }

    @Override // com.jayway.jsonpath.internal.Path
    public boolean isFunctionPath() {
        return this.root.isFunctionPath();
    }

    public String toString() {
        return this.root.toString();
    }

    public RootPathToken getRoot() {
        return this.root;
    }
}
