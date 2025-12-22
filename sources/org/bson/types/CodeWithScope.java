package org.bson.types;

import org.bson.Document;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/CodeWithScope.class */
public class CodeWithScope extends Code {
    private final Document scope;
    private static final long serialVersionUID = -6284832275113680002L;

    public CodeWithScope(String code, Document scope) {
        super(code);
        this.scope = scope;
    }

    public Document getScope() {
        return this.scope;
    }

    @Override // org.bson.types.Code
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        CodeWithScope that = (CodeWithScope) o;
        if (this.scope != null) {
            if (!this.scope.equals(that.scope)) {
                return false;
            }
            return true;
        }
        if (that.scope != null) {
            return false;
        }
        return true;
    }

    @Override // org.bson.types.Code
    public int hashCode() {
        return getCode().hashCode() ^ this.scope.hashCode();
    }
}
