package org.bson;

import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDbPointer.class */
public class BsonDbPointer extends BsonValue {
    private final String namespace;
    private final ObjectId id;

    public BsonDbPointer(String namespace, ObjectId id) {
        if (namespace == null) {
            throw new IllegalArgumentException("namespace can not be null");
        }
        if (id == null) {
            throw new IllegalArgumentException("id can not be null");
        }
        this.namespace = namespace;
        this.id = id;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.DB_POINTER;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public ObjectId getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonDbPointer dbPointer = (BsonDbPointer) o;
        if (!this.id.equals(dbPointer.id) || !this.namespace.equals(dbPointer.namespace)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.namespace.hashCode();
        return (31 * result) + this.id.hashCode();
    }

    public String toString() {
        return "BsonDbPointer{namespace='" + this.namespace + "', id=" + this.id + '}';
    }
}
