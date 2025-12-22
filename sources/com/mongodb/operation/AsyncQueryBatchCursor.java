package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.ServerCursor;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.QueryResult;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.FieldNameValidator;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncQueryBatchCursor.class */
class AsyncQueryBatchCursor<T> implements AsyncBatchCursor<T> {
    private static final FieldNameValidator NO_OP_FIELD_NAME_VALIDATOR = new NoOpFieldNameValidator();
    private final MongoNamespace namespace;
    private final int limit;
    private final Decoder<T> decoder;
    private final long maxTimeMS;
    private final AsyncConnectionSource connectionSource;
    private final AtomicReference<ServerCursor> cursor;
    private volatile QueryResult<T> firstBatch;
    private volatile int batchSize;
    private final AtomicBoolean isClosed = new AtomicBoolean();
    private final AtomicInteger count = new AtomicInteger();

    AsyncQueryBatchCursor(QueryResult<T> firstBatch, int limit, int batchSize, long maxTimeMS, Decoder<T> decoder, AsyncConnectionSource connectionSource, AsyncConnection connection) {
        Assertions.isTrueArgument("maxTimeMS >= 0", maxTimeMS >= 0);
        this.maxTimeMS = maxTimeMS;
        this.namespace = firstBatch.getNamespace();
        this.firstBatch = firstBatch;
        this.limit = limit;
        this.batchSize = batchSize;
        this.decoder = decoder;
        this.cursor = new AtomicReference<>(firstBatch.getCursor());
        this.connectionSource = (AsyncConnectionSource) Assertions.notNull("connectionSource", connectionSource);
        this.count.addAndGet(firstBatch.getResults().size());
        if (firstBatch.getCursor() != null) {
            connectionSource.retain();
            if (limitReached()) {
                killCursor(connection);
            }
        }
    }

