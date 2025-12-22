package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ResponseCallback.class */
abstract class ResponseCallback implements SingleResultCallback<ResponseBuffers> {
    private volatile boolean closed;
    private final ServerAddress serverAddress;
    private final long requestId;

    protected abstract void callCallback(ResponseBuffers responseBuffers, Throwable th);

    ResponseCallback(long requestId, ServerAddress serverAddress) {
        this.serverAddress = serverAddress;
        this.requestId = requestId;
    }

    protected ServerAddress getServerAddress() {
        return this.serverAddress;
    }

    protected long getRequestId() {
        return this.requestId;
    }

    @Override // com.mongodb.async.SingleResultCallback
    public void onResult(ResponseBuffers responseBuffers, Throwable t) {
        if (this.closed) {
            throw new MongoInternalException("Callback should not be invoked more than once");
        }
        this.closed = true;
        if (responseBuffers != null) {
            callCallback(responseBuffers, t);
        } else {
            callCallback(null, t);
        }
    }
}
