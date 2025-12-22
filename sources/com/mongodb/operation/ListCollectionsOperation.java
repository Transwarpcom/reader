package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.connection.ServerType;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonRegularExpression;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ListCollectionsOperation.class */
public class ListCollectionsOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private final String databaseName;
    private final Decoder<T> decoder;
    private BsonDocument filter;
    private int batchSize;
    private long maxTimeMS;
    private boolean nameOnly;

    public ListCollectionsOperation(String databaseName, Decoder<T> decoder) {
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public boolean isNameOnly() {
        return this.nameOnly;
    }

    public ListCollectionsOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public ListCollectionsOperation<T> nameOnly(boolean nameOnly) {
        this.nameOnly = nameOnly;
        return this;
    }

    public Integer getBatchSize() {
        return Integer.valueOf(this.batchSize);
    }

    public ListCollectionsOperation<T> batchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public ListCollectionsOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.ListCollectionsOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotZero(connection.getDescription())) {
                    try {
                        return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, ListCollectionsOperation.this.databaseName, ListCollectionsOperation.this.getCommand(), ListCollectionsOperation.this.createCommandDecoder(), connection, ListCollectionsOperation.this.commandTransformer(source));
                    } catch (MongoCommandException e) {
                        return (BatchCursor) CommandOperationHelper.rethrowIfNotNamespaceError(e, OperationHelper.createEmptyBatchCursor(ListCollectionsOperation.this.createNamespace(), ListCollectionsOperation.this.decoder, source.getServerDescription().getAddress(), ListCollectionsOperation.this.batchSize));
                    }
                }
                return new ProjectingBatchCursor(new QueryBatchCursor(connection.query(ListCollectionsOperation.this.getNamespace(), ListCollectionsOperation.this.asQueryDocument(connection.getDescription(), binding.getReadPreference()), null, 0, 0, ListCollectionsOperation.this.batchSize, binding.getReadPreference().isSlaveOk(), false, false, false, false, false, new BsonDocumentCodec()), 0, ListCollectionsOperation.this.batchSize, new BsonDocumentCodec(), source));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.ListCollectionsOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(final AsyncConnectionSource source, final AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                    return;
                }
                final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotZero(connection.getDescription())) {
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, ListCollectionsOperation.this.databaseName, ListCollectionsOperation.this.getCommand(), ListCollectionsOperation.this.createCommandDecoder(), connection, ListCollectionsOperation.this.asyncTransformer(source, connection), new SingleResultCallback<AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.ListCollectionsOperation.2.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(AsyncBatchCursor<T> result, Throwable t2) {
                            if (t2 == null || CommandOperationHelper.isNamespaceError(t2)) {
                                wrappedCallback.onResult(result != null ? result : ListCollectionsOperation.this.emptyAsyncCursor(source), null);
                            } else {
                                wrappedCallback.onResult(null, t2);
                            }
                        }
                    });
                } else {
                    connection.queryAsync(ListCollectionsOperation.this.getNamespace(), ListCollectionsOperation.this.asQueryDocument(connection.getDescription(), binding.getReadPreference()), null, 0, 0, ListCollectionsOperation.this.batchSize, binding.getReadPreference().isSlaveOk(), false, false, false, false, false, new BsonDocumentCodec(), new SingleResultCallback<QueryResult<BsonDocument>>() { // from class: com.mongodb.operation.ListCollectionsOperation.2.2
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(QueryResult<BsonDocument> result, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                wrappedCallback.onResult(new ProjectingAsyncBatchCursor(new AsyncQueryBatchCursor(result, 0, ListCollectionsOperation.this.batchSize, 0L, new BsonDocumentCodec(), source, connection)), null);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncBatchCursor<T> emptyAsyncCursor(AsyncConnectionSource source) {
        return OperationHelper.createEmptyAsyncBatchCursor(createNamespace(), source.getServerDescription().getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoNamespace createNamespace() {
        return new MongoNamespace(this.databaseName, "$cmd.listCollections");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.ListCollectionsOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return OperationHelper.cursorDocumentToAsyncBatchCursor(result.getDocument("cursor"), ListCollectionsOperation.this.decoder, source, connection, ListCollectionsOperation.this.batchSize);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> commandTransformer(final ConnectionSource source) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.ListCollectionsOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return OperationHelper.cursorDocumentToBatchCursor(result.getDocument("cursor"), ListCollectionsOperation.this.decoder, source, ListCollectionsOperation.this.batchSize);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoNamespace getNamespace() {
        return new MongoNamespace(this.databaseName, "system.namespaces");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        BsonDocument command = new BsonDocument("listCollections", new BsonInt32(1)).append("cursor", CursorHelper.getCursorDocumentFromBatchSize(this.batchSize == 0 ? null : Integer.valueOf(this.batchSize)));
        if (this.filter != null) {
            command.append("filter", this.filter);
        }
        if (this.nameOnly) {
            command.append("nameOnly", BsonBoolean.TRUE);
        }
        if (this.maxTimeMS > 0) {
            command.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        return command;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument asQueryDocument(ConnectionDescription connectionDescription, ReadPreference readPreference) {
        BsonDocument document = new BsonDocument();
        BsonDocument transformedFilter = null;
        if (this.filter != null) {
            if (this.filter.containsKey("name")) {
                if (!this.filter.isString("name")) {
                    throw new IllegalArgumentException("When filtering collections on MongoDB versions < 3.0 the name field must be a string");
                }
                transformedFilter = new BsonDocument();
                transformedFilter.putAll(this.filter);
                transformedFilter.put("name", (BsonValue) new BsonString(String.format("%s.%s", this.databaseName, this.filter.getString("name").getValue())));
            } else {
                transformedFilter = this.filter;
            }
        }
        BsonDocument indexExcludingRegex = new BsonDocument("name", new BsonRegularExpression("^[^$]*$"));
        BsonDocument query = transformedFilter == null ? indexExcludingRegex : new BsonDocument("$and", new BsonArray(Arrays.asList(indexExcludingRegex, transformedFilter)));
        document.put("$query", (BsonValue) query);
        if (connectionDescription.getServerType() == ServerType.SHARD_ROUTER && !readPreference.equals(ReadPreference.primary())) {
            document.put("$readPreference", (BsonValue) readPreference.toDocument());
        }
        if (this.maxTimeMS > 0) {
            document.put("$maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        return document;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Codec<BsonDocument> createCommandDecoder() {
        return CommandResultDocumentCodec.create(this.decoder, "firstBatch");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ListCollectionsOperation$ProjectingBatchCursor.class */
    private final class ProjectingBatchCursor implements BatchCursor<T> {
        private final BatchCursor<BsonDocument> delegate;

        private ProjectingBatchCursor(BatchCursor<BsonDocument> delegate) {
            this.delegate = delegate;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.delegate.remove();
        }

        @Override // com.mongodb.operation.BatchCursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // com.mongodb.operation.BatchCursor, java.util.Iterator
        public boolean hasNext() {
            return this.delegate.hasNext();
        }

        @Override // java.util.Iterator
        public List<T> next() {
            return ListCollectionsOperation.this.projectFromFullNamespaceToCollectionName(this.delegate.next());
        }

        @Override // com.mongodb.operation.BatchCursor
        public void setBatchSize(int batchSize) {
            this.delegate.setBatchSize(batchSize);
        }

        @Override // com.mongodb.operation.BatchCursor
        public int getBatchSize() {
            return this.delegate.getBatchSize();
        }

        @Override // com.mongodb.operation.BatchCursor
        public List<T> tryNext() {
            return ListCollectionsOperation.this.projectFromFullNamespaceToCollectionName(this.delegate.tryNext());
        }

        @Override // com.mongodb.operation.BatchCursor
        public ServerCursor getServerCursor() {
            return this.delegate.getServerCursor();
        }

        @Override // com.mongodb.operation.BatchCursor
        public ServerAddress getServerAddress() {
            return this.delegate.getServerAddress();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ListCollectionsOperation$ProjectingAsyncBatchCursor.class */
    private final class ProjectingAsyncBatchCursor implements AsyncBatchCursor<T> {
        private final AsyncBatchCursor<BsonDocument> delegate;

        private ProjectingAsyncBatchCursor(AsyncBatchCursor<BsonDocument> delegate) {
            this.delegate = delegate;
        }

        @Override // com.mongodb.async.AsyncBatchCursor
        public void next(final SingleResultCallback<List<T>> callback) {
            this.delegate.next(new SingleResultCallback<List<BsonDocument>>() { // from class: com.mongodb.operation.ListCollectionsOperation.ProjectingAsyncBatchCursor.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(List<BsonDocument> result, Throwable t) {
                    if (t == null) {
                        callback.onResult(ListCollectionsOperation.this.projectFromFullNamespaceToCollectionName(result), null);
                    } else {
                        callback.onResult(null, t);
                    }
                }
            });
        }

        @Override // com.mongodb.async.AsyncBatchCursor
        public void tryNext(final SingleResultCallback<List<T>> callback) {
            this.delegate.tryNext(new SingleResultCallback<List<BsonDocument>>() { // from class: com.mongodb.operation.ListCollectionsOperation.ProjectingAsyncBatchCursor.2
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(List<BsonDocument> result, Throwable t) {
                    if (t == null) {
                        callback.onResult(ListCollectionsOperation.this.projectFromFullNamespaceToCollectionName(result), null);
                    } else {
                        callback.onResult(null, t);
                    }
                }
            });
        }

        @Override // com.mongodb.async.AsyncBatchCursor
        public void setBatchSize(int batchSize) {
            this.delegate.setBatchSize(batchSize);
        }

        @Override // com.mongodb.async.AsyncBatchCursor
        public int getBatchSize() {
            return this.delegate.getBatchSize();
        }

        @Override // com.mongodb.async.AsyncBatchCursor
        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        @Override // com.mongodb.async.AsyncBatchCursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<T> projectFromFullNamespaceToCollectionName(List<BsonDocument> unstripped) {
        if (unstripped == null) {
            return null;
        }
        List<T> stripped = new ArrayList<>(unstripped.size());
        String prefix = this.databaseName + ".";
        for (BsonDocument cur : unstripped) {
            String name = cur.getString("name").getValue();
            String collectionName = name.substring(prefix.length());
            cur.put("name", (BsonValue) new BsonString(collectionName));
            stripped.add(this.decoder.decode(new BsonDocumentReader(cur), DecoderContext.builder().build()));
        }
        return stripped;
    }
}
