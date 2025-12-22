package com.mongodb.binding;

import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/SingleConnectionReadBinding.class */
public class SingleConnectionReadBinding extends AbstractReferenceCounted implements ReadBinding {
    private final ReadPreference readPreference;
    private final ServerDescription serverDescription;
    private final Connection connection;

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public SingleConnectionReadBinding(ReadPreference readPreference, ServerDescription serverDescription, Connection connection) {
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.serverDescription = (ServerDescription) Assertions.notNull("serverDescription", serverDescription);
        this.connection = ((Connection) Assertions.notNull("connection", connection)).retain();
    }

    @Override // com.mongodb.binding.ReadBinding
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.binding.ReadBinding
    public ConnectionSource getReadConnectionSource() {
        return new SingleConnectionSource();
    }

    @Override // com.mongodb.binding.ReadBinding, com.mongodb.binding.WriteBinding
    public SessionContext getSessionContext() {
        return NoOpSessionContext.INSTANCE;
    }

    @Override // com.mongodb.binding.AbstractReferenceCounted, com.mongodb.binding.ReferenceCounted
    public ReadBinding retain() {
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

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/SingleConnectionReadBinding$SingleConnectionSource.class */
    private class SingleConnectionSource extends AbstractReferenceCounted implements ConnectionSource {
        SingleConnectionSource() {
            SingleConnectionReadBinding.this.retain();
        }

        @Override // com.mongodb.binding.ConnectionSource
        public ServerDescription getServerDescription() {
            return SingleConnectionReadBinding.this.serverDescription;
        }

        @Override // com.mongodb.binding.ConnectionSource
        public SessionContext getSessionContext() {
            return NoOpSessionContext.INSTANCE;
        }

        @Override // com.mongodb.binding.ConnectionSource
        public Connection getConnection() {
            return SingleConnectionReadBinding.this.connection.retain();
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
                SingleConnectionReadBinding.this.release();
            }
        }
    }
}
