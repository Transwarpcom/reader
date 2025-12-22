package com.mongodb.binding;

import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncSingleConnectionReadBinding.class */
public class AsyncSingleConnectionReadBinding extends AbstractReferenceCounted implements AsyncReadBinding {
    private final ReadPreference readPreference;
    private final ServerDescription serverDescription;
    private final AsyncConnection connection;

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public AsyncSingleConnectionReadBinding(ReadPreference readPreference, ServerDescription serverDescription, AsyncConnection connection) {
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.serverDescription = (ServerDescription) Assertions.notNull("serverDescription", serverDescription);
        this.connection = ((AsyncConnection) Assertions.notNull("connection", connection)).retain();
    }

    @Override // com.mongodb.binding.AsyncReadBinding
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.binding.AsyncReadBinding, com.mongodb.binding.AsyncWriteBinding
    public SessionContext getSessionContext() {
        return NoOpSessionContext.INSTANCE;
    }

    @Override // com.mongodb.binding.AsyncReadBinding
    public void getReadConnectionSource(SingleResultCallback<AsyncConnectionSource> callback) {
        callback.onResult(new AsyncSingleConnectionSource(), null);
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public AsyncReadBinding retain() {
        super.retain();
        return this;
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public void release() {
        super.release();
        if (getCount() == 0) {
            this.connection.release();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncSingleConnectionReadBinding$AsyncSingleConnectionSource.class */
    private class AsyncSingleConnectionSource extends AbstractReferenceCounted implements AsyncConnectionSource {
        AsyncSingleConnectionSource() {
            AsyncSingleConnectionReadBinding.this.retain();
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public ServerDescription getServerDescription() {
            return AsyncSingleConnectionReadBinding.this.serverDescription;
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public SessionContext getSessionContext() {
            return NoOpSessionContext.INSTANCE;
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public void getConnection(SingleResultCallback<AsyncConnection> callback) {
            callback.onResult(AsyncSingleConnectionReadBinding.this.connection.retain(), null);
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public AsyncConnectionSource retain() {
            super.retain();
            return this;
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public void release() {
            super.release();
            if (super.getCount() == 0) {
                AsyncSingleConnectionReadBinding.this.release();
            }
        }
    }
}
