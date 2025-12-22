package com.mongodb.binding;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.Server;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.ReadConcernAwareNoOpSessionContext;
import com.mongodb.selector.ReadPreferenceServerSelector;
import com.mongodb.selector.ServerSelector;
import com.mongodb.selector.WritableServerSelector;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncClusterBinding.class */
public class AsyncClusterBinding extends AbstractReferenceCounted implements AsyncReadWriteBinding {
    private final Cluster cluster;
    private final ReadPreference readPreference;
    private final ReadConcern readConcern;

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    @Deprecated
    public AsyncClusterBinding(Cluster cluster, ReadPreference readPreference) {
        this(cluster, readPreference, ReadConcern.DEFAULT);
    }

    public AsyncClusterBinding(Cluster cluster, ReadPreference readPreference, ReadConcern readConcern) {
        this.cluster = (Cluster) Assertions.notNull("cluster", cluster);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public AsyncReadWriteBinding retain() {
        super.retain();
        return this;
    }

    @Override // com.mongodb.binding.AsyncReadBinding
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.binding.AsyncReadBinding, com.mongodb.binding.AsyncWriteBinding
    public SessionContext getSessionContext() {
        return new ReadConcernAwareNoOpSessionContext(this.readConcern);
    }

    @Override // com.mongodb.binding.AsyncReadBinding
    public void getReadConnectionSource(SingleResultCallback<AsyncConnectionSource> callback) {
        getAsyncClusterBindingConnectionSource(new ReadPreferenceServerSelector(this.readPreference), callback);
    }

    @Override // com.mongodb.binding.AsyncWriteBinding
    public void getWriteConnectionSource(SingleResultCallback<AsyncConnectionSource> callback) {
        getAsyncClusterBindingConnectionSource(new WritableServerSelector(), callback);
    }

    private void getAsyncClusterBindingConnectionSource(ServerSelector serverSelector, final SingleResultCallback<AsyncConnectionSource> callback) {
        this.cluster.selectServerAsync(serverSelector, new SingleResultCallback<Server>() { // from class: com.mongodb.binding.AsyncClusterBinding.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Server result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                } else {
                    callback.onResult(new AsyncClusterBindingConnectionSource(result), null);
                }
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncClusterBinding$AsyncClusterBindingConnectionSource.class */
    private final class AsyncClusterBindingConnectionSource extends AbstractReferenceCounted implements AsyncConnectionSource {
        private final Server server;

        private AsyncClusterBindingConnectionSource(Server server) {
            this.server = server;
            AsyncClusterBinding.this.retain();
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public ServerDescription getServerDescription() {
            return this.server.getDescription();
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public SessionContext getSessionContext() {
            return new ReadConcernAwareNoOpSessionContext(AsyncClusterBinding.this.readConcern);
        }

        @Override // com.mongodb.binding.AsyncConnectionSource
        public void getConnection(SingleResultCallback<AsyncConnection> callback) {
            this.server.getConnectionAsync(callback);
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public AsyncConnectionSource retain() {
            super.retain();
            AsyncClusterBinding.this.retain();
            return this;
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public void release() {
            super.release();
            AsyncClusterBinding.this.release();
        }
    }
}
