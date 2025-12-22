package com.mongodb.client.result;

import com.mongodb.lang.Nullable;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/UpdateResult.class */
public abstract class UpdateResult {
    public abstract boolean wasAcknowledged();

    public abstract long getMatchedCount();

    public abstract boolean isModifiedCountAvailable();

    public abstract long getModifiedCount();

    @Nullable
    public abstract BsonValue getUpsertedId();

    public static UpdateResult acknowledged(long matchedCount, @Nullable Long modifiedCount, @Nullable BsonValue upsertedId) {
        return new AcknowledgedUpdateResult(matchedCount, modifiedCount, upsertedId);
    }

    public static UpdateResult unacknowledged() {
        return new UnacknowledgedUpdateResult();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/UpdateResult$AcknowledgedUpdateResult.class */
    private static class AcknowledgedUpdateResult extends UpdateResult {
        private final long matchedCount;
        private final Long modifiedCount;
        private final BsonValue upsertedId;

        AcknowledgedUpdateResult(long matchedCount, @Nullable Long modifiedCount, @Nullable BsonValue upsertedId) {
            this.matchedCount = matchedCount;
            this.modifiedCount = modifiedCount;
            this.upsertedId = upsertedId;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public boolean wasAcknowledged() {
            return true;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public long getMatchedCount() {
            return this.matchedCount;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public boolean isModifiedCountAvailable() {
            return this.modifiedCount != null;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public long getModifiedCount() {
            if (this.modifiedCount == null) {
                throw new UnsupportedOperationException("Modified count is only available when connected to MongoDB 2.6 servers or above.");
            }
            return this.modifiedCount.longValue();
        }

        @Override // com.mongodb.client.result.UpdateResult
        @Nullable
        public BsonValue getUpsertedId() {
            return this.upsertedId;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AcknowledgedUpdateResult that = (AcknowledgedUpdateResult) o;
            if (this.matchedCount != that.matchedCount) {
                return false;
            }
            if (this.modifiedCount != null) {
                if (!this.modifiedCount.equals(that.modifiedCount)) {
                    return false;
                }
            } else if (that.modifiedCount != null) {
                return false;
            }
            if (this.upsertedId != null) {
                if (!this.upsertedId.equals(that.upsertedId)) {
                    return false;
                }
                return true;
            }
            if (that.upsertedId != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int result = (int) (this.matchedCount ^ (this.matchedCount >>> 32));
            return (31 * ((31 * result) + (this.modifiedCount != null ? this.modifiedCount.hashCode() : 0))) + (this.upsertedId != null ? this.upsertedId.hashCode() : 0);
        }

        public String toString() {
            return "AcknowledgedUpdateResult{matchedCount=" + this.matchedCount + ", modifiedCount=" + this.modifiedCount + ", upsertedId=" + this.upsertedId + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/UpdateResult$UnacknowledgedUpdateResult.class */
    private static class UnacknowledgedUpdateResult extends UpdateResult {
        private UnacknowledgedUpdateResult() {
        }

        @Override // com.mongodb.client.result.UpdateResult
        public boolean wasAcknowledged() {
            return false;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public long getMatchedCount() {
            throw getUnacknowledgedWriteException();
        }

        @Override // com.mongodb.client.result.UpdateResult
        public boolean isModifiedCountAvailable() {
            return false;
        }

        @Override // com.mongodb.client.result.UpdateResult
        public long getModifiedCount() {
            throw getUnacknowledgedWriteException();
        }

        @Override // com.mongodb.client.result.UpdateResult
        @Nullable
        public BsonValue getUpsertedId() {
            throw getUnacknowledgedWriteException();
        }

        private UnsupportedOperationException getUnacknowledgedWriteException() {
            return new UnsupportedOperationException("Cannot get information about an unacknowledged update");
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "UnacknowledgedUpdateResult{}";
        }
    }
}
