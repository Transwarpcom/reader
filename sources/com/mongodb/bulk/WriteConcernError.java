package com.mongodb.bulk;

import com.mongodb.assertions.Assertions;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/WriteConcernError.class */
public class WriteConcernError {
    private final int code;
    private final String codeName;
    private final String message;
    private final BsonDocument details;

    public WriteConcernError(int code, String codeName, String message, BsonDocument details) {
        this.code = code;
        this.codeName = (String) Assertions.notNull("codeName", codeName);
        this.message = (String) Assertions.notNull("message", message);
        this.details = (BsonDocument) Assertions.notNull("details", details);
    }

    @Deprecated
    public WriteConcernError(int code, String message, BsonDocument details) {
        this(code, "", message, details);
    }

    public int getCode() {
        return this.code;
    }

    public String getCodeName() {
        return this.codeName;
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
        WriteConcernError that = (WriteConcernError) o;
        if (this.code != that.code || !this.codeName.equals(that.codeName) || !this.details.equals(that.details) || !this.message.equals(that.message)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.code;
        return (31 * ((31 * ((31 * result) + this.codeName.hashCode())) + this.message.hashCode())) + this.details.hashCode();
    }

    public String toString() {
        return "WriteConcernError{code=" + this.code + ", codeName='" + this.codeName + "', message='" + this.message + "', details=" + this.details + '}';
    }
}
