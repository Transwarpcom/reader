package com.mongodb.client.internal;

import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoIterableImpl.class */
public abstract class MongoIterableImpl<TResult> implements MongoIterable<TResult> {
    private final ClientSession clientSession;
    private final ReadConcern readConcern;
    private final OperationExecutor executor;
    private final ReadPreference readPreference;
    private Integer batchSize;

    public abstract ReadOperation<BatchCursor<TResult>> asReadOperation();

    public MongoIterableImpl(@Nullable ClientSession clientSession, OperationExecutor executor, ReadConcern readConcern, ReadPreference readPreference) {
        this.clientSession = clientSession;
        this.executor = (OperationExecutor) Assertions.notNull("executor", executor);
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
    }

    @Nullable
    ClientSession getClientSession() {
        return this.clientSession;
    }

    OperationExecutor getExecutor() {
        return this.executor;
    }

    ReadPreference getReadPreference() {
        return this.readPreference;
    }

    protected ReadConcern getReadConcern() {
        return this.readConcern;
    }

    @Nullable
    public Integer getBatchSize() {
        return this.batchSize;
    }

    @Override // com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public MongoIterable<TResult> batchSize2(int batchSize) {
        this.batchSize = Integer.valueOf(batchSize);
        return this;
    }

    @Override // java.lang.Iterable
    public MongoCursor<TResult> iterator() {
        return new MongoBatchCursorAdapter(execute());
    }

    @Override // com.mongodb.client.MongoIterable
    @Nullable
    public TResult first() {
        MongoCursor<TResult> cursor = iterator();
        try {
            if (!cursor.hasNext()) {
                return null;
            }
            return cursor.next();
        } finally {
            cursor.close();
        }
    }

    @Override // com.mongodb.client.MongoIterable
    public <U> MongoIterable<U> map(Function<TResult, U> mapper) {
        return new MappingIterable(this, mapper);
    }

    @Override // com.mongodb.client.MongoIterable
    public void forEach(Block<? super TResult> block) {
        MongoCursor<TResult> cursor = iterator();
        while (cursor.hasNext()) {
            try {
                block.apply(cursor.next());
            } finally {
                cursor.close();
            }
        }
    }

    @Override // com.mongodb.client.MongoIterable
    public <A extends Collection<? super TResult>> A into(final A target) {
        forEach(new Block<TResult>() { // from class: com.mongodb.client.internal.MongoIterableImpl.1
            @Override // com.mongodb.Block
            public void apply(TResult t) {
                target.add(t);
            }
        });
        return target;
    }

    private BatchCursor<TResult> execute() {
        return (BatchCursor) this.executor.execute(asReadOperation(), this.readPreference, this.readConcern, this.clientSession);
    }
}
