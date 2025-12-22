package com.jayway.jsonpath.internal.function.latebinding;

import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.spi.json.JsonProvider;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/latebinding/JsonLateBindingValue.class */
public class JsonLateBindingValue implements ILateBindingValue {
    private final JsonProvider jsonProvider;
    private final Parameter jsonParameter;

    public JsonLateBindingValue(JsonProvider jsonProvider, Parameter jsonParameter) {
        this.jsonProvider = jsonProvider;
        this.jsonParameter = jsonParameter;
    }

    @Override // com.jayway.jsonpath.internal.function.latebinding.ILateBindingValue
    public Object get() {
        return this.jsonProvider.parse(this.jsonParameter.getJson());
    }
}
