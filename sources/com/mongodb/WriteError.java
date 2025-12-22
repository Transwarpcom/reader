package com.mongodb;

import com.mongodb.assertions.Assertions;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/WriteError.class */
public class WriteError {
    private final int code;
    private final String message;
    private final BsonDocument details;

    public WriteError(int code, String message, BsonDocument details) {
        this.code = code;
        this.message = (String) Assertions.notNull("message", message);
        this.details = (BsonDocument) Assertions.notNull("details", details);
    }

    public WriteError(WriteError writeError) {
        this.code = writeError.code;
        this.message = writeError.message;
        this.details = writeError.details;
    }

    public ErrorCategory getCategory() {
        return ErrorCategory.fromErrorCode(this.code);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public BsonDocument getDetails() {
        return this.details;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WriteError that = (WriteError) o;
        if (this.code != that.code || !this.details.equals(that.details) || !this.message.equals(that.message)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.code;
        return (31 * ((31 * result) + this.message.hashCode())) + this.details.hashCode();
    }

    public String toString() {
        return "WriteError{code=" + this.code + ", message='" + this.message + "', details=" + this.details + '}';
    }
}
