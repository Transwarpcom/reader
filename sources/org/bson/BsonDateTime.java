package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDateTime.class */
public class BsonDateTime extends BsonValue implements Comparable<BsonDateTime> {
    private final long value;

    public BsonDateTime(long value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(BsonDateTime o) {
        return Long.valueOf(this.value).compareTo(Long.valueOf(o.value));
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.DATE_TIME;
    }

    public long getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonDateTime that = (BsonDateTime) o;
        if (this.value != that.value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.value ^ (this.value >>> 32));
    }

    public String toString() {
        return "BsonDateTime{value=" + this.value + '}';
    }
}
