package com.mongodb.bulk;

import com.mongodb.bulk.WriteRequest;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/BulkWriteResult.class */
public abstract class BulkWriteResult {
    public abstract boolean wasAcknowledged();

    public abstract int getInsertedCount();

    public abstract int getMatchedCount();

    public abstract int getDeletedCount();

    public abstract boolean isModifiedCountAvailable();

    public abstract int getModifiedCount();

    public abstract List<BulkWriteUpsert> getUpserts();

    public static BulkWriteResult acknowledged(WriteRequest.Type type, int count, List<BulkWriteUpsert> upserts) {
        return acknowledged(type, count, 0, upserts);
    }

    public static BulkWriteResult acknowledged(WriteRequest.Type type, int count, Integer modifiedCount, List<BulkWriteUpsert> upserts) {
        return acknowledged(type == WriteRequest.Type.INSERT ? count : 0, (type == WriteRequest.Type.UPDATE || type == WriteRequest.Type.REPLACE) ? count : 0, type == WriteRequest.Type.DELETE ? count : 0, modifiedCount, upserts);
    }

    public static BulkWriteResult acknowledged(final int insertedCount, final int matchedCount, final int removedCount, final Integer modifiedCount, final List<BulkWriteUpsert> upserts) {
        return new BulkWriteResult() { // from class: com.mongodb.bulk.BulkWriteResult.1
            @Override // com.mongodb.bulk.BulkWriteResult
            public boolean wasAcknowledged() {
                return true;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getInsertedCount() {
                return insertedCount;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getMatchedCount() {
                return matchedCount;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getDeletedCount() {
                return removedCount;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public boolean isModifiedCountAvailable() {
                return modifiedCount != null;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getModifiedCount() {
                if (modifiedCount == null) {
                    throw new UnsupportedOperationException("The modifiedCount is not available because at least one of the servers that was updated was not able to provide this information (the server is must be at least version 2.6");
                }
                return modifiedCount.intValue();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public List<BulkWriteUpsert> getUpserts() {
                return upserts;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                BulkWriteResult that = (BulkWriteResult) o;
                if (!that.wasAcknowledged() || insertedCount != that.getInsertedCount() || isModifiedCountAvailable() != that.isModifiedCountAvailable()) {
                    return false;
                }
                if ((modifiedCount != null && !modifiedCount.equals(Integer.valueOf(that.getModifiedCount()))) || removedCount != that.getDeletedCount() || matchedCount != that.getMatchedCount() || !upserts.equals(that.getUpserts())) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                int result = upserts.hashCode();
                return (31 * ((31 * ((31 * ((31 * result) + insertedCount)) + matchedCount)) + removedCount)) + (modifiedCount != null ? modifiedCount.hashCode() : 0);
            }

            public String toString() {
                return "AcknowledgedBulkWriteResult{insertedCount=" + insertedCount + ", matchedCount=" + matchedCount + ", removedCount=" + removedCount + ", modifiedCount=" + modifiedCount + ", upserts=" + upserts + '}';
            }
        };
    }

    public static BulkWriteResult unacknowledged() {
        return new BulkWriteResult() { // from class: com.mongodb.bulk.BulkWriteResult.2
            @Override // com.mongodb.bulk.BulkWriteResult
            public boolean wasAcknowledged() {
                return false;
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getInsertedCount() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getMatchedCount() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getDeletedCount() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public boolean isModifiedCountAvailable() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public int getModifiedCount() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.bulk.BulkWriteResult
            public List<BulkWriteUpsert> getUpserts() {
                throw getUnacknowledgedWriteException();
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                BulkWriteResult that = (BulkWriteResult) o;
                return !that.wasAcknowledged();
            }

            public int hashCode() {
                return 0;
            }

            public String toString() {
                return "UnacknowledgedBulkWriteResult{}";
            }

            private UnsupportedOperationException getUnacknowledgedWriteException() {
                return new UnsupportedOperationException("Cannot get information about an unacknowledged write");
            }
        };
    }
}
