package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.CommandListener;
import com.mongodb.internal.connection.RequestMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/QueryProtocol.class */
class QueryProtocol<T> implements LegacyProtocol<QueryResult<T>> {
    private static final String FIND_COMMAND_NAME = "find";
    private static final String EXPLAIN_COMMAND_NAME = "explain";
    private final int skip;
    private final int limit;
    private final int batchSize;
    private final int numberToReturn;
    private final boolean withLimitAndBatchSize;
    private final BsonDocument queryDocument;
    private final BsonDocument fields;
    private final Decoder<T> resultDecoder;
    private final MongoNamespace namespace;
    private boolean tailableCursor;
    private boolean slaveOk;
    private boolean oplogReplay;
    private boolean noCursorTimeout;
    private boolean awaitData;
    private boolean partial;
    private CommandListener commandListener;
    public static final Logger LOGGER = Loggers.getLogger("protocol.query");
    private static final Map<String, String> META_OPERATOR_TO_COMMAND_FIELD_MAP = new HashMap();

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

    QueryProtocol(MongoNamespace namespace, int skip, int numberToReturn, BsonDocument queryDocument, BsonDocument fields, Decoder<T> resultDecoder) {
        this.namespace = namespace;
        this.skip = skip;
        this.withLimitAndBatchSize = false;
        this.numberToReturn = numberToReturn;
        this.limit = 0;
        this.batchSize = 0;
        this.queryDocument = queryDocument;
        this.fields = fields;
        this.resultDecoder = resultDecoder;
    }

    QueryProtocol(MongoNamespace namespace, int skip, int limit, int batchSize, BsonDocument queryDocument, BsonDocument fields, Decoder<T> resultDecoder) {
        this.namespace = namespace;
        this.skip = skip;
        this.withLimitAndBatchSize = true;
        this.numberToReturn = 0;
        this.limit = limit;
        this.batchSize = batchSize;
        this.queryDocument = queryDocument;
        this.fields = fields;
        this.resultDecoder = resultDecoder;
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    public CommandListener getCommandListener() {
        return this.commandListener;
    }

    public boolean isTailableCursor() {
        return this.tailableCursor;
    }

    public QueryProtocol<T> tailableCursor(boolean tailableCursor) {
        this.tailableCursor = tailableCursor;
        return this;
    }

    public boolean isSlaveOk() {
        return this.slaveOk;
    }

    public QueryProtocol<T> slaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
        return this;
    }

    public boolean isOplogReplay() {
        return this.oplogReplay;
    }

    public QueryProtocol<T> oplogReplay(boolean oplogReplay) {
        this.oplogReplay = oplogReplay;
        return this;
    }

    public boolean isNoCursorTimeout() {
        return this.noCursorTimeout;
    }

    public QueryProtocol<T> noCursorTimeout(boolean noCursorTimeout) {
        this.noCursorTimeout = noCursorTimeout;
        return this;
    }

    public boolean isAwaitData() {
        return this.awaitData;
    }

    public QueryProtocol<T> awaitData(boolean awaitData) {
        this.awaitData = awaitData;
        return this;
    }

    public boolean isPartial() {
        return this.partial;
    }

