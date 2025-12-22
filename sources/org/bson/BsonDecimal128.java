package org.bson;

import org.bson.assertions.Assertions;
import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDecimal128.class */
public final class BsonDecimal128 extends BsonNumber {
    private final Decimal128 value;

    public BsonDecimal128(Decimal128 value) {
        Assertions.notNull("value", value);
        this.value = value;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.DECIMAL128;
    }

    public Decimal128 getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonDecimal128 that = (BsonDecimal128) o;
        if (!this.value.equals(that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "BsonDecimal128{value=" + this.value + '}';
    }

    @Override // org.bson.BsonNumber
    public int intValue() {
        return this.value.bigDecimalValue().intValue();
    }

    @Override // org.bson.BsonNumber
    public long longValue() {
        return this.value.bigDecimalValue().longValue();
    }

    @Override // org.bson.BsonNumber
    public double doubleValue() {
        return this.value.bigDecimalValue().doubleValue();
    }

    @Override // org.bson.BsonNumber
    public Decimal128 decimal128Value() {
        return this.value;
    }
}
