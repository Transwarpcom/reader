package org.bson;

import org.bson.internal.UnsignedLongs;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonTimestamp.class */
public final class BsonTimestamp extends BsonValue implements Comparable<BsonTimestamp> {
    private final long value;

    public BsonTimestamp() {
        this.value = 0L;
    }

    public BsonTimestamp(long value) {
        this.value = value;
    }

    public BsonTimestamp(int seconds, int increment) {
        this.value = (seconds << 32) | (increment & 4294967295L);
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.TIMESTAMP;
    }

    public long getValue() {
        return this.value;
    }

    public int getTime() {
        return (int) (this.value >> 32);
    }

    public int getInc() {
        return (int) this.value;
    }

    public String toString() {
        return "Timestamp{value=" + getValue() + ", seconds=" + getTime() + ", inc=" + getInc() + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(BsonTimestamp ts) {
        return UnsignedLongs.compare(this.value, ts.value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonTimestamp timestamp = (BsonTimestamp) o;
        if (this.value != timestamp.value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.value ^ (this.value >>> 32));
    }
}
