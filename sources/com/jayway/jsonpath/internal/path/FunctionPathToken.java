package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;
import com.jayway.jsonpath.internal.function.PathFunctionFactory;
import com.jayway.jsonpath.internal.function.latebinding.JsonLateBindingValue;
import com.jayway.jsonpath.internal.function.latebinding.PathLateBindingValue;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/FunctionPathToken.class */
public class FunctionPathToken extends PathToken {
    private final String functionName;
    private final String pathFragment;
    private List<Parameter> functionParams;

    public FunctionPathToken(String pathFragment, List<Parameter> parameters) {
        this.pathFragment = pathFragment + ((parameters == null || parameters.size() <= 0) ? "()" : "(...)");
        if (null != pathFragment) {
            this.functionName = pathFragment;
            this.functionParams = parameters;
        } else {
            this.functionName = null;
            this.functionParams = null;
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public void evaluate(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) throws InvalidPathException {
        PathFunction pathFunction = PathFunctionFactory.newFunction(this.functionName);
        evaluateParameters(currentPath, parent, model, ctx);
        Object result = pathFunction.invoke(currentPath, parent, model, ctx, this.functionParams);
        ctx.addResult(currentPath + "." + this.functionName, parent, result);
        if (!isLeaf()) {
            next().evaluate(currentPath, parent, result, ctx);
        }
    }

    private void evaluateParameters(String currentPath, PathRef parent, Object model, EvaluationContextImpl ctx) {
        if (null != this.functionParams) {
            for (Parameter param : this.functionParams) {
                switch (param.getType()) {
                    case PATH:
                        PathLateBindingValue pathLateBindingValue = new PathLateBindingValue(param.getPath(), ctx.rootDocument(), ctx.configuration());
                        if (!param.hasEvaluated() || !pathLateBindingValue.equals(param.getILateBingValue())) {
                            param.setLateBinding(pathLateBindingValue);
                            param.setEvaluated(true);
                            break;
                        } else {
                            break;
                        }
                        break;
                    case JSON:
                        if (param.hasEvaluated()) {
                            break;
                        } else {
                            param.setLateBinding(new JsonLateBindingValue(ctx.configuration().jsonProvider(), param));
                            param.setEvaluated(true);
                            break;
                        }
                }
            }
        }
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public boolean isTokenDefinite() {
        return true;
    }

    @Override // com.jayway.jsonpath.internal.path.PathToken
    public String getPathFragment() {
        return "." + this.pathFragment;
    }

    public void setParameters(List<Parameter> parameters) {
        this.functionParams = parameters;
    }

    public List<Parameter> getParameters() {
        return this.functionParams;
    }

    public String getFunctionName() {
        return this.functionName;
    }
}
