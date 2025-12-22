package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonUndefined.class */
public final class BsonUndefined extends BsonValue {
    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.UNDEFINED;
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
}