    public QueryProtocol<T> partial(boolean partial) {
        this.partial = partial;
        return this;
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public QueryResult<T> execute(InternalConnection internalConnection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Sending query of namespace %s on connection [%s] to server %s", this.namespace, internalConnection.getDescription().getConnectionId(), internalConnection.getDescription().getServerAddress()));
        }
        long jNanoTime = System.nanoTime();
        QueryMessage queryMessageCreateQueryMessage = null;
        try {
            ByteBufferBsonOutput byteBufferBsonOutput = new ByteBufferBsonOutput(internalConnection);
            try {
                queryMessageCreateQueryMessage = createQueryMessage(internalConnection.getDescription());
                queryMessageCreateQueryMessage.encode(byteBufferBsonOutput, NoOpSessionContext.INSTANCE);
                boolean zSendQueryStartedEvent = sendQueryStartedEvent(internalConnection, queryMessageCreateQueryMessage, byteBufferBsonOutput, queryMessageCreateQueryMessage.getEncodingMetadata());
                internalConnection.sendMessage(byteBufferBsonOutput.getByteBuffers(), queryMessageCreateQueryMessage.getId());
                byteBufferBsonOutput.close();
                ResponseBuffers responseBuffersReceiveMessage = internalConnection.receiveMessage(queryMessageCreateQueryMessage.getId());
                try {
                    if (responseBuffersReceiveMessage.getReplyHeader().isQueryFailure()) {
                        throw ProtocolHelper.getQueryFailureException((BsonDocument) new ReplyMessage(responseBuffersReceiveMessage, new BsonDocumentCodec(), queryMessageCreateQueryMessage.getId()).getDocuments().get(0), internalConnection.getDescription().getServerAddress());
                    }
                    ReplyMessage replyMessage = new ReplyMessage(responseBuffersReceiveMessage, this.resultDecoder, queryMessageCreateQueryMessage.getId());
                    QueryResult<T> queryResult = new QueryResult<>(this.namespace, replyMessage.getDocuments(), replyMessage.getReplyHeader().getCursorId(), internalConnection.getDescription().getServerAddress());
                    sendQuerySucceededEvent(internalConnection.getDescription(), jNanoTime, queryMessageCreateQueryMessage, zSendQueryStartedEvent, responseBuffersReceiveMessage, queryResult);
                    LOGGER.debug("Query completed");
                    responseBuffersReceiveMessage.close();
                    return queryResult;
                } catch (Throwable th) {
                    responseBuffersReceiveMessage.close();
                    throw th;
                }
            } catch (Throwable th2) {
                byteBufferBsonOutput.close();
                throw th2;
            }
        } catch (RuntimeException e) {
            if (this.commandListener != null) {
                ProtocolHelper.sendCommandFailedEvent(queryMessageCreateQueryMessage, FIND_COMMAND_NAME, internalConnection.getDescription(), System.nanoTime() - jNanoTime, e, this.commandListener);
            }
            throw e;
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, SingleResultCallback<QueryResult<T>> callback) {
        long startTimeNanos = System.nanoTime();
        QueryMessage message = createQueryMessage(connection.getDescription());
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously sending query of namespace %s on connection [%s] to server %s", this.namespace, connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
            RequestMessage.EncodingMetadata metadata = ProtocolHelper.encodeMessageWithMetadata(message, bsonOutput);
            boolean isExplainEvent = sendQueryStartedEvent(connection, message, bsonOutput, metadata);
            SingleResultCallback<ResponseBuffers> receiveCallback = new QueryResultCallback(callback, message.getId(), startTimeNanos, message, isExplainEvent, connection.getDescription());
            connection.sendMessageAsync(bsonOutput.getByteBuffers(), message.getId(), new SendMessageCallback(connection, bsonOutput, message, getCommandName(isExplainEvent), startTimeNanos, this.commandListener, callback, receiveCallback));
        } catch (Throwable t) {
            if (this.commandListener != null) {
                ProtocolHelper.sendCommandFailedEvent(message, FIND_COMMAND_NAME, connection.getDescription(), System.nanoTime() - startTimeNanos, t, this.commandListener);
            }
            callback.onResult(null, t);
        }
    }

