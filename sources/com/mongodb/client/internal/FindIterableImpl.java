package com.mongodb.client.internal;

import com.mongodb.CursorType;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.FindOptions;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.concurrent.TimeUnit;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/FindIterableImpl.class */
final class FindIterableImpl<TDocument, TResult> extends MongoIterableImpl<TResult> implements FindIterable<TResult> {
    private final SyncOperations<TDocument> operations;
    private final Class<TResult> resultClass;
    private final FindOptions findOptions;
    private Bson filter;

    FindIterableImpl(@Nullable ClientSession clientSession, MongoNamespace namespace, Class<TDocument> documentClass, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, OperationExecutor executor, Bson filter) {
        super(clientSession, executor, readConcern, readPreference);
        this.operations = new SyncOperations<>(namespace, documentClass, readPreference, codecRegistry);
        this.resultClass = (Class) Assertions.notNull("resultClass", resultClass);
        this.filter = (Bson) Assertions.notNull("filter", filter);
        this.findOptions = new FindOptions();
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> filter(@Nullable Bson filter) {
        this.filter = filter;
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> limit(int limit) {
        this.findOptions.limit(limit);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> skip(int skip) {
        this.findOptions.skip(skip);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.findOptions.maxTime(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.findOptions.maxAwaitTime(maxAwaitTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public FindIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        this.findOptions.batchSize(batchSize);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> collation(@Nullable Collation collation) {
        this.findOptions.collation(collation);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> modifiers(@Nullable Bson modifiers) {
        this.findOptions.modifiers(modifiers);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> projection(@Nullable Bson projection) {
        this.findOptions.projection(projection);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> sort(@Nullable Bson sort) {
        this.findOptions.sort(sort);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> noCursorTimeout(boolean noCursorTimeout) {
        this.findOptions.noCursorTimeout(noCursorTimeout);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> oplogReplay(boolean oplogReplay) {
        this.findOptions.oplogReplay(oplogReplay);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> partial(boolean partial) {
        this.findOptions.partial(partial);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> cursorType(CursorType cursorType) {
        this.findOptions.cursorType(cursorType);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> comment(@Nullable String comment) {
        this.findOptions.comment(comment);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> hint(@Nullable Bson hint) {
        this.findOptions.hint(hint);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> max(@Nullable Bson max) {
        this.findOptions.max(max);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> min(@Nullable Bson min) {
        this.findOptions.min(min);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    @Deprecated
    public FindIterable<TResult> maxScan(long maxScan) {
        this.findOptions.maxScan(maxScan);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> returnKey(boolean returnKey) {
        this.findOptions.returnKey(returnKey);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    public FindIterable<TResult> showRecordId(boolean showRecordId) {
        this.findOptions.showRecordId(showRecordId);
        return this;
    }

    @Override // com.mongodb.client.FindIterable
    @Deprecated
    public FindIterable<TResult> snapshot(boolean snapshot) {
        this.findOptions.snapshot(snapshot);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    @Nullable
    public TResult first() {
        BatchCursor<TResult> batchCursor = (BatchCursor) getExecutor().execute(this.operations.findFirst(this.filter, this.resultClass, this.findOptions), getReadPreference(), getReadConcern(), getClientSession());
        try {
            return batchCursor.hasNext() ? batchCursor.next().iterator().next() : null;
        } finally {
            batchCursor.close();
        }
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        return this.operations.find(this.filter, this.resultClass, this.findOptions);
    }
}
