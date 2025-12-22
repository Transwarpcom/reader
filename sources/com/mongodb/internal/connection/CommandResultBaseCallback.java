package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandResultBaseCallback.class */
abstract class CommandResultBaseCallback<T> extends ResponseCallback {
    public static final Logger LOGGER = Loggers.getLogger("protocol.command");
    private final Decoder<T> decoder;

    protected abstract void callCallback(T t, Throwable th);

    CommandResultBaseCallback(Decoder<T> decoder, long requestId, ServerAddress serverAddress) {
        super(requestId, serverAddress);
        this.decoder = decoder;
    }

    @Override // com.mongodb.internal.connection.ResponseCallback
    protected void callCallback(ResponseBuffers responseBuffers, Throwable t) {
        try {
            if (t != null || responseBuffers == null) {
                callCallback((CommandResultBaseCallback<T>) null, t);
            } else {
                ReplyMessage<T> replyMessage = new ReplyMessage<>(responseBuffers, this.decoder, getRequestId());
                callCallback((CommandResultBaseCallback<T>) replyMessage.getDocuments().get(0), (Throwable) null);
            }
            if (responseBuffers != null) {
                try {
                    responseBuffers.close();
                } catch (Throwable t1) {
                    LOGGER.debug("GetMore ResponseBuffer close exception", t1);
                }
            }
        } catch (Throwable th) {
            if (responseBuffers != null) {
                try {
                    responseBuffers.close();
                } catch (Throwable t12) {
                    LOGGER.debug("GetMore ResponseBuffer close exception", t12);
                    throw th;
                }
            }
            throw th;
        }
    }
}