    private boolean sendQueryStartedEvent(InternalConnection connection, QueryMessage message, ByteBufferBsonOutput bsonOutput, RequestMessage.EncodingMetadata metadata) {
        boolean isExplainEvent = false;
        if (this.commandListener != null) {
            BsonDocument command = asFindCommandDocument(bsonOutput, metadata.getFirstDocumentPosition());
            isExplainEvent = command.keySet().iterator().next().equals(EXPLAIN_COMMAND_NAME);
            ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), getCommandName(isExplainEvent), command, connection.getDescription(), this.commandListener);
        }
        return isExplainEvent;
    }

    private String getCommandName(boolean isExplainEvent) {
        return isExplainEvent ? EXPLAIN_COMMAND_NAME : FIND_COMMAND_NAME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendQuerySucceededEvent(ConnectionDescription connectionDescription, long startTimeNanos, QueryMessage message, boolean isExplainEvent, ResponseBuffers responseBuffers, QueryResult<T> queryResult) {
        if (this.commandListener != null) {
            BsonDocument response = asFindCommandResponseDocument(responseBuffers, queryResult, isExplainEvent);
            ProtocolHelper.sendCommandSucceededEvent(message, getCommandName(isExplainEvent), response, connectionDescription, System.nanoTime() - startTimeNanos, this.commandListener);
        }
    }

    private QueryMessage createQueryMessage(ConnectionDescription connectionDescription) {
        return (QueryMessage) new QueryMessage(this.namespace.getFullName(), this.skip, getNumberToReturn(), this.queryDocument, this.fields, ProtocolHelper.getMessageSettings(connectionDescription)).tailableCursor(isTailableCursor()).slaveOk(isSlaveOk()).oplogReplay(isOplogReplay()).noCursorTimeout(isNoCursorTimeout()).awaitData(isAwaitData()).partial(isPartial());
    }

    private int getNumberToReturn() {
        if (this.withLimitAndBatchSize) {
            if (this.limit < 0) {
                return this.limit;
            }
            if (this.limit == 0) {
                return this.batchSize;
            }
            if (this.batchSize == 0) {
                return this.limit;
            }
            if (this.limit < Math.abs(this.batchSize)) {
                return this.limit;
            }
            return this.batchSize;
        }
        return this.numberToReturn;
    }

    private BsonDocument asFindCommandDocument(ByteBufferBsonOutput bsonOutput, int firstDocumentPosition) {
        BsonDocument command = new BsonDocument(FIND_COMMAND_NAME, new BsonString(this.namespace.getCollectionName()));
        boolean isExplain = false;
        List<ByteBufBsonDocument> documents = ByteBufBsonDocument.createList(bsonOutput, firstDocumentPosition);
        ByteBufBsonDocument rawQueryDocument = documents.get(0);
        for (Map.Entry<String, BsonValue> cur : rawQueryDocument.entrySet()) {
            String commandFieldName = META_OPERATOR_TO_COMMAND_FIELD_MAP.get(cur.getKey());
            if (commandFieldName != null) {
                command.append(commandFieldName, cur.getValue());
            } else if (cur.getKey().equals("$explain")) {
                isExplain = true;
            }
        }
        if (command.size() == 1) {
            command.append("filter", rawQueryDocument);
        }
        if (documents.size() == 2) {
            command.append("projection", documents.get(1));
        }
        if (this.skip != 0) {
            command.append("skip", new BsonInt32(this.skip));
        }
        if (this.withLimitAndBatchSize) {
            if (this.limit != 0) {
                command.append("limit", new BsonInt32(this.limit));
            }
            if (this.batchSize != 0) {
                command.append("batchSize", new BsonInt32(this.batchSize));
            }
        }
        if (this.tailableCursor) {
            command.append("tailable", BsonBoolean.valueOf(this.tailableCursor));
        }
        if (this.noCursorTimeout) {
            command.append("noCursorTimeout", BsonBoolean.valueOf(this.noCursorTimeout));
        }
        if (this.oplogReplay) {
            command.append("oplogReplay", BsonBoolean.valueOf(this.oplogReplay));
        }
        if (this.awaitData) {
            command.append("awaitData", BsonBoolean.valueOf(this.awaitData));
        }
        if (this.partial) {
            command.append("allowPartialResults", BsonBoolean.valueOf(this.partial));
        }
        if (isExplain) {
            command = new BsonDocument(EXPLAIN_COMMAND_NAME, command);
        }
        return command;
    }

    private BsonDocument asFindCommandResponseDocument(ResponseBuffers responseBuffers, QueryResult<T> queryResult, boolean isExplain) {
        List<ByteBufBsonDocument> rawResultDocuments = Collections.emptyList();
        if (responseBuffers.getReplyHeader().getNumberReturned() > 0) {
            responseBuffers.reset();
            rawResultDocuments = ByteBufBsonDocument.createList(responseBuffers);
        }
        if (isExplain) {
            BsonDocument explainCommandResponseDocument = new BsonDocument("ok", new BsonDouble(1.0d));
            explainCommandResponseDocument.putAll(rawResultDocuments.get(0));
            return explainCommandResponseDocument;
        }
        BsonDocument cursorDocument = new BsonDocument("id", queryResult.getCursor() == null ? new BsonInt64(0L) : new BsonInt64(queryResult.getCursor().getId())).append("ns", new BsonString(this.namespace.getFullName())).append("firstBatch", new BsonArray(rawResultDocuments));
        return new BsonDocument("cursor", cursorDocument).append("ok", new BsonDouble(1.0d));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/QueryProtocol$QueryResultCallback.class */
    class QueryResultCallback extends ResponseCallback {
        private final SingleResultCallback<QueryResult<T>> callback;
        private final ConnectionDescription connectionDescription;
        private final long startTimeNanos;
        private final QueryMessage message;
        private final boolean isExplainEvent;

        QueryResultCallback(SingleResultCallback<QueryResult<T>> callback, int requestId, long startTimeNanos, QueryMessage message, boolean isExplainEvent, ConnectionDescription connectionDescription) {
            super(requestId, connectionDescription.getServerAddress());
            this.callback = callback;
            this.startTimeNanos = startTimeNanos;
            this.message = message;
            this.isExplainEvent = isExplainEvent;
            this.connectionDescription = connectionDescription;
        }

        @Override // com.mongodb.internal.connection.ResponseCallback
        protected void callCallback(ResponseBuffers responseBuffers, Throwable th) {
            try {
                try {
                    if (th != null) {
                        throw th;
                    }
                    if (responseBuffers.getReplyHeader().isQueryFailure()) {
                        throw ProtocolHelper.getQueryFailureException((BsonDocument) new ReplyMessage(responseBuffers, new BsonDocumentCodec(), getRequestId()).getDocuments().get(0), getServerAddress());
                    }
                    ReplyMessage replyMessage = new ReplyMessage(responseBuffers, QueryProtocol.this.resultDecoder, getRequestId());
                    QueryResult<T> queryResult = new QueryResult<>(QueryProtocol.this.namespace, replyMessage.getDocuments(), replyMessage.getReplyHeader().getCursorId(), getServerAddress());
                    QueryProtocol.this.sendQuerySucceededEvent(this.connectionDescription, this.startTimeNanos, this.message, this.isExplainEvent, responseBuffers, queryResult);
                    if (QueryProtocol.LOGGER.isDebugEnabled()) {
                        QueryProtocol.LOGGER.debug(String.format("Query results received %s documents with cursor %s", Integer.valueOf(queryResult.getResults().size()), queryResult.getCursor()));
                    }
                    this.callback.onResult(queryResult, null);
                    if (responseBuffers != null) {
                        try {
                            responseBuffers.close();
                        } catch (Throwable th2) {
                            QueryProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th2);
                        }
                    }
                } catch (Throwable th3) {
                    if (QueryProtocol.this.commandListener != null) {
                        ProtocolHelper.sendCommandFailedEvent(this.message, QueryProtocol.FIND_COMMAND_NAME, this.connectionDescription, System.nanoTime() - this.startTimeNanos, th3, QueryProtocol.this.commandListener);
                    }
                    this.callback.onResult(null, th3);
                    if (responseBuffers != null) {
                        try {
                            responseBuffers.close();
                        } catch (Throwable th4) {
                            QueryProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th4);
                        }
                    }
                }
            } catch (Throwable th5) {
                if (responseBuffers != null) {
                    try {
                        responseBuffers.close();
                    } catch (Throwable th6) {
                        QueryProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th6);
                        throw th5;
                    }
                }
                throw th5;
            }
        }
    }
}
