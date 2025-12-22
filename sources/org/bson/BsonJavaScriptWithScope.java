package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonJavaScriptWithScope.class */
public class BsonJavaScriptWithScope extends BsonValue {
    private final String code;
    private final BsonDocument scope;

    public BsonJavaScriptWithScope(String code, BsonDocument scope) {
        if (code == null) {
            throw new IllegalArgumentException("code can not be null");
        }
        if (scope == null) {
            throw new IllegalArgumentException("scope can not be null");
        }
        this.code = code;
        this.scope = scope;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.JAVASCRIPT_WITH_SCOPE;
    }

    public String getCode() {
        return this.code;
    }

    public BsonDocument getScope() {
        return this.scope;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonJavaScriptWithScope that = (BsonJavaScriptWithScope) o;
        if (!this.code.equals(that.code) || !this.scope.equals(that.scope)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.code.hashCode();
        return (31 * result) + this.scope.hashCode();
    }

    public String toString() {
        return "BsonJavaScriptWithScope{code=" + getCode() + "scope=" + this.scope + '}';
    }

    static BsonJavaScriptWithScope clone(BsonJavaScriptWithScope from) {
        return new BsonJavaScriptWithScope(from.code, from.scope.mo1002clone());
    }
}
