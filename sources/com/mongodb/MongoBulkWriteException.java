package com.mongodb;

import com.mongodb.bulk.BulkWriteError;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.WriteConcernError;
import com.mongodb.lang.Nullable;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoBulkWriteException.class */
public class MongoBulkWriteException extends MongoServerException {
    private static final long serialVersionUID = -4345399805987210275L;
    private final BulkWriteResult writeResult;
    private final List<BulkWriteError> errors;
    private final ServerAddress serverAddress;
    private final WriteConcernError writeConcernError;

    public MongoBulkWriteException(BulkWriteResult writeResult, List<BulkWriteError> writeErrors, @Nullable WriteConcernError writeConcernError, ServerAddress serverAddress) {
        super("Bulk write operation error on server " + serverAddress + ". " + (writeErrors.isEmpty() ? "" : "Write errors: " + writeErrors + ". ") + (writeConcernError == null ? "" : "Write concern error: " + writeConcernError + ". "), serverAddress);
        this.writeResult = writeResult;
        this.errors = writeErrors;
        this.writeConcernError = writeConcernError;
        this.serverAddress = serverAddress;
    }

    public BulkWriteResult getWriteResult() {
        return this.writeResult;
    }

    public List<BulkWriteError> getWriteErrors() {
        return this.errors;
    }

    public WriteConcernError getWriteConcernError() {
        return this.writeConcernError;
    }

    @Override // com.mongodb.MongoServerException
    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MongoBulkWriteException that = (MongoBulkWriteException) o;
        if (!this.errors.equals(that.errors) || !this.serverAddress.equals(that.serverAddress)) {
            return false;
        }
        if (this.writeConcernError != null) {
            if (!this.writeConcernError.equals(that.writeConcernError)) {
                return false;
            }
        } else if (that.writeConcernError != null) {
            return false;
        }
        if (!this.writeResult.equals(that.writeResult)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.writeResult.hashCode();
        return (31 * ((31 * ((31 * result) + this.errors.hashCode())) + this.serverAddress.hashCode())) + (this.writeConcernError != null ? this.writeConcernError.hashCode() : 0);
    }
}
