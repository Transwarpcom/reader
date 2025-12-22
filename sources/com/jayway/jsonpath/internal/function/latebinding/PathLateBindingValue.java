package com.jayway.jsonpath.internal.function.latebinding;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.internal.Path;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/latebinding/PathLateBindingValue.class */
public class PathLateBindingValue implements ILateBindingValue {
    private final Path path;
    private final String rootDocument;
    private final Configuration configuration;
    private final Object result;

    public PathLateBindingValue(Path path, Object rootDocument, Configuration configuration) {
        this.path = path;
        this.rootDocument = rootDocument.toString();
        this.configuration = configuration;
        this.result = path.evaluate(rootDocument, rootDocument, configuration).getValue();
    }

    @Override // com.jayway.jsonpath.internal.function.latebinding.ILateBindingValue
    public Object get() {
        return this.result;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PathLateBindingValue that = (PathLateBindingValue) o;
        return Objects.equals(this.path, that.path) && this.rootDocument.equals(that.rootDocument) && Objects.equals(this.configuration, that.configuration);
    }
}