    @Override // com.mongodb.async.AsyncBatchCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.isClosed.getAndSet(true)) {
            killCursorOnClose();
        }
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void next(SingleResultCallback<List<T>> callback) {
        next(callback, false);
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void tryNext(SingleResultCallback<List<T>> callback) {
        next(callback, true);
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void setBatchSize(int batchSize) {
        Assertions.isTrue("open", !this.isClosed.get());
        this.batchSize = batchSize;
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public int getBatchSize() {
        Assertions.isTrue("open", !this.isClosed.get());
        return this.batchSize;
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public boolean isClosed() {
        return this.isClosed.get();
    }

    private void next(SingleResultCallback<List<T>> callback, boolean tryNext) {
        if (isClosed()) {
            Object[] objArr = new Object[1];
            objArr[0] = tryNext ? "tryNext()" : "next()";
            callback.onResult(null, new MongoException(String.format("%s called after the cursor was closed.", objArr)));
            return;
        }
        if (this.firstBatch != null && (tryNext || !this.firstBatch.getResults().isEmpty())) {
            List<T> results = this.firstBatch.getResults();
            if (tryNext && results.isEmpty()) {
                results = null;
            }
            this.firstBatch = null;
            callback.onResult(results, null);
            return;
        }
        ServerCursor localCursor = getServerCursor();
        if (localCursor == null) {
            this.isClosed.set(true);
            callback.onResult(null, null);
        } else {
            getMore(localCursor, callback, tryNext);
        }
    }

    private boolean limitReached() {
        return Math.abs(this.limit) != 0 && this.count.get() >= Math.abs(this.limit);
    }

    private void getMore(final ServerCursor cursor, final SingleResultCallback<List<T>> callback, final boolean tryNext) {
        this.connectionSource.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.AsyncQueryBatchCursor.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(AsyncConnection connection, Throwable t) {
                if (t == null) {
                    AsyncQueryBatchCursor.this.getMore(connection, cursor, callback, tryNext);
                } else {
                    callback.onResult(null, t);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMore(AsyncConnection connection, ServerCursor cursor, SingleResultCallback<List<T>> callback, boolean tryNext) {
        if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
            connection.commandAsync(this.namespace.getDatabaseName(), asGetMoreCommandDocument(cursor.getId()), NO_OP_FIELD_NAME_VALIDATOR, ReadPreference.primary(), CommandResultDocumentCodec.create(this.decoder, "nextBatch"), this.connectionSource.getSessionContext(), new CommandResultSingleResultCallback(connection, cursor, callback, tryNext));
        } else {
            connection.getMoreAsync(this.namespace, cursor.getId(), CursorHelper.getNumberToReturn(this.limit, this.batchSize, this.count.get()), this.decoder, new QueryResultSingleResultCallback(connection, callback, tryNext));
        }
    }

    private BsonDocument asGetMoreCommandDocument(long cursorId) {
        BsonDocument document = new BsonDocument("getMore", new BsonInt64(cursorId)).append("collection", new BsonString(this.namespace.getCollectionName()));
        int batchSizeForGetMoreCommand = Math.abs(CursorHelper.getNumberToReturn(this.limit, this.batchSize, this.count.get()));
        if (batchSizeForGetMoreCommand != 0) {
            document.append("batchSize", new BsonInt32(batchSizeForGetMoreCommand));
        }
        if (this.maxTimeMS != 0) {
            document.append("maxTimeMS", new BsonInt64(this.maxTimeMS));
        }
        return document;
    }

    private void killCursorOnClose() {
        final ServerCursor localCursor = getServerCursor();
        if (localCursor != null) {
            this.connectionSource.getConnection(new SingleResultCallback<AsyncConnection>() { // from class: com.mongodb.operation.AsyncQueryBatchCursor.2
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(AsyncConnection connection, Throwable t) {
                    if (t != null) {
                        AsyncQueryBatchCursor.this.connectionSource.release();
                    } else {
                        AsyncQueryBatchCursor.this.killCursorAsynchronouslyAndReleaseConnectionAndSource(connection, localCursor);
                    }
                }
            });
        }
    }

    private void killCursor(AsyncConnection connection) {
        ServerCursor localCursor = this.cursor.getAndSet(null);
        if (localCursor != null) {
            killCursorAsynchronouslyAndReleaseConnectionAndSource(connection.retain(), localCursor);
        } else {
            this.connectionSource.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killCursorAsynchronouslyAndReleaseConnectionAndSource(final AsyncConnection connection, ServerCursor localCursor) {
        if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
            connection.commandAsync(this.namespace.getDatabaseName(), asKillCursorsCommandDocument(localCursor), NO_OP_FIELD_NAME_VALIDATOR, ReadPreference.primary(), new BsonDocumentCodec(), this.connectionSource.getSessionContext(), new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.operation.AsyncQueryBatchCursor.3
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(BsonDocument result, Throwable t) {
                    connection.release();
                    AsyncQueryBatchCursor.this.connectionSource.release();
                }
            });
        } else {
            connection.killCursorAsync(this.namespace, Collections.singletonList(Long.valueOf(localCursor.getId())), new SingleResultCallback<Void>() { // from class: com.mongodb.operation.AsyncQueryBatchCursor.4
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(Void result, Throwable t) {
                    connection.release();
                    AsyncQueryBatchCursor.this.connectionSource.release();
                }
            });
        }
    }

    private BsonDocument asKillCursorsCommandDocument(ServerCursor localCursor) {
        return new BsonDocument("killCursors", new BsonString(this.namespace.getCollectionName())).append("cursors", new BsonArray(Collections.singletonList(new BsonInt64(localCursor.getId()))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGetMoreQueryResult(AsyncConnection connection, SingleResultCallback<List<T>> callback, QueryResult<T> result, boolean tryNext) {
        if (isClosed()) {
            connection.release();
            Object[] objArr = new Object[1];
            objArr[0] = tryNext ? "tryNext()" : "next()";
            callback.onResult(null, new MongoException(String.format("The cursor was closed before %s completed.", objArr)));
            return;
        }
        this.cursor.getAndSet(result.getCursor());
        if (!tryNext && result.getResults().isEmpty() && result.getCursor() != null) {
            getMore(connection, result.getCursor(), callback, false);
            return;
        }
        this.count.addAndGet(result.getResults().size());
        if (limitReached()) {
            killCursor(connection);
            connection.release();
        } else {
            connection.release();
            if (result.getCursor() == null) {
                this.connectionSource.release();
            }
        }
        if (result.getResults().isEmpty()) {
            callback.onResult(null, null);
        } else {
            callback.onResult(result.getResults(), null);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncQueryBatchCursor$CommandResultSingleResultCallback.class */
    private class CommandResultSingleResultCallback implements SingleResultCallback<BsonDocument> {
        private final AsyncConnection connection;
        private final ServerCursor cursor;
        private final SingleResultCallback<List<T>> callback;
        private final boolean tryNext;

        CommandResultSingleResultCallback(AsyncConnection connection, ServerCursor cursor, SingleResultCallback<List<T>> callback, boolean tryNext) {
            this.connection = connection;
            this.cursor = cursor;
            this.callback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
            this.tryNext = tryNext;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(BsonDocument result, Throwable t) {
            Throwable thTranslateCommandException;
            if (t != null) {
                if (t instanceof MongoCommandException) {
                    thTranslateCommandException = QueryHelper.translateCommandException((MongoCommandException) t, this.cursor);
                } else {
                    thTranslateCommandException = t;
                }
                Throwable translatedException = thTranslateCommandException;
                this.connection.release();
                this.callback.onResult(null, translatedException);
                return;
            }
            QueryResult<T> queryResult = OperationHelper.getMoreCursorDocumentToQueryResult(result.getDocument("cursor"), this.connection.getDescription().getServerAddress());
            AsyncQueryBatchCursor.this.handleGetMoreQueryResult(this.connection, this.callback, queryResult, this.tryNext);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncQueryBatchCursor$QueryResultSingleResultCallback.class */
    private class QueryResultSingleResultCallback implements SingleResultCallback<QueryResult<T>> {
        private final AsyncConnection connection;
        private final SingleResultCallback<List<T>> callback;
        private final boolean tryNext;

        QueryResultSingleResultCallback(AsyncConnection connection, SingleResultCallback<List<T>> callback, boolean tryNext) {
            this.connection = connection;
            this.callback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
            this.tryNext = tryNext;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(QueryResult<T> result, Throwable t) {
            if (t == null) {
                AsyncQueryBatchCursor.this.handleGetMoreQueryResult(this.connection, this.callback, result, this.tryNext);
            } else {
                this.connection.release();
                this.callback.onResult(null, t);
            }
        }
    }

    ServerCursor getServerCursor() {
        return this.cursor.get();
    }
}
