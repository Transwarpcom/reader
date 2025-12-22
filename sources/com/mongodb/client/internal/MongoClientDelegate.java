package com.mongodb.client.internal;

import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClientException;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.binding.ClusterBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.binding.ReadWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.client.ClientSession;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.session.ServerSessionPool;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.ReadOperation;
import com.mongodb.operation.WriteOperation;
import com.mongodb.selector.ServerSelector;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoClientDelegate.class */
public class MongoClientDelegate {
    private final Cluster cluster;
    private final ServerSessionPool serverSessionPool;
    private final List<MongoCredential> credentialList;
    private final Object originator;
    private final OperationExecutor operationExecutor;

    public MongoClientDelegate(Cluster cluster, List<MongoCredential> credentialList, Object originator) {
        this(cluster, credentialList, originator, null);
    }

    public MongoClientDelegate(Cluster cluster, List<MongoCredential> credentialList, Object originator, @Nullable OperationExecutor operationExecutor) {
        this.cluster = cluster;
        this.serverSessionPool = new ServerSessionPool(cluster);
        this.credentialList = credentialList;
        this.originator = originator;
        this.operationExecutor = operationExecutor == null ? new DelegateOperationExecutor() : operationExecutor;
    }

    public OperationExecutor getOperationExecutor() {
        return this.operationExecutor;
    }

    @Nullable
    public ClientSession createClientSession(ClientSessionOptions options, ReadConcern readConcern, WriteConcern writeConcern, ReadPreference readPreference) {
        Assertions.notNull("readConcern", readConcern);
        Assertions.notNull("writeConcern", writeConcern);
        Assertions.notNull("readPreference", readPreference);
        if (this.credentialList.size() > 1) {
            return null;
        }
        ClusterDescription connectedClusterDescription = getConnectedClusterDescription();
        if (connectedClusterDescription.getType() == ClusterType.STANDALONE || connectedClusterDescription.getLogicalSessionTimeoutMinutes() == null) {
            return null;
        }
        ClientSessionOptions mergedOptions = ClientSessionOptions.builder(options).defaultTransactionOptions(TransactionOptions.merge(options.getDefaultTransactionOptions(), TransactionOptions.builder().readConcern(readConcern).writeConcern(writeConcern).readPreference(readPreference).build())).build();
        return new ClientSessionImpl(this.serverSessionPool, this.originator, mergedOptions, this);
    }

    public List<ServerAddress> getServerAddressList() {
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (ServerDescription cur : this.cluster.getDescription().getServerDescriptions()) {
            serverAddresses.add(cur.getAddress());
        }
        return serverAddresses;
    }

    public void close() {
        this.serverSessionPool.close();
        this.cluster.close();
    }

    public Cluster getCluster() {
        return this.cluster;
    }

    public ServerSessionPool getServerSessionPool() {
        return this.serverSessionPool;
    }

