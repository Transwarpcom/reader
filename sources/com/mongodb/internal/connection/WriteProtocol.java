package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.WriteConcernResult;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.event.CommandListener;
import com.mongodb.internal.connection.RequestMessage;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.io.OutputBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/WriteProtocol.class */
abstract class WriteProtocol implements LegacyProtocol<WriteConcernResult> {
    private final MongoNamespace namespace;
    private final boolean ordered;
    private CommandListener commandListener;

    protected abstract BsonDocument getAsWriteCommand(ByteBufferBsonOutput byteBufferBsonOutput, int i);

    protected abstract RequestMessage createRequestMessage(MessageSettings messageSettings);

    protected abstract Logger getLogger();

    WriteProtocol(MongoNamespace namespace, boolean ordered) {
        this.namespace = namespace;
        this.ordered = ordered;
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.internal.connection.LegacyProtocol
    public WriteConcernResult execute(InternalConnection connection) {
        RequestMessage requestMessage = null;
        long startTimeNanos = System.nanoTime();
        boolean sentCommandStartedEvent = false;
        ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
        try {
            try {
                requestMessage = createRequestMessage(ProtocolHelper.getMessageSettings(connection.getDescription()));
                requestMessage.encode(bsonOutput, NoOpSessionContext.INSTANCE);
                sendStartedEvent(connection, requestMessage, requestMessage.getEncodingMetadata(), bsonOutput);
                sentCommandStartedEvent = true;
                int messageId = requestMessage.getId();
                connection.sendMessage(bsonOutput.getByteBuffers(), messageId);
                bsonOutput.close();
                sendSucceededEvent(connection, requestMessage, startTimeNanos);
                return WriteConcernResult.unacknowledged();
            } catch (RuntimeException e) {
                sendFailedEvent(connection, requestMessage, sentCommandStartedEvent, e, startTimeNanos);
                throw e;
            }
        } catch (Throwable th) {
            bsonOutput.close();
            throw th;
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, SingleResultCallback<WriteConcernResult> callback) {
        executeAsync(createRequestMessage(ProtocolHelper.getMessageSettings(connection.getDescription())), connection, callback);
    }

    private void executeAsync(RequestMessage requestMessage, InternalConnection connection, SingleResultCallback<WriteConcernResult> callback) {
        long startTimeNanos = System.nanoTime();
        boolean sentCommandStartedEvent = false;
        try {
            ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
            RequestMessage.EncodingMetadata encodingMetadata = ProtocolHelper.encodeMessageWithMetadata(requestMessage, bsonOutput);
            sendStartedEvent(connection, requestMessage, encodingMetadata, bsonOutput);
            sentCommandStartedEvent = true;
            connection.sendMessageAsync(bsonOutput.getByteBuffers(), requestMessage.getId(), new UnacknowledgedWriteResultCallback(callback, requestMessage, bsonOutput, connection, startTimeNanos));
        } catch (Throwable t) {
            sendFailedEvent(connection, requestMessage, sentCommandStartedEvent, t, startTimeNanos);
            callback.onResult(null, t);
        }
    }

    protected BsonDocument getBaseCommandDocument(String commandName) {
        BsonDocument baseCommandDocument = new BsonDocument(commandName, new BsonString(getNamespace().getCollectionName())).append("ordered", BsonBoolean.valueOf(isOrdered()));
        baseCommandDocument.append("writeConcern", WriteConcern.UNACKNOWLEDGED.asDocument());
        return baseCommandDocument;
    }

    protected String getCommandName(RequestMessage message) {
        switch (message.getOpCode()) {
            case OP_INSERT:
                return "insert";
            case OP_UPDATE:
                return "update";
            case OP_DELETE:
                return "delete";
            default:
                throw new MongoInternalException("Unexpected op code for write: " + message.getOpCode());
        }
    }

    private void sendStartedEvent(InternalConnection connection, RequestMessage message, RequestMessage.EncodingMetadata encodingMetadata, ByteBufferBsonOutput bsonOutput) {
        if (this.commandListener != null) {
            ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), getCommandName(message), getAsWriteCommand(bsonOutput, encodingMetadata.getFirstDocumentPosition()), connection.getDescription(), this.commandListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSucceededEvent(InternalConnection connection, RequestMessage message, long startTimeNanos) {
        if (this.commandListener != null) {
            sendSucceededEvent(connection, message, getResponseDocument(), startTimeNanos);
        }
    }

    private void sendSucceededEvent(InternalConnection connection, RequestMessage message, BsonDocument responseDocument, long startTimeNanos) {
        if (this.commandListener != null) {
            ProtocolHelper.sendCommandSucceededEvent(message, getCommandName(message), responseDocument, connection.getDescription(), System.nanoTime() - startTimeNanos, this.commandListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFailedEvent(InternalConnection connection, RequestMessage message, boolean sentCommandStartedEvent, Throwable t, long startTimeNanos) {
        if (this.commandListener != null && sentCommandStartedEvent) {
            ProtocolHelper.sendCommandFailedEvent(message, getCommandName(message), connection.getDescription(), System.nanoTime() - startTimeNanos, t, this.commandListener);
        }
    }

    private BsonDocument getResponseDocument() {
        return new BsonDocument("ok", new BsonInt32(1));
    }

    protected MongoNamespace getNamespace() {
        return this.namespace;
    }

    protected boolean isOrdered() {
        return this.ordered;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/WriteProtocol$UnacknowledgedWriteResultCallback.class */
    private final class UnacknowledgedWriteResultCallback implements SingleResultCallback<Void> {
        private final SingleResultCallback<WriteConcernResult> callback;
        private final RequestMessage message;
        private final OutputBuffer writtenBuffer;
        private final InternalConnection connection;
        private final long startTimeNanos;

        UnacknowledgedWriteResultCallback(SingleResultCallback<WriteConcernResult> callback, RequestMessage message, OutputBuffer writtenBuffer, InternalConnection connection, long startTimeNanos) {
            this.callback = callback;
            this.message = message;
            this.connection = connection;
            this.writtenBuffer = writtenBuffer;
            this.startTimeNanos = startTimeNanos;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(Void result, Throwable t) {
            this.writtenBuffer.close();
            if (t != null) {
                WriteProtocol.this.sendFailedEvent(this.connection, this.message, true, t, this.startTimeNanos);
                this.callback.onResult(null, t);
            } else {
                WriteProtocol.this.sendSucceededEvent(this.connection, this.message, this.startTimeNanos);
                this.callback.onResult(WriteConcernResult.unacknowledged(), null);
            }
        }
    }
}
