package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonString.class */
public class BsonString extends BsonValue implements Comparable<BsonString> {
    private final String value;

    public BsonString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(BsonString o) {
        return this.value.compareTo(o.value);
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.STRING;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonString that = (BsonString) o;
        if (!this.value.equals(that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "BsonString{value='" + this.value + "'}";
    }
}
