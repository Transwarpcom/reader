package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.CommandListener;
import java.util.Iterator;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/KillCursorProtocol.class */
class KillCursorProtocol implements LegacyProtocol<Void> {
    public static final Logger LOGGER = Loggers.getLogger("protocol.killcursor");
    private static final String COMMAND_NAME = "killCursors";
    private final MongoNamespace namespace;
    private final List<Long> cursors;
    private CommandListener commandListener;

    KillCursorProtocol(MongoNamespace namespace, List<Long> cursors) {
        this.namespace = namespace;
        this.cursors = cursors;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.internal.connection.LegacyProtocol
    public Void execute(InternalConnection connection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Killing cursors [%s] on connection [%s] to server %s", getCursorIdListAsString(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
        }
        ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
        long startTimeNanos = System.nanoTime();
        KillCursorsMessage message = null;
        try {
            try {
                message = new KillCursorsMessage(this.cursors);
                if (this.commandListener != null && this.namespace != null) {
                    ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), COMMAND_NAME, asCommandDocument(), connection.getDescription(), this.commandListener);
                }
                message.encode(bsonOutput, NoOpSessionContext.INSTANCE);
                connection.sendMessage(bsonOutput.getByteBuffers(), message.getId());
                if (this.commandListener != null && this.namespace != null) {
                    ProtocolHelper.sendCommandSucceededEvent(message, COMMAND_NAME, asCommandResponseDocument(), connection.getDescription(), System.nanoTime() - startTimeNanos, this.commandListener);
                }
                return null;
            } catch (RuntimeException e) {
                if (this.commandListener != null && this.namespace != null) {
                    ProtocolHelper.sendCommandFailedEvent(message, COMMAND_NAME, connection.getDescription(), System.nanoTime() - startTimeNanos, e, this.commandListener);
                }
                throw e;
            }
        } finally {
            bsonOutput.close();
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(final InternalConnection connection, final SingleResultCallback<Void> callback) {
        final long startTimeNanos = System.nanoTime();
        final KillCursorsMessage message = new KillCursorsMessage(this.cursors);
        boolean startEventSent = false;
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously killing cursors [%s] on connection [%s] to server %s", getCursorIdListAsString(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            final ByteBufferBsonOutput bsonOutput = new ByteBufferBsonOutput(connection);
            if (this.commandListener != null && this.namespace != null) {
                ProtocolHelper.sendCommandStartedEvent(message, this.namespace.getDatabaseName(), COMMAND_NAME, asCommandDocument(), connection.getDescription(), this.commandListener);
                startEventSent = true;
            }
            message.encode(bsonOutput, NoOpSessionContext.INSTANCE);
            connection.sendMessageAsync(bsonOutput.getByteBuffers(), message.getId(), new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.KillCursorProtocol.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(Void result, Throwable t) {
                    if (KillCursorProtocol.this.commandListener != null && KillCursorProtocol.this.namespace != null) {
                        if (t != null) {
                            ProtocolHelper.sendCommandFailedEvent(message, KillCursorProtocol.COMMAND_NAME, connection.getDescription(), System.nanoTime() - startTimeNanos, t, KillCursorProtocol.this.commandListener);
                        } else {
                            ProtocolHelper.sendCommandSucceededEvent(message, KillCursorProtocol.COMMAND_NAME, KillCursorProtocol.this.asCommandResponseDocument(), connection.getDescription(), System.nanoTime() - startTimeNanos, KillCursorProtocol.this.commandListener);
                        }
                    }
                    bsonOutput.close();
                    callback.onResult(result, t);
                }
            });
        } catch (Throwable t) {
            if (startEventSent) {
                ProtocolHelper.sendCommandFailedEvent(message, COMMAND_NAME, connection.getDescription(), System.nanoTime() - startTimeNanos, t, this.commandListener);
            }
            callback.onResult(null, t);
        }
    }

    @Override // com.mongodb.internal.connection.LegacyProtocol
    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    private BsonDocument asCommandDocument() {
        BsonArray array = new BsonArray();
        Iterator<Long> it = this.cursors.iterator();
        while (it.hasNext()) {
            long cursor = it.next().longValue();
            array.add((BsonValue) new BsonInt64(cursor));
        }
        return new BsonDocument(COMMAND_NAME, this.namespace == null ? new BsonInt32(1) : new BsonString(this.namespace.getCollectionName())).append("cursors", array);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument asCommandResponseDocument() {
        BsonArray cursorIdArray = new BsonArray();
        Iterator<Long> it = this.cursors.iterator();
        while (it.hasNext()) {
            long cursorId = it.next().longValue();
            cursorIdArray.add((BsonValue) new BsonInt64(cursorId));
        }
        return new BsonDocument("ok", new BsonDouble(1.0d)).append("cursorsUnknown", cursorIdArray);
    }

    private String getCursorIdListAsString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.cursors.size(); i++) {
            Long cursor = this.cursors.get(i);
            builder.append(cursor);
            if (i < this.cursors.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
