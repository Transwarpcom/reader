package org.bson.types;

import org.bson.BSONObject;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/CodeWScope.class */
public class CodeWScope extends Code {
    private final BSONObject scope;
    private static final long serialVersionUID = -6284832275113680002L;

    public CodeWScope(String code, BSONObject scope) {
        super(code);
        this.scope = scope;
    }

    public BSONObject getScope() {
        return this.scope;
    }

    @Override // org.bson.types.Code
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeWScope c = (CodeWScope) o;
        return getCode().equals(c.getCode()) && this.scope.equals(c.scope);
    }

    @Override // org.bson.types.Code
    public int hashCode() {
        return getCode().hashCode() ^ this.scope.hashCode();
    }
}
