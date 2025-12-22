package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.QueryResult;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ParallelCollectionScanOperation.class */
public class ParallelCollectionScanOperation<T> implements AsyncReadOperation<List<AsyncBatchCursor<T>>>, ReadOperation<List<BatchCursor<T>>> {
    private final MongoNamespace namespace;
    private final int numCursors;
    private int batchSize = 0;
    private final Decoder<T> decoder;

    public ParallelCollectionScanOperation(MongoNamespace namespace, int numCursors, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        Assertions.isTrue("numCursors >= 1", numCursors >= 1);
        this.numCursors = numCursors;
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public int getNumCursors() {
        return this.numCursors;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public ParallelCollectionScanOperation<T> batchSize(int batchSize) {
        Assertions.isTrue("batchSize >= 0", batchSize >= 0);
        this.batchSize = batchSize;
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public List<BatchCursor<T>> execute(final ReadBinding binding) {
        return (List) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<List<BatchCursor<T>>>() { // from class: com.mongodb.operation.ParallelCollectionScanOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public List<BatchCursor<T>> call(ConnectionSource source, Connection connection) {
                OperationHelper.validateReadConcern(connection, binding.getSessionContext().getReadConcern());
                return (List) CommandOperationHelper.executeWrappedCommandProtocol(binding, ParallelCollectionScanOperation.this.namespace.getDatabaseName(), ParallelCollectionScanOperation.this.getCommand(binding.getSessionContext()), CommandResultDocumentCodec.create(ParallelCollectionScanOperation.this.decoder, "firstBatch"), connection, ParallelCollectionScanOperation.this.transformer(source));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<List<AsyncBatchCursor<T>>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.ParallelCollectionScanOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                SingleResultCallback<List<AsyncBatchCursor<T>>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<List<AsyncBatchCursor<T>>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                    OperationHelper.validateReadConcern(source, connection, binding.getSessionContext().getReadConcern(), new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.ParallelCollectionScanOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                        public void call(AsyncConnectionSource source2, AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, ParallelCollectionScanOperation.this.namespace.getDatabaseName(), ParallelCollectionScanOperation.this.getCommand(binding.getSessionContext()), CommandResultDocumentCodec.create(ParallelCollectionScanOperation.this.decoder, "firstBatch"), connection2, ParallelCollectionScanOperation.this.asyncTransformer(source2, connection2), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, List<BatchCursor<T>>> transformer(final ConnectionSource source) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, List<BatchCursor<T>>>() { // from class: com.mongodb.operation.ParallelCollectionScanOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public List<BatchCursor<T>> apply(BsonDocument result, ServerAddress serverAddress) {
                List<BatchCursor<T>> cursors = new ArrayList<>();
                Iterator<BsonValue> it = ParallelCollectionScanOperation.this.getCursorDocuments(result).iterator();
                while (it.hasNext()) {
                    BsonValue cursorValue = it.next();
                    cursors.add(new QueryBatchCursor<>(ParallelCollectionScanOperation.this.createQueryResult(ParallelCollectionScanOperation.this.getCursorDocument(cursorValue.asDocument()), source.getServerDescription().getAddress()), 0, ParallelCollectionScanOperation.this.getBatchSize(), ParallelCollectionScanOperation.this.decoder, source));
                }
                return cursors;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, List<AsyncBatchCursor<T>>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, List<AsyncBatchCursor<T>>>() { // from class: com.mongodb.operation.ParallelCollectionScanOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public List<AsyncBatchCursor<T>> apply(BsonDocument result, ServerAddress serverAddress) {
                List<AsyncBatchCursor<T>> cursors = new ArrayList<>();
                Iterator<BsonValue> it = ParallelCollectionScanOperation.this.getCursorDocuments(result).iterator();
                while (it.hasNext()) {
                    BsonValue cursorValue = it.next();
                    cursors.add(new AsyncQueryBatchCursor<>(ParallelCollectionScanOperation.this.createQueryResult(ParallelCollectionScanOperation.this.getCursorDocument(cursorValue.asDocument()), source.getServerDescription().getAddress()), 0, ParallelCollectionScanOperation.this.getBatchSize(), 0L, ParallelCollectionScanOperation.this.decoder, source, connection));
                }
                return cursors;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonArray getCursorDocuments(BsonDocument result) {
        return result.getArray("cursors");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCursorDocument(BsonDocument cursorDocument) {
        return cursorDocument.getDocument("cursor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument cursorDocument, ServerAddress serverAddress) {
        return OperationHelper.cursorDocumentToQueryResult(cursorDocument, serverAddress);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(SessionContext sessionContext) {
        BsonDocument document = new BsonDocument("parallelCollectionScan", new BsonString(this.namespace.getCollectionName())).append("numCursors", new BsonInt32(getNumCursors()));
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, document);
        return document;
    }
}
