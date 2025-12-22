package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandResultCallback.class */
class CommandResultCallback<T> extends CommandResultBaseCallback<BsonDocument> {
    public static final Logger LOGGER = Loggers.getLogger("protocol.command");
    private final SingleResultCallback<T> callback;
    private final Decoder<T> decoder;

    CommandResultCallback(SingleResultCallback<T> callback, Decoder<T> decoder, long requestId, ServerAddress serverAddress) {
        super(new BsonDocumentCodec(), requestId, serverAddress);
        this.callback = callback;
        this.decoder = decoder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mongodb.internal.connection.CommandResultBaseCallback
    public void callCallback(BsonDocument bsonDocument, Throwable th) {
        if (th != null) {
            this.callback.onResult(null, th);
            return;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Command execution completed with status " + ProtocolHelper.isCommandOk(bsonDocument));
        }
        if (!ProtocolHelper.isCommandOk(bsonDocument)) {
            this.callback.onResult(null, ProtocolHelper.getCommandFailureException(bsonDocument, getServerAddress()));
            return;
        }
        try {
            this.callback.onResult(this.decoder.decode(new BsonDocumentReader(bsonDocument), DecoderContext.builder().build()), null);
        } catch (Throwable th2) {
            this.callback.onResult(null, th2);
        }
    }
}
