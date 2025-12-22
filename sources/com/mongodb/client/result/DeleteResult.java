package com.mongodb.client.result;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/DeleteResult.class */
public abstract class DeleteResult {
    public abstract boolean wasAcknowledged();

    public abstract long getDeletedCount();

    public static DeleteResult acknowledged(long deletedCount) {
        return new AcknowledgedDeleteResult(deletedCount);
    }

    public static DeleteResult unacknowledged() {
        return new UnacknowledgedDeleteResult();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/DeleteResult$AcknowledgedDeleteResult.class */
    private static class AcknowledgedDeleteResult extends DeleteResult {
        private final long deletedCount;

        AcknowledgedDeleteResult(long deletedCount) {
            this.deletedCount = deletedCount;
        }

        @Override // com.mongodb.client.result.DeleteResult
        public boolean wasAcknowledged() {
            return true;
        }

        @Override // com.mongodb.client.result.DeleteResult
        public long getDeletedCount() {
            return this.deletedCount;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AcknowledgedDeleteResult that = (AcknowledgedDeleteResult) o;
            if (this.deletedCount != that.deletedCount) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (int) (this.deletedCount ^ (this.deletedCount >>> 32));
        }

        public String toString() {
            return "AcknowledgedDeleteResult{deletedCount=" + this.deletedCount + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/result/DeleteResult$UnacknowledgedDeleteResult.class */
    private static class UnacknowledgedDeleteResult extends DeleteResult {
        private UnacknowledgedDeleteResult() {
        }

        @Override // com.mongodb.client.result.DeleteResult
        public boolean wasAcknowledged() {
            return false;
        }

        @Override // com.mongodb.client.result.DeleteResult
        public long getDeletedCount() {
            throw new UnsupportedOperationException("Cannot get information about an unacknowledged delete");
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
            return "UnacknowledgedDeleteResult{}";
        }
    }
}
