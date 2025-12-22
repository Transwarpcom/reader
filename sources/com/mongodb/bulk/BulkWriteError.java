package com.mongodb.bulk;

import com.mongodb.WriteError;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/BulkWriteError.class */
public class BulkWriteError extends WriteError {
    private final int index;

    public BulkWriteError(int code, String message, BsonDocument details, int index) {
        super(code, message, details);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // com.mongodb.WriteError
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BulkWriteError that = (BulkWriteError) o;
        if (this.index != that.index) {
            return false;
        }
        return super.equals(that);
    }

    @Override // com.mongodb.WriteError
    public int hashCode() {
        int result = super.hashCode();
        return (31 * result) + this.index;
    }

    @Override // com.mongodb.WriteError
    public String toString() {
        return "BulkWriteError{index=" + this.index + ", code=" + getCode() + ", message='" + getMessage() + "', details=" + getDetails() + '}';
    }
}