    private ClusterDescription getConnectedClusterDescription() {
        ClusterDescription clusterDescription = this.cluster.getDescription();
        if (getServerDescriptionListToConsiderForSessionSupport(clusterDescription).isEmpty()) {
            this.cluster.selectServer(new ServerSelector() { // from class: com.mongodb.client.internal.MongoClientDelegate.1
                @Override // com.mongodb.selector.ServerSelector
                public List<ServerDescription> select(ClusterDescription clusterDescription2) {
                    return MongoClientDelegate.this.getServerDescriptionListToConsiderForSessionSupport(clusterDescription2);
                }
            });
            clusterDescription = this.cluster.getDescription();
        }
        return clusterDescription;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ServerDescription> getServerDescriptionListToConsiderForSessionSupport(ClusterDescription clusterDescription) {
        if (clusterDescription.getConnectionMode() == ClusterConnectionMode.SINGLE) {
            return clusterDescription.getAny();
        }
        return clusterDescription.getAnyPrimaryOrSecondary();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoClientDelegate$DelegateOperationExecutor.class */
    private class DelegateOperationExecutor implements OperationExecutor {
        private DelegateOperationExecutor() {
        }

        @Override // com.mongodb.client.internal.OperationExecutor
        public <T> T execute(ReadOperation<T> readOperation, ReadPreference readPreference, ReadConcern readConcern) {
            return (T) execute(readOperation, readPreference, readConcern, null);
        }

        @Override // com.mongodb.client.internal.OperationExecutor
        public <T> T execute(WriteOperation<T> writeOperation, ReadConcern readConcern) {
            return (T) execute(writeOperation, readConcern, (ClientSession) null);
        }

        @Override // com.mongodb.client.internal.OperationExecutor
        public <T> T execute(ReadOperation<T> operation, ReadPreference readPreference, ReadConcern readConcern, @Nullable ClientSession session) {
            ClientSession actualClientSession = getClientSession(session);
            ReadBinding binding = getReadBinding(readPreference, readConcern, actualClientSession, session == null && actualClientSession != null);
            try {
                if (session != null) {
                    try {
                        if (session.hasActiveTransaction() && !binding.getReadPreference().equals(ReadPreference.primary())) {
                            throw new MongoClientException("Read preference in a transaction must be primary");
                        }
                    } catch (MongoException e) {
                        labelException(session, e);
                        throw e;
                    }
                }
                T tExecute = operation.execute(binding);
                binding.release();
                return tExecute;
            } catch (Throwable th) {
                binding.release();
                throw th;
            }
        }

        @Override // com.mongodb.client.internal.OperationExecutor
        public <T> T execute(WriteOperation<T> operation, ReadConcern readConcern, @Nullable ClientSession session) {
            ClientSession actualClientSession = getClientSession(session);
            WriteBinding binding = getWriteBinding(readConcern, actualClientSession, session == null && actualClientSession != null);
            try {
                try {
                    T tExecute = operation.execute(binding);
                    binding.release();
                    return tExecute;
                } catch (MongoException e) {
                    labelException(session, e);
                    throw e;
                }
            } catch (Throwable th) {
                binding.release();
                throw th;
            }
        }

        WriteBinding getWriteBinding(ReadConcern readConcern, @Nullable ClientSession session, boolean ownsSession) {
            return getReadWriteBinding(ReadPreference.primary(), readConcern, session, ownsSession);
        }

        ReadBinding getReadBinding(ReadPreference readPreference, ReadConcern readConcern, @Nullable ClientSession session, boolean ownsSession) {
            return getReadWriteBinding(readPreference, readConcern, session, ownsSession);
        }

        ReadWriteBinding getReadWriteBinding(ReadPreference readPreference, ReadConcern readConcern, @Nullable ClientSession session, boolean ownsSession) {
            ReadWriteBinding readWriteBinding = new ClusterBinding(MongoClientDelegate.this.cluster, getReadPreferenceForBinding(readPreference, session), readConcern);
            if (session != null) {
                readWriteBinding = new ClientSessionBinding(session, ownsSession, readWriteBinding);
            }
            return readWriteBinding;
        }

        private void labelException(@Nullable ClientSession session, MongoException e) {
            if (((e instanceof MongoSocketException) || (e instanceof MongoTimeoutException)) && session != null && session.hasActiveTransaction() && !e.hasErrorLabel(MongoException.UNKNOWN_TRANSACTION_COMMIT_RESULT_LABEL)) {
                e.addLabel(MongoException.TRANSIENT_TRANSACTION_ERROR_LABEL);
            }
        }

        private ReadPreference getReadPreferenceForBinding(ReadPreference readPreference, @Nullable ClientSession session) {
            if (session == null) {
                return readPreference;
            }
            if (session.hasActiveTransaction()) {
                ReadPreference readPreferenceForBinding = session.getTransactionOptions().getReadPreference();
                if (readPreferenceForBinding == null) {
                    throw new MongoInternalException("Invariant violated.  Transaction options read preference can not be null");
                }
                return readPreferenceForBinding;
            }
            return readPreference;
        }

        @Nullable
        ClientSession getClientSession(@Nullable ClientSession clientSessionFromOperation) {
            ClientSession session;
            if (clientSessionFromOperation != null) {
                Assertions.isTrue("ClientSession from same MongoClient", clientSessionFromOperation.getOriginator() == MongoClientDelegate.this.originator);
                session = clientSessionFromOperation;
            } else {
                session = MongoClientDelegate.this.createClientSession(ClientSessionOptions.builder().causallyConsistent(false).build(), ReadConcern.DEFAULT, WriteConcern.ACKNOWLEDGED, ReadPreference.primary());
            }
            return session;
        }
    }
}
