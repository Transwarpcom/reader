package org.bson;

import java.util.Arrays;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonBinary.class */
public class BsonBinary extends BsonValue {
    private final byte type;
    private final byte[] data;

    public BsonBinary(byte[] data) {
        this(BsonBinarySubType.BINARY, data);
    }

    public BsonBinary(BsonBinarySubType type, byte[] data) {
        if (type == null) {
            throw new IllegalArgumentException("type may not be null");
        }
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }
        this.type = type.getValue();
        this.data = data;
    }

    public BsonBinary(byte type, byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }
        this.type = type;
        this.data = data;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.BINARY;
    }

    public byte getType() {
        return this.type;
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonBinary that = (BsonBinary) o;
        if (!Arrays.equals(this.data, that.data) || this.type != that.type) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.type;
        return (31 * result) + Arrays.hashCode(this.data);
    }

    public String toString() {
        return "BsonBinary{type=" + ((int) this.type) + ", data=" + Arrays.toString(this.data) + '}';
    }

    static BsonBinary clone(BsonBinary from) {
        return new BsonBinary(from.type, (byte[]) from.data.clone());
    }
}
