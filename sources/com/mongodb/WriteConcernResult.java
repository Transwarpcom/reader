package com.mongodb;

import com.mongodb.lang.Nullable;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/WriteConcernResult.class */
public abstract class WriteConcernResult {
    public abstract boolean wasAcknowledged();

    public abstract int getCount();

    public abstract boolean isUpdateOfExisting();

    @Nullable
    public abstract BsonValue getUpsertedId();

    public static WriteConcernResult acknowledged(final int count, final boolean isUpdateOfExisting, @Nullable final BsonValue upsertedId) {
        return new WriteConcernResult() { // from class: com.mongodb.WriteConcernResult.1
            @Override // com.mongodb.WriteConcernResult
            public boolean wasAcknowledged() {
                return true;
            }

            @Override // com.mongodb.WriteConcernResult
            public int getCount() {
                return count;
            }

            @Override // com.mongodb.WriteConcernResult
            public boolean isUpdateOfExisting() {
                return isUpdateOfExisting;
            }

            @Override // com.mongodb.WriteConcernResult
            @Nullable
            public BsonValue getUpsertedId() {
                return upsertedId;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                WriteConcernResult that = (WriteConcernResult) o;
                if (!that.wasAcknowledged() || count != that.getCount() || isUpdateOfExisting != that.isUpdateOfExisting()) {
                    return false;
                }
                if (upsertedId != null) {
                    if (!upsertedId.equals(that.getUpsertedId())) {
                        return false;
                    }
                    return true;
                }
                if (that.getUpsertedId() != null) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                int result = count;
                return (31 * ((31 * result) + (isUpdateOfExisting ? 1 : 0))) + (upsertedId != null ? upsertedId.hashCode() : 0);
            }

            public String toString() {
                return "AcknowledgedWriteResult{count=" + count + ", isUpdateOfExisting=" + isUpdateOfExisting + ", upsertedId=" + upsertedId + '}';
            }
        };
    }

    public static WriteConcernResult unacknowledged() {
        return new WriteConcernResult() { // from class: com.mongodb.WriteConcernResult.2
            @Override // com.mongodb.WriteConcernResult
            public boolean wasAcknowledged() {
                return false;
            }

            @Override // com.mongodb.WriteConcernResult
            public int getCount() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.WriteConcernResult
            public boolean isUpdateOfExisting() {
                throw getUnacknowledgedWriteException();
            }

            @Override // com.mongodb.WriteConcernResult
            public BsonValue getUpsertedId() {
                throw getUnacknowledgedWriteException();
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                WriteConcernResult that = (WriteConcernResult) o;
                return !that.wasAcknowledged();
            }

            public int hashCode() {
                return 1;
            }

            public String toString() {
                return "UnacknowledgedWriteResult{}";
            }

            private UnsupportedOperationException getUnacknowledgedWriteException() {
                return new UnsupportedOperationException("Cannot get information about an unacknowledged write");
            }
        };
    }
}
