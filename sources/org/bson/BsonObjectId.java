package org.bson;

import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonObjectId.class */
public class BsonObjectId extends BsonValue implements Comparable<BsonObjectId> {
    private final ObjectId value;

    public BsonObjectId() {
        this(new ObjectId());
    }

    public BsonObjectId(ObjectId value) {
        if (value == null) {
            throw new IllegalArgumentException("value may not be null");
        }
        this.value = value;
    }

    public ObjectId getValue() {
        return this.value;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.OBJECT_ID;
    }

    @Override // java.lang.Comparable
    public int compareTo(BsonObjectId o) {
        return this.value.compareTo(o.value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonObjectId that = (BsonObjectId) o;
        if (!this.value.equals(that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "BsonObjectId{value=" + this.value.toHexString() + '}';
    }
}
