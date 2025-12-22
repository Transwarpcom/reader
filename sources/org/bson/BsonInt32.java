package org.bson;

import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonInt32.class */
public final class BsonInt32 extends BsonNumber implements Comparable<BsonInt32> {
    private final int value;

    public BsonInt32(int value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(BsonInt32 o) {
        if (this.value < o.value) {
            return -1;
        }
        return this.value == o.value ? 0 : 1;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.INT32;
    }

    public int getValue() {
        return this.value;
    }

    @Override // org.bson.BsonNumber
    public int intValue() {
        return this.value;
    }

    @Override // org.bson.BsonNumber
    public long longValue() {
        return this.value;
    }

    @Override // org.bson.BsonNumber
    public Decimal128 decimal128Value() {
        return new Decimal128(this.value);
    }

    @Override // org.bson.BsonNumber
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonInt32 bsonInt32 = (BsonInt32) o;
        if (this.value != bsonInt32.value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return "BsonInt32{value=" + this.value + '}';
    }
}
