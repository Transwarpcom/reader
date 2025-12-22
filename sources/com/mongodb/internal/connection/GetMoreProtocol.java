package com.mongodb.internal.connection;

import com.mongodb.MongoCursorNotFoundException;
import com.mongodb.MongoNamespace;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.CommandListener;
import java.util.Collections;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/GetMoreProtocol.class */
class GetMoreProtocol<T> implements LegacyProtocol<QueryResult<T>> {
    public static final Logger LOGGER = Loggers.getLogger("protocol.getmore");
    private static final String COMMAND_NAME = "getMore";
    private final Decoder<T> resultDecoder;
    private final MongoNamespace namespace;
    private final long cursorId;
    private final int numberToReturn;
    private CommandListener commandListener;

    GetMoreProtocol(MongoNamespace namespace, long cursorId, int numberToReturn, Decoder<T> resultDecoder) {
        this.namespace = namespace;
        this.cursorId = cursorId;
        this.numberToReturn = numberToReturn;
        this.resultDecoder = resultDecoder;
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.mongodb.internal.connection.LegacyProtocol
    public QueryResult<T> execute(InternalConnection internalConnection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Getting more documents from namespace %s with cursor %d on connection [%s] to server %s", this.namespace, Long.valueOf(this.cursorId), internalConnection.getDescription().getConnectionId(), internalConnection.getDescription().getServerAddress()));
        }
        long jNanoTime = System.nanoTime();
        GetMoreMessage getMoreMessage = new GetMoreMessage(this.namespace.getFullName(), this.cursorId, this.numberToReturn);
        try {
            sendMessage(getMoreMessage, internalConnection);
            ResponseBuffers responseBuffersReceiveMessage = internalConnection.receiveMessage(getMoreMessage.getId());
            try {
                if (responseBuffersReceiveMessage.getReplyHeader().isCursorNotFound()) {
                    throw new MongoCursorNotFoundException(getMoreMessage.getCursorId(), internalConnection.getDescription().getServerAddress());
                }
                if (responseBuffersReceiveMessage.getReplyHeader().isQueryFailure()) {
                    throw ProtocolHelper.getQueryFailureException((BsonDocument) new ReplyMessage(responseBuffersReceiveMessage, new BsonDocumentCodec(), getMoreMessage.getId()).getDocuments().get(0), internalConnection.getDescription().getServerAddress());
                }
                ReplyMessage replyMessage = new ReplyMessage(responseBuffersReceiveMessage, this.resultDecoder, getMoreMessage.getId());
                QueryResult<T> queryResult = new QueryResult<>(this.namespace, replyMessage.getDocuments(), replyMessage.getReplyHeader().getCursorId(), internalConnection.getDescription().getServerAddress());
                if (this.commandListener != null) {
                    ProtocolHelper.sendCommandSucceededEvent(getMoreMessage, COMMAND_NAME, asGetMoreCommandResponseDocument(queryResult, responseBuffersReceiveMessage), internalConnection.getDescription(), System.nanoTime() - jNanoTime, this.commandListener);
                }
                responseBuffersReceiveMessage.close();
                LOGGER.debug("Get-more completed");
                return queryResult;
            } catch (Throwable th) {
                responseBuffersReceiveMessage.close();
                throw th;
            }
        } catch (RuntimeException e) {
            if (this.commandListener != null) {
                ProtocolHelper.sendCommandFailedEvent(getMoreMessage, COMMAND_NAME, internalConnection.getDescription(), System.nanoTime() - jNanoTime, e, this.commandListener);
            }
            throw e;
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, SingleResultCallback<QueryResult<T>> callback) {
        long startTimeNanos = System.nanoTime();
        GetMoreMessage message = new GetMoreMessage(this.namespace.getFullName(), this.cursorId, this.numberToReturn);
        boolean sentStartedEvent = false;
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously getting more documents from namespace %s with cursor %d on connection [%s] to server %s", this.namespace, Long.valueOf(this.cursorId), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
            if (this.commandListener != null) {
                ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), COMMAND_NAME, asGetMoreCommandDocument(), connection.getDescription(), this.commandListener);
                sentStartedEvent = true;
            }
            ProtocolHelper.encodeMessage(message, bsonOutput);
            SingleResultCallback<ResponseBuffers> receiveCallback = new GetMoreResultCallback(callback, this.cursorId, message, connection.getDescription(), this.commandListener, startTimeNanos);
            connection.sendMessageAsync(bsonOutput.getByteBuffers(), message.getId(), new SendMessageCallback(connection, bsonOutput, message, COMMAND_NAME, startTimeNanos, this.commandListener, callback, receiveCallback));
        } catch (Throwable t) {
            if (sentStartedEvent) {
                ProtocolHelper.sendCommandFailedEvent(message, COMMAND_NAME, connection.getDescription(), System.nanoTime() - startTimeNanos, t, this.commandListener);
            }
            callback.onResult(null, t);
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    private void sendMessage(GetMoreMessage message, InternalConnection connection) {
        ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
        try {
            if (this.commandListener != null) {
                ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), COMMAND_NAME, asGetMoreCommandDocument(), connection.getDescription(), this.commandListener);
            }
            message.encode(bsonOutput, NoOpSessionContext.INSTANCE);
            connection.sendMessage(bsonOutput.getByteBuffers(), message.getId());
            bsonOutput.close();
        } catch (Throwable th) {
            bsonOutput.close();
            throw th;
        }
    }

    private BsonDocument asGetMoreCommandDocument() {
        return new BsonDocument(COMMAND_NAME, new BsonInt64(this.cursorId)).append("collection", new BsonString(this.namespace.getCollectionName())).append("batchSize", new BsonInt32(this.numberToReturn));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument asGetMoreCommandResponseDocument(QueryResult<T> queryResult, ResponseBuffers responseBuffers) {
        List<ByteBufBsonDocument> rawResultDocuments = Collections.emptyList();
        if (responseBuffers.getReplyHeader().getNumberReturned() != 0) {
            responseBuffers.reset();
            rawResultDocuments = ByteBufBsonDocument.createList(responseBuffers);
        }
        BsonDocument cursorDocument = new BsonDocument("id", queryResult.getCursor() == null ? new BsonInt64(0L) : new BsonInt64(queryResult.getCursor().getId())).append("ns", new BsonString(this.namespace.getFullName())).append("nextBatch", new BsonArray(rawResultDocuments));
        return new BsonDocument("cursor", cursorDocument).append("ok", new BsonDouble(1.0d));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/GetMoreProtocol$GetMoreResultCallback.class */
    class GetMoreResultCallback extends ResponseCallback {
        private final SingleResultCallback<QueryResult<T>> callback;
        private final long cursorId;
        private final GetMoreMessage message;
        private final ConnectionDescription connectionDescription;
        private final CommandListener commandListener;
        private final long startTimeNanos;

        GetMoreResultCallback(SingleResultCallback<QueryResult<T>> callback, long cursorId, GetMoreMessage message, ConnectionDescription connectionDescription, CommandListener commandListener, long startTimeNanos) {
            super(message.getId(), connectionDescription.getServerAddress());
            this.callback = callback;
            this.cursorId = cursorId;
            this.message = message;
            this.connectionDescription = connectionDescription;
            this.commandListener = commandListener;
            this.startTimeNanos = startTimeNanos;
        }

        @Override // com.mongodb.internal.connection.ResponseCallback
        protected void callCallback(ResponseBuffers responseBuffers, Throwable th) {
            try {
                try {
                    if (th != null) {
                        throw th;
                    }
                    if (responseBuffers.getReplyHeader().isCursorNotFound()) {
                        throw new MongoCursorNotFoundException(this.cursorId, getServerAddress());
                    }
                    if (responseBuffers.getReplyHeader().isQueryFailure()) {
                        throw ProtocolHelper.getQueryFailureException((BsonDocument) new ReplyMessage(responseBuffers, new BsonDocumentCodec(), this.message.getId()).getDocuments().get(0), this.connectionDescription.getServerAddress());
                    }
                    ReplyMessage replyMessage = new ReplyMessage(responseBuffers, GetMoreProtocol.this.resultDecoder, getRequestId());
                    QueryResult<T> queryResult = new QueryResult<>(GetMoreProtocol.this.namespace, replyMessage.getDocuments(), replyMessage.getReplyHeader().getCursorId(), getServerAddress());
                    if (this.commandListener != null) {
                        ProtocolHelper.sendCommandSucceededEvent(this.message, GetMoreProtocol.COMMAND_NAME, GetMoreProtocol.this.asGetMoreCommandResponseDocument(queryResult, responseBuffers), this.connectionDescription, System.nanoTime() - this.startTimeNanos, this.commandListener);
                    }
                    if (GetMoreProtocol.LOGGER.isDebugEnabled()) {
                        GetMoreProtocol.LOGGER.debug(String.format("GetMore results received %s documents with cursor %s", Integer.valueOf(queryResult.getResults().size()), queryResult.getCursor()));
                    }
                    this.callback.onResult(queryResult, null);
                    if (responseBuffers != null) {
                        try {
                            responseBuffers.close();
                        } catch (Throwable th2) {
                            GetMoreProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th2);
                        }
                    }
                } catch (Throwable th3) {
                    if (this.commandListener != null) {
                        ProtocolHelper.sendCommandFailedEvent(this.message, GetMoreProtocol.COMMAND_NAME, this.connectionDescription, System.nanoTime() - this.startTimeNanos, th3, this.commandListener);
                    }
                    this.callback.onResult(null, th3);
                    if (responseBuffers != null) {
                        try {
                            responseBuffers.close();
                        } catch (Throwable th4) {
                            GetMoreProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th4);
                        }
                    }
                }
            } catch (Throwable th5) {
                if (responseBuffers != null) {
                    try {
                        responseBuffers.close();
                    } catch (Throwable th6) {
                        GetMoreProtocol.LOGGER.debug("GetMore ResponseBuffer close exception", th6);
                        throw th5;
                    }
                }
                throw th5;
            }
        }
    }
}
