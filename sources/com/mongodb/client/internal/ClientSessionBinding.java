package com.mongodb.client.internal;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadWriteBinding;
import com.mongodb.client.ClientSession;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.session.ClientSessionContext;
import com.mongodb.session.SessionContext;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ClientSessionBinding.class */
public class ClientSessionBinding implements ReadWriteBinding {
    private final ReadWriteBinding wrapped;
    private final ClientSession session;
    private final boolean ownsSession;
    private final ClientSessionContext sessionContext;

    public ClientSessionBinding(ClientSession session, boolean ownsSession, ReadWriteBinding wrapped) {
        this.wrapped = (ReadWriteBinding) Assertions.notNull("wrapped", wrapped);
        this.ownsSession = ownsSession;
        this.session = (ClientSession) Assertions.notNull("session", session);
        this.sessionContext = new SyncClientSessionContext(session);
    }

    @Override // com.mongodb.binding.ReadBinding
    public ReadPreference getReadPreference() {
        return this.wrapped.getReadPreference();
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public int getCount() {
        return this.wrapped.getCount();
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public ReadWriteBinding retain() {
        this.wrapped.retain();
        return this;
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public void release() {
        this.wrapped.release();
        closeSessionIfCountIsZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSessionIfCountIsZero() {
        if (getCount() == 0 && this.ownsSession) {
            this.session.close();
        }
    }

    @Override // com.mongodb.binding.ReadBinding
    public ConnectionSource getReadConnectionSource() {
        ConnectionSource readConnectionSource = this.wrapped.getReadConnectionSource();
        return new SessionBindingConnectionSource(readConnectionSource);
    }

    @Override // com.mongodb.binding.ReadBinding, com.mongodb.binding.WriteBinding
    public SessionContext getSessionContext() {
        return this.sessionContext;
    }

    @Override // com.mongodb.binding.WriteBinding
    public ConnectionSource getWriteConnectionSource() {
        ConnectionSource writeConnectionSource = this.wrapped.getWriteConnectionSource();
        return new SessionBindingConnectionSource(writeConnectionSource);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ClientSessionBinding$SessionBindingConnectionSource.class */
    private class SessionBindingConnectionSource implements ConnectionSource {
        private ConnectionSource wrapped;

        SessionBindingConnectionSource(ConnectionSource wrapped) {
            this.wrapped = wrapped;
        }

        @Override // com.mongodb.binding.ConnectionSource
        public ServerDescription getServerDescription() {
            return this.wrapped.getServerDescription();
        }

        @Override // com.mongodb.binding.ConnectionSource
        public SessionContext getSessionContext() {
            return ClientSessionBinding.this.sessionContext;
        }

        @Override // com.mongodb.binding.ConnectionSource
        public Connection getConnection() {
            return this.wrapped.getConnection();
        }

        @Override // com.mongodb.binding.ReferenceCounted
        public ConnectionSource retain() {
            this.wrapped = this.wrapped.retain();
            return this;
        }

        @Override // com.mongodb.binding.ReferenceCounted
        public int getCount() {
            return this.wrapped.getCount();
        }

        @Override // com.mongodb.binding.ReferenceCounted
        public void release() {
            this.wrapped.release();
            ClientSessionBinding.this.closeSessionIfCountIsZero();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ClientSessionBinding$SyncClientSessionContext.class */
    private final class SyncClientSessionContext extends ClientSessionContext implements SessionContext {
        private final ClientSession clientSession;

        SyncClientSessionContext(ClientSession clientSession) {
            super(clientSession);
            this.clientSession = clientSession;
        }

        @Override // com.mongodb.session.SessionContext
        public boolean isImplicitSession() {
            return ClientSessionBinding.this.ownsSession;
        }

        @Override // com.mongodb.session.SessionContext
        public boolean notifyMessageSent() {
            return this.clientSession.notifyMessageSent();
        }

        @Override // com.mongodb.session.SessionContext
        public boolean hasActiveTransaction() {
            return this.clientSession.hasActiveTransaction();
        }

        @Override // com.mongodb.session.SessionContext
        public ReadConcern getReadConcern() {
            if (!this.clientSession.hasActiveTransaction()) {
                return ClientSessionBinding.this.wrapped.getSessionContext().getReadConcern();
            }
            return this.clientSession.getTransactionOptions().getReadConcern();
        }
    }
}
