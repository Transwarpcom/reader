package com.mongodb.binding;

import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.Connection;
import com.mongodb.connection.Server;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.selector.ServerAddressSelector;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/SingleServerBinding.class */
public class SingleServerBinding extends AbstractReferenceCounted implements ReadWriteBinding {
    private final Cluster cluster;
    private final ServerAddress serverAddress;
    private final ReadPreference readPreference;

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public SingleServerBinding(Cluster cluster, ServerAddress serverAddress) {
        this(cluster, serverAddress, ReadPreference.primary());
    }

    public SingleServerBinding(Cluster cluster, ServerAddress serverAddress, ReadPreference readPreference) {
        this.cluster = (Cluster) Assertions.notNull("cluster", cluster);
        this.serverAddress = (ServerAddress) Assertions.notNull("serverAddress", serverAddress);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
    }

    @Override // com.mongodb.binding.WriteBinding
    public ConnectionSource getWriteConnectionSource() {
        return new SingleServerBindingConnectionSource();
    }

    @Override // com.mongodb.binding.ReadBinding
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.binding.ReadBinding
    public ConnectionSource getReadConnectionSource() {
        return new SingleServerBindingConnectionSource();
    }

    @Override // com.mongodb.binding.ReadBinding, com.mongodb.binding.WriteBinding
    public SessionContext getSessionContext() {
        return NoOpSessionContext.INSTANCE;
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public SingleServerBinding retain() {
        super.retain();
        return this;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/SingleServerBinding$SingleServerBindingConnectionSource.class */
    private final class SingleServerBindingConnectionSource extends AbstractReferenceCounted implements ConnectionSource {
        private final Server server;

        private SingleServerBindingConnectionSource() {
            SingleServerBinding.this.retain();
            this.server = SingleServerBinding.this.cluster.selectServer(new ServerAddressSelector(SingleServerBinding.this.serverAddress));
        }

        @Override // com.mongodb.binding.ConnectionSource
        public ServerDescription getServerDescription() {
            return this.server.getDescription();
        }

        @Override // com.mongodb.binding.ConnectionSource
        public SessionContext getSessionContext() {
            return NoOpSessionContext.INSTANCE;
        }

        @Override // com.mongodb.binding.ConnectionSource
        public Connection getConnection() {
            return SingleServerBinding.this.cluster.selectServer(new ServerAddressSelector(SingleServerBinding.this.serverAddress)).getConnection();
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public ConnectionSource retain() {
            super.retain();
            return this;
        }

        @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
        public void release() {
            super.release();
            if (super.getCount() == 0) {
                SingleServerBinding.this.release();
            }
        }
    }
}
