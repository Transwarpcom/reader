package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonNull.class */
public final class BsonNull extends BsonValue {
    public static final BsonNull VALUE = new BsonNull();

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.NULL;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "BsonNull";
    }
}
