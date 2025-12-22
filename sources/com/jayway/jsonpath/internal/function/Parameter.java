package com.jayway.jsonpath.internal.function;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.function.latebinding.ILateBindingValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/Parameter.class */
public class Parameter {
    private ParamType type;
    private Path path;
    private ILateBindingValue lateBinding;
    private Boolean evaluated;
    private String json;

    public Parameter() {
        this.evaluated = false;
    }

    public Parameter(String json) {
        this.evaluated = false;
        this.json = json;
        this.type = ParamType.JSON;
    }

    public Parameter(Path path) {
        this.evaluated = false;
        this.path = path;
        this.type = ParamType.PATH;
    }

    public Object getValue() {
        return this.lateBinding.get();
    }

    public void setLateBinding(ILateBindingValue lateBinding) {
        this.lateBinding = lateBinding;
    }

    public Path getPath() {
        return this.path;
    }

    public void setEvaluated(Boolean evaluated) {
        this.evaluated = evaluated;
    }

    public boolean hasEvaluated() {
        return this.evaluated.booleanValue();
    }

    public ParamType getType() {
        return this.type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public ILateBindingValue getILateBingValue() {
        return this.lateBinding;
    }

    public static <T> List<T> toList(Class<T> type, EvaluationContext ctx, List<Parameter> parameters) {
        List<T> values = new ArrayList<>();
        if (null != parameters) {
            for (Parameter param : parameters) {
                consume(type, ctx, values, param.getValue());
            }
        }
        return values;
    }

    public static void consume(Class expectedType, EvaluationContext ctx, Collection collection, Object value) {
        if (ctx.configuration().jsonProvider().isArray(value)) {
            for (Object o : ctx.configuration().jsonProvider().toIterable(value)) {
                if (o != null && expectedType.isAssignableFrom(o.getClass())) {
                    collection.add(o);
                } else if (o != null && expectedType == String.class) {
                    collection.add(o.toString());
                }
            }
            return;
        }
        if (value != null && expectedType.isAssignableFrom(value.getClass())) {
            collection.add(value);
        }
    }
}
