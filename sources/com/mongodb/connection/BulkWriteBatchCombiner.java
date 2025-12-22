package com.mongodb.connection;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.BulkWriteError;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.BulkWriteUpsert;
import com.mongodb.bulk.WriteConcernError;
import com.mongodb.internal.connection.IndexMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/BulkWriteBatchCombiner.class */
public class BulkWriteBatchCombiner {
    private final ServerAddress serverAddress;
    private final boolean ordered;
    private final WriteConcern writeConcern;
    private int insertedCount;
    private int matchedCount;
    private int deletedCount;
    private Integer modifiedCount = 0;
    private final Set<BulkWriteUpsert> writeUpserts = new TreeSet(new Comparator<BulkWriteUpsert>() { // from class: com.mongodb.connection.BulkWriteBatchCombiner.1
        @Override // java.util.Comparator
        public int compare(BulkWriteUpsert o1, BulkWriteUpsert o2) {
            if (o1.getIndex() < o2.getIndex()) {
                return -1;
            }
            return o1.getIndex() == o2.getIndex() ? 0 : 1;
        }
    });
    private final Set<BulkWriteError> writeErrors = new TreeSet(new Comparator<BulkWriteError>() { // from class: com.mongodb.connection.BulkWriteBatchCombiner.2
        @Override // java.util.Comparator
        public int compare(BulkWriteError o1, BulkWriteError o2) {
            if (o1.getIndex() < o2.getIndex()) {
                return -1;
            }
            return o1.getIndex() == o2.getIndex() ? 0 : 1;
        }
    });
    private final List<WriteConcernError> writeConcernErrors = new ArrayList();

    public BulkWriteBatchCombiner(ServerAddress serverAddress, boolean ordered, WriteConcern writeConcern) {
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
        this.ordered = ordered;
        this.serverAddress = (ServerAddress) Assertions.notNull("serverAddress", serverAddress);
    }

    public void addResult(BulkWriteResult result, IndexMap indexMap) {
        this.insertedCount += result.getInsertedCount();
        this.matchedCount += result.getMatchedCount();
        this.deletedCount += result.getDeletedCount();
        if (result.isModifiedCountAvailable() && this.modifiedCount != null) {
            this.modifiedCount = Integer.valueOf(this.modifiedCount.intValue() + result.getModifiedCount());
        } else {
            this.modifiedCount = null;
        }
        mergeUpserts(result.getUpserts(), indexMap);
    }

    public void addErrorResult(MongoBulkWriteException exception, IndexMap indexMap) {
        addResult(exception.getWriteResult(), indexMap);
        mergeWriteErrors(exception.getWriteErrors(), indexMap);
        mergeWriteConcernError(exception.getWriteConcernError());
    }

    public void addWriteErrorResult(BulkWriteError writeError, IndexMap indexMap) {
        Assertions.notNull("writeError", writeError);
        mergeWriteErrors(Arrays.asList(writeError), indexMap);
    }

    public void addWriteConcernErrorResult(WriteConcernError writeConcernError) {
        Assertions.notNull("writeConcernError", writeConcernError);
        mergeWriteConcernError(writeConcernError);
    }

    public void addErrorResult(List<BulkWriteError> writeErrors, WriteConcernError writeConcernError, IndexMap indexMap) {
        mergeWriteErrors(writeErrors, indexMap);
        mergeWriteConcernError(writeConcernError);
    }

    public BulkWriteResult getResult() {
        throwOnError();
        return createResult();
    }

    public boolean shouldStopSendingMoreBatches() {
        return this.ordered && hasWriteErrors();
    }

    public boolean hasErrors() {
        return hasWriteErrors() || hasWriteConcernErrors();
    }

    public MongoBulkWriteException getError() {
        if (hasErrors()) {
            return new MongoBulkWriteException(createResult(), new ArrayList(this.writeErrors), this.writeConcernErrors.isEmpty() ? null : this.writeConcernErrors.get(this.writeConcernErrors.size() - 1), this.serverAddress);
        }
        return null;
    }

    private void mergeWriteConcernError(WriteConcernError writeConcernError) {
        if (writeConcernError != null) {
            if (this.writeConcernErrors.isEmpty()) {
                this.writeConcernErrors.add(writeConcernError);
            } else if (!writeConcernError.equals(this.writeConcernErrors.get(this.writeConcernErrors.size() - 1))) {
                this.writeConcernErrors.add(writeConcernError);
            }
        }
    }

    private void mergeWriteErrors(List<BulkWriteError> newWriteErrors, IndexMap indexMap) {
        for (BulkWriteError cur : newWriteErrors) {
            this.writeErrors.add(new BulkWriteError(cur.getCode(), cur.getMessage(), cur.getDetails(), indexMap.map(cur.getIndex())));
        }
    }

    private void mergeUpserts(List<BulkWriteUpsert> upserts, IndexMap indexMap) {
        for (BulkWriteUpsert bulkWriteUpsert : upserts) {
            this.writeUpserts.add(new BulkWriteUpsert(indexMap.map(bulkWriteUpsert.getIndex()), bulkWriteUpsert.getId()));
        }
    }

    private void throwOnError() {
        if (hasErrors()) {
            throw getError();
        }
    }

    private BulkWriteResult createResult() {
        if (this.writeConcern.isAcknowledged()) {
            return BulkWriteResult.acknowledged(this.insertedCount, this.matchedCount, this.deletedCount, this.modifiedCount, new ArrayList(this.writeUpserts));
        }
        return BulkWriteResult.unacknowledged();
    }

    private boolean hasWriteErrors() {
        return !this.writeErrors.isEmpty();
    }

    private boolean hasWriteConcernErrors() {
        return !this.writeConcernErrors.isEmpty();
    }
}
