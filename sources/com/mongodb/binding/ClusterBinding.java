package com.mongodb.binding;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.Connection;
import com.mongodb.connection.Server;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.ReadConcernAwareNoOpSessionContext;
import com.mongodb.selector.ReadPreferenceServerSelector;
import com.mongodb.selector.ServerSelector;
import com.mongodb.selector.WritableServerSelector;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ClusterBinding.class */
public class ClusterBinding extends AbstractReferenceCounted implements ReadWriteBinding {
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
    public ClusterBinding(Cluster cluster, ReadPreference readPreference) {
        this(cluster, readPreference, ReadConcern.DEFAULT);
    }

    public ClusterBinding(Cluster cluster, ReadPreference readPreference, ReadConcern readConcern) {
        this.cluster = (Cluster) Assertions.notNull("cluster", cluster);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public ReadWriteBinding retain() {
        super.retain();
        return this;
    }

    @Override // com.mongodb.binding.ReadBinding
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.binding.ReadBinding
    public ConnectionSource getReadConnectionSource() {
        return new ClusterBindingConnectionSource(new ReadPreferenceServerSelector(this.readPreference));
    }

    @Override // com.mongodb.binding.ReadBinding, com.mongodb.binding.WriteBinding
    public SessionContext getSessionContext() {
        return new ReadConcernAwareNoOpSessionContext(this.readConcern);
    }

    @Override // com.mongodb.binding.WriteBinding
    public ConnectionSource getWriteConnectionSource() {
        return new ClusterBindingConnectionSource(new WritableServerSelector());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ClusterBinding$ClusterBindingConnectionSource.class */
    private final class ClusterBindingConnectionSource extends AbstractReferenceCounted implements ConnectionSource {
        private final Server server;

        private ClusterBindingConnectionSource(ServerSelector serverSelector) {
            this.server = ClusterBinding.this.cluster.selectServer(serverSelector);
            ClusterBinding.this.retain();
        }

        @Override // com.mongodb.binding.ConnectionSource
        public ServerDescription getServerDescription() {
            return this.server.getDescription();
        }

        @Override // com.mongodb.binding.ConnectionSource
        public SessionContext getSessionContext() {
            return new ReadConcernAwareNoOpSessionContext(ClusterBinding.this.readConcern);
        }

        @Override // com.mongodb.binding.ConnectionSource
        public Connection getConnection() {
            return this.server.getConnection();
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public ConnectionSource retain() {
            super.retain();
            ClusterBinding.this.retain();
            return this;
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public void release() {
            super.release();
            ClusterBinding.this.release();
        }
    }
}
