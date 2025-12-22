package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonMaxKey.class */
public final class BsonMaxKey extends BsonValue {
    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.MAX_KEY;
    }

    public boolean equals(Object o) {
        return o instanceof BsonMaxKey;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "BsonMaxKey";
    }
}
