package org.bson.types;

import java.io.Serializable;
import java.util.Arrays;
import org.bson.BsonBinarySubType;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/Binary.class */
public class Binary implements Serializable {
    private static final long serialVersionUID = 7902997490338209467L;
    private final byte type;
    private final byte[] data;

    public Binary(byte[] data) {
        this(BsonBinarySubType.BINARY, data);
    }

    public Binary(BsonBinarySubType type, byte[] data) {
        this(type.getValue(), data);
    }

    public Binary(byte type, byte[] data) {
        this.type = type;
        this.data = (byte[]) data.clone();
    }

    public byte getType() {
        return this.type;
    }

    public byte[] getData() {
        return (byte[]) this.data.clone();
    }

    public int length() {
        return this.data.length;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Binary binary = (Binary) o;
        if (this.type != binary.type || !Arrays.equals(this.data, binary.data)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.type;
        return (31 * result) + Arrays.hashCode(this.data);
    }
}
