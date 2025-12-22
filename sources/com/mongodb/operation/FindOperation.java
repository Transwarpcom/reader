package com.mongodb.operation;

import com.mongodb.CursorType;
import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoNamespace;
import com.mongodb.MongoQueryException;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.AsyncSingleConnectionReadBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.binding.SingleConnectionReadBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.connection.ServerType;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindOperation.class */
public class FindOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private static final String FIRST_BATCH = "firstBatch";
    private final MongoNamespace namespace;
    private final Decoder<T> decoder;
    private BsonDocument filter;
    private int batchSize;
    private int limit;
    private BsonDocument modifiers;
    private BsonDocument projection;
    private long maxTimeMS;
    private long maxAwaitTimeMS;
    private int skip;
    private BsonDocument sort;
    private CursorType cursorType = CursorType.NonTailable;
    private boolean slaveOk;
    private boolean oplogReplay;
    private boolean noCursorTimeout;
    private boolean partial;
    private Collation collation;
    private String comment;
    private BsonDocument hint;
    private BsonDocument max;
    private BsonDocument min;
    private long maxScan;
    private boolean returnKey;
    private boolean showRecordId;
    private boolean snapshot;
    private static final Map<String, String> META_OPERATOR_TO_COMMAND_FIELD_MAP = new HashMap();

    public FindOperation(MongoNamespace namespace, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    public Decoder<T> getDecoder() {
        return this.decoder;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public FindOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public FindOperation<T> batchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public int getLimit() {
        return this.limit;
    }

    public FindOperation<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public BsonDocument getModifiers() {
        return this.modifiers;
    }

    @Deprecated
    public FindOperation<T> modifiers(BsonDocument modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public BsonDocument getProjection() {
        return this.projection;
    }

    public FindOperation<T> projection(BsonDocument projection) {
        this.projection = projection;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public FindOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        Assertions.isTrueArgument("maxTime >= 0", maxTime >= 0);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public long getMaxAwaitTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxAwaitTimeMS, TimeUnit.MILLISECONDS);
    }

    public FindOperation<T> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        Assertions.isTrueArgument("maxAwaitTime >= 0", maxAwaitTime >= 0);
        this.maxAwaitTimeMS = TimeUnit.MILLISECONDS.convert(maxAwaitTime, timeUnit);
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public FindOperation<T> skip(int skip) {
        this.skip = skip;
        return this;
    }

    public BsonDocument getSort() {
        return this.sort;
    }

    public FindOperation<T> sort(BsonDocument sort) {
        this.sort = sort;
        return this;
    }

    public CursorType getCursorType() {
        return this.cursorType;
    }

    public FindOperation<T> cursorType(CursorType cursorType) {
        this.cursorType = (CursorType) Assertions.notNull("cursorType", cursorType);
        return this;
    }

    public boolean isSlaveOk() {
        return this.slaveOk;
    }

    public FindOperation<T> slaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
        return this;
    }

    public boolean isOplogReplay() {
        return this.oplogReplay;
    }

    public FindOperation<T> oplogReplay(boolean oplogReplay) {
        this.oplogReplay = oplogReplay;
        return this;
    }

    public boolean isNoCursorTimeout() {
        return this.noCursorTimeout;
    }

    public FindOperation<T> noCursorTimeout(boolean noCursorTimeout) {
        this.noCursorTimeout = noCursorTimeout;
        return this;
    }

    public boolean isPartial() {
        return this.partial;
    }

    public FindOperation<T> partial(boolean partial) {
        this.partial = partial;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public FindOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public FindOperation<T> comment(String comment) {
        this.comment = comment;
        return this;
    }

    public BsonDocument getHint() {
        return this.hint;
    }

    public FindOperation<T> hint(BsonDocument hint) {
        this.hint = hint;
        return this;
    }

    public BsonDocument getMax() {
        return this.max;
    }

    public FindOperation<T> max(BsonDocument max) {
        this.max = max;
        return this;
    }

    public BsonDocument getMin() {
        return this.min;
    }

    public FindOperation<T> min(BsonDocument min) {
        this.min = min;
        return this;
    }

    @Deprecated
    public long getMaxScan() {
        return this.maxScan;
    }

    @Deprecated
    public FindOperation<T> maxScan(long maxScan) {
        this.maxScan = maxScan;
        return this;
    }

    public boolean isReturnKey() {
        return this.returnKey;
    }

    public FindOperation<T> returnKey(boolean returnKey) {
        this.returnKey = returnKey;
        return this;
    }

    public boolean isShowRecordId() {
        return this.showRecordId;
    }

    public FindOperation<T> showRecordId(boolean showRecordId) {
        this.showRecordId = showRecordId;
        return this;
    }

    @Deprecated
    public boolean isSnapshot() {
        return this.snapshot;
    }

    @Deprecated
    public FindOperation<T> snapshot(boolean snapshot) {
        this.snapshot = snapshot;
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.FindOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                    try {
                        OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), FindOperation.this.collation);
                        return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, FindOperation.this.namespace.getDatabaseName(), FindOperation.this.wrapInExplainIfNecessary(FindOperation.this.getCommand(binding.getSessionContext())), CommandResultDocumentCodec.create(FindOperation.this.decoder, FindOperation.FIRST_BATCH), connection, FindOperation.this.transformer(source, connection));
                    } catch (MongoCommandException e) {
                        throw new MongoQueryException(e);
                    }
                }
                OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), FindOperation.this.collation);
                QueryResult<T> queryResult = connection.query(FindOperation.this.namespace, FindOperation.this.asDocument(connection.getDescription(), binding.getReadPreference()), FindOperation.this.projection, FindOperation.this.skip, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.isSlaveOk() || binding.getReadPreference().isSlaveOk(), FindOperation.this.isTailableCursor(), FindOperation.this.isAwaitData(), FindOperation.this.isNoCursorTimeout(), FindOperation.this.isPartial(), FindOperation.this.isOplogReplay(), FindOperation.this.decoder);
                return new QueryBatchCursor(queryResult, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.getMaxTimeForCursor(), FindOperation.this.decoder, source, connection);
            }
        });
    }

    /* renamed from: com.mongodb.operation.FindOperation$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindOperation$2.class */
    class AnonymousClass2 implements OperationHelper.AsyncCallableWithConnectionAndSource {
        final /* synthetic */ SingleResultCallback val$callback;
        final /* synthetic */ AsyncReadBinding val$binding;

        AnonymousClass2(SingleResultCallback singleResultCallback, AsyncReadBinding asyncReadBinding) {
            this.val$callback = singleResultCallback;
            this.val$binding = asyncReadBinding;
        }

        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
        public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
            SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(this.val$callback, OperationHelper.LOGGER);
            if (t != null) {
                errHandlingCallback.onResult(null, t);
            } else if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(FindOperation.exceptionTransformingCallback(errHandlingCallback), source, connection);
                OperationHelper.validateReadConcernAndCollation(source, connection, this.val$binding.getSessionContext().getReadConcern(), FindOperation.this.collation, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.FindOperation.2.1
                    @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                    public void call(AsyncConnectionSource source2, AsyncConnection connection2, Throwable t2) {
                        if (t2 != null) {
                            wrappedCallback.onResult(null, t2);
                        } else {
                            CommandOperationHelper.executeWrappedCommandProtocolAsync(AnonymousClass2.this.val$binding, FindOperation.this.namespace.getDatabaseName(), FindOperation.this.wrapInExplainIfNecessary(FindOperation.this.getCommand(AnonymousClass2.this.val$binding.getSessionContext())), CommandResultDocumentCodec.create(FindOperation.this.decoder, FindOperation.FIRST_BATCH), connection2, FindOperation.this.asyncTransformer(source2, connection2), wrappedCallback);
                        }
                    }
                });
            } else {
                final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback2 = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                OperationHelper.validateReadConcernAndCollation(source, connection, this.val$binding.getSessionContext().getReadConcern(), FindOperation.this.collation, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.FindOperation.2.2
                    @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                    public void call(final AsyncConnectionSource source2, final AsyncConnection connection2, Throwable t2) {
                        if (t2 != null) {
                            wrappedCallback2.onResult(null, t2);
                        } else {
                            connection2.queryAsync(FindOperation.this.namespace, FindOperation.this.asDocument(connection2.getDescription(), AnonymousClass2.this.val$binding.getReadPreference()), FindOperation.this.projection, FindOperation.this.skip, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.isSlaveOk() || AnonymousClass2.this.val$binding.getReadPreference().isSlaveOk(), FindOperation.this.isTailableCursor(), FindOperation.this.isAwaitData(), FindOperation.this.isNoCursorTimeout(), FindOperation.this.isPartial(), FindOperation.this.isOplogReplay(), FindOperation.this.decoder, new SingleResultCallback<QueryResult<T>>() { // from class: com.mongodb.operation.FindOperation.2.2.1
                                @Override // com.mongodb.async.SingleResultCallback
                                public void onResult(QueryResult<T> result, Throwable t3) {
                                    if (t3 != null) {
                                        wrappedCallback2.onResult(null, t3);
                                    } else {
                                        wrappedCallback2.onResult(new AsyncQueryBatchCursor(result, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.getMaxTimeForCursor(), FindOperation.this.decoder, source2, connection2), null);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(AsyncReadBinding binding, SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new AnonymousClass2(callback, binding));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> SingleResultCallback<T> exceptionTransformingCallback(final SingleResultCallback<T> callback) {
        return new SingleResultCallback<T>() { // from class: com.mongodb.operation.FindOperation.3
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(T result, Throwable t) {
                if (t != null) {
                    if (t instanceof MongoCommandException) {
                        MongoCommandException commandException = (MongoCommandException) t;
                        callback.onResult(result, new MongoQueryException(commandException.getServerAddress(), commandException.getErrorCode(), commandException.getErrorMessage()));
                        return;
                    } else {
                        callback.onResult(result, t);
                        return;
                    }
                }
                callback.onResult(result, null);
            }
        };
    }

    public ReadOperation<BsonDocument> asExplainableOperation(ExplainVerbosity explainVerbosity) {
        Assertions.notNull("explainVerbosity", explainVerbosity);
        return new ReadOperation<BsonDocument>() { // from class: com.mongodb.operation.FindOperation.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.ReadOperation
            public BsonDocument execute(final ReadBinding binding) {
                return (BsonDocument) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BsonDocument>() { // from class: com.mongodb.operation.FindOperation.4.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
                    public BsonDocument call(ConnectionSource connectionSource, Connection connection) {
                        SingleConnectionReadBinding singleConnectionReadBinding = new SingleConnectionReadBinding(binding.getReadPreference(), connectionSource.getServerDescription(), connection);
                        try {
                            if (!ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                                BatchCursor<T> batchCursorExecute = FindOperation.this.createExplainableQueryOperation().execute((ReadBinding) singleConnectionReadBinding);
                                try {
                                    BsonDocument bsonDocument = (BsonDocument) batchCursorExecute.next().iterator().next();
                                    batchCursorExecute.close();
                                    singleConnectionReadBinding.release();
                                    return bsonDocument;
                                } catch (Throwable th) {
                                    batchCursorExecute.close();
                                    throw th;
                                }
                            }
                            try {
                                BsonDocument bsonDocument2 = (BsonDocument) new CommandReadOperation(FindOperation.this.getNamespace().getDatabaseName(), new BsonDocument("explain", FindOperation.this.getCommand(binding.getSessionContext())), new BsonDocumentCodec()).execute(singleConnectionReadBinding);
                                singleConnectionReadBinding.release();
                                return bsonDocument2;
                            } catch (MongoCommandException e) {
                                throw new MongoQueryException(e);
                            }
                        } catch (Throwable th2) {
                            singleConnectionReadBinding.release();
                            throw th2;
                        }
                        singleConnectionReadBinding.release();
                        throw th2;
                    }
                });
            }
        };
    }

    public AsyncReadOperation<BsonDocument> asExplainableOperationAsync(ExplainVerbosity explainVerbosity) {
        Assertions.notNull("explainVerbosity", explainVerbosity);
        return new AsyncReadOperation<BsonDocument>() { // from class: com.mongodb.operation.FindOperation.5
            @Override // com.mongodb.operation.AsyncReadOperation
            public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<BsonDocument> callback) {
                OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.FindOperation.5.1
                    @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                    public void call(AsyncConnectionSource connectionSource, AsyncConnection connection, Throwable t) {
                        SingleResultCallback<BsonDocument> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                        if (t != null) {
                            errHandlingCallback.onResult(null, t);
                            return;
                        }
                        AsyncReadBinding singleConnectionReadBinding = new AsyncSingleConnectionReadBinding(binding.getReadPreference(), connectionSource.getServerDescription(), connection);
                        if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                            new CommandReadOperation(FindOperation.this.namespace.getDatabaseName(), new BsonDocument("explain", FindOperation.this.getCommand(binding.getSessionContext())), new BsonDocumentCodec()).executeAsync(singleConnectionReadBinding, OperationHelper.releasingCallback(FindOperation.exceptionTransformingCallback(errHandlingCallback), singleConnectionReadBinding, connectionSource, connection));
                        } else {
                            FindOperation.this.createExplainableQueryOperation().executeAsync(singleConnectionReadBinding, OperationHelper.releasingCallback(new ExplainResultCallback(errHandlingCallback), singleConnectionReadBinding, connectionSource, connection));
                        }
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FindOperation<BsonDocument> createExplainableQueryOperation() {
        FindOperation findOperation = new FindOperation(this.namespace, new BsonDocumentCodec());
        BsonDocument bsonDocument = new BsonDocument();
        if (this.modifiers != null) {
            bsonDocument.putAll(this.modifiers);
        }
        bsonDocument.append("$explain", BsonBoolean.TRUE);
        return findOperation.filter(this.filter).projection(this.projection).sort(this.sort).skip(this.skip).limit(Math.abs(this.limit) * (-1)).modifiers(bsonDocument);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument asDocument(ConnectionDescription connectionDescription, ReadPreference readPreference) {
        BsonDocument document = new BsonDocument();
        if (this.modifiers != null) {
            document.putAll(this.modifiers);
        }
        if (this.sort != null) {
            document.put("$orderby", (BsonValue) this.sort);
        }
        if (this.maxTimeMS > 0) {
            document.put("$maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (connectionDescription.getServerType() == ServerType.SHARD_ROUTER && !readPreference.equals(ReadPreference.primary())) {
            document.put("$readPreference", (BsonValue) readPreference.toDocument());
        }
        if (this.comment != null) {
            document.put("$comment", (BsonValue) new BsonString(this.comment));
        }
        if (this.hint != null) {
            document.put("$hint", (BsonValue) this.hint);
        }
        if (this.max != null) {
            document.put("$max", (BsonValue) this.max);
        }
        if (this.min != null) {
            document.put("$min", (BsonValue) this.min);
        }
        if (this.maxScan > 0) {
            document.put("$maxScan", (BsonValue) new BsonInt64(this.maxScan));
        }
        if (this.returnKey) {
            document.put("$returnKey", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.showRecordId) {
            document.put("$showDiskLoc", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.snapshot) {
            document.put("$snapshot", (BsonValue) BsonBoolean.TRUE);
        }
        if (document.isEmpty()) {
            document = this.filter != null ? this.filter : new BsonDocument();
        } else if (this.filter != null) {
            document.put("$query", (BsonValue) this.filter);
        } else if (!document.containsKey("$query")) {
            document.put("$query", (BsonValue) new BsonDocument());
        }
        return document;
    }

    static {
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$query", "filter");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$orderby", "sort");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$hint", "hint");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$comment", "comment");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$maxScan", "maxScan");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$maxTimeMS", "maxTimeMS");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$max", "max");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$min", "min");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$returnKey", "returnKey");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$showDiskLoc", "showRecordId");
        META_OPERATOR_TO_COMMAND_FIELD_MAP.put("$snapshot", "snapshot");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(SessionContext sessionContext) {
        BsonDocument commandDocument = new BsonDocument("find", new BsonString(this.namespace.getCollectionName()));
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, commandDocument);
        if (this.modifiers != null) {
            for (Map.Entry<String, BsonValue> cur : this.modifiers.entrySet()) {
                String commandFieldName = META_OPERATOR_TO_COMMAND_FIELD_MAP.get(cur.getKey());
                if (commandFieldName != null) {
                    commandDocument.append(commandFieldName, cur.getValue());
                }
            }
        }
        DocumentHelper.putIfNotNullOrEmpty(commandDocument, "filter", this.filter);
        DocumentHelper.putIfNotNullOrEmpty(commandDocument, "sort", this.sort);
        DocumentHelper.putIfNotNullOrEmpty(commandDocument, "projection", this.projection);
        if (this.skip > 0) {
            commandDocument.put("skip", (BsonValue) new BsonInt32(this.skip));
        }
        if (this.limit != 0) {
            commandDocument.put("limit", (BsonValue) new BsonInt32(Math.abs(this.limit)));
        }
        if (this.limit >= 0) {
            if (this.batchSize < 0 && Math.abs(this.batchSize) < this.limit) {
                commandDocument.put("limit", (BsonValue) new BsonInt32(Math.abs(this.batchSize)));
            } else if (this.batchSize != 0) {
                commandDocument.put("batchSize", (BsonValue) new BsonInt32(Math.abs(this.batchSize)));
            }
        }
        if (this.limit < 0 || this.batchSize < 0) {
            commandDocument.put("singleBatch", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.maxTimeMS > 0) {
            commandDocument.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (isTailableCursor()) {
            commandDocument.put("tailable", (BsonValue) BsonBoolean.TRUE);
        }
        if (isAwaitData()) {
            commandDocument.put("awaitData", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.oplogReplay) {
            commandDocument.put("oplogReplay", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.noCursorTimeout) {
            commandDocument.put("noCursorTimeout", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.partial) {
            commandDocument.put("allowPartialResults", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.collation != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        if (this.comment != null) {
            commandDocument.put("comment", (BsonValue) new BsonString(this.comment));
        }
        if (this.hint != null) {
            commandDocument.put("hint", (BsonValue) this.hint);
        }
        if (this.max != null) {
            commandDocument.put("max", (BsonValue) this.max);
        }
        if (this.min != null) {
            commandDocument.put("min", (BsonValue) this.min);
        }
        if (this.maxScan > 0) {
            commandDocument.put("maxScan", (BsonValue) new BsonInt64(this.maxScan));
        }
        if (this.returnKey) {
            commandDocument.put("returnKey", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.showRecordId) {
            commandDocument.put("showRecordId", (BsonValue) BsonBoolean.TRUE);
        }
        if (this.snapshot) {
            commandDocument.put("snapshot", (BsonValue) BsonBoolean.TRUE);
        }
        return commandDocument;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument wrapInExplainIfNecessary(BsonDocument commandDocument) {
        if (isExplain()) {
            return new BsonDocument("explain", commandDocument);
        }
        return commandDocument;
    }

    private boolean isExplain() {
        return this.modifiers != null && this.modifiers.get("$explain", BsonBoolean.FALSE).equals(BsonBoolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTailableCursor() {
        return this.cursorType.isTailable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAwaitData() {
        return this.cursorType == CursorType.TailableAwait;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.FindOperation.6
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = FindOperation.this.documentToQueryResult(result, serverAddress);
                return new QueryBatchCursor(queryResult, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.getMaxTimeForCursor(), FindOperation.this.decoder, source, connection);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getMaxTimeForCursor() {
        if (this.cursorType == CursorType.TailableAwait) {
            return this.maxAwaitTimeMS;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.FindOperation.7
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = FindOperation.this.documentToQueryResult(result, serverAddress);
                return new AsyncQueryBatchCursor(queryResult, FindOperation.this.limit, FindOperation.this.batchSize, FindOperation.this.getMaxTimeForCursor(), FindOperation.this.decoder, source, connection);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> documentToQueryResult(BsonDocument result, ServerAddress serverAddress) {
        QueryResult<T> queryResult;
        if (isExplain()) {
            T decodedDocument = this.decoder.decode(new BsonDocumentReader(result), DecoderContext.builder().build());
            queryResult = new QueryResult<>(getNamespace(), Collections.singletonList(decodedDocument), 0L, serverAddress);
        } else {
            queryResult = OperationHelper.cursorDocumentToQueryResult(result.getDocument("cursor"), serverAddress);
        }
        return queryResult;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindOperation$ExplainResultCallback.class */
    private static class ExplainResultCallback implements SingleResultCallback<AsyncBatchCursor<BsonDocument>> {
        private final SingleResultCallback<BsonDocument> callback;

        ExplainResultCallback(SingleResultCallback<BsonDocument> callback) {
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(final AsyncBatchCursor<BsonDocument> cursor, Throwable t) {
            if (t != null) {
                this.callback.onResult(null, t);
            } else {
                cursor.next(new SingleResultCallback<List<BsonDocument>>() { // from class: com.mongodb.operation.FindOperation.ExplainResultCallback.1
                    @Override // com.mongodb.async.SingleResultCallback
                    public void onResult(List<BsonDocument> result, Throwable t2) {
                        cursor.close();
                        if (t2 != null) {
                            ExplainResultCallback.this.callback.onResult(null, t2);
                        } else if (result == null || result.size() == 0) {
                            ExplainResultCallback.this.callback.onResult(null, new MongoInternalException("Expected explain result"));
                        } else {
                            ExplainResultCallback.this.callback.onResult(result.get(0), null);
                        }
                    }
                });
            }
        }
    }
}
