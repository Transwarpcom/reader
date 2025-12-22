package com.mongodb.internal.connection;

import com.mongodb.MongoCompressor;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoSecurityException;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ConnectionId;
import com.mongodb.connection.ServerType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnectionInitializer.class */
public class InternalStreamConnectionInitializer implements InternalConnectionInitializer {
    private final List<Authenticator> authenticators;
    private final BsonDocument clientMetadataDocument;
    private final List<MongoCompressor> requestedCompressors;
    private final boolean checkSaslSupportedMechs;

    public InternalStreamConnectionInitializer(List<Authenticator> authenticators, BsonDocument clientMetadataDocument, List<MongoCompressor> requestedCompressors) {
        this.authenticators = new ArrayList((Collection) Assertions.notNull("authenticators", authenticators));
        this.clientMetadataDocument = clientMetadataDocument;
        this.requestedCompressors = (List) Assertions.notNull("requestedCompressors", requestedCompressors);
        this.checkSaslSupportedMechs = this.authenticators.size() > 0 && (this.authenticators.get(0) instanceof DefaultAuthenticator);
    }

    @Override // com.mongodb.internal.connection.InternalConnectionInitializer
    public ConnectionDescription initialize(InternalConnection internalConnection) {
        Assertions.notNull("internalConnection", internalConnection);
        ConnectionDescription connectionDescription = initializeConnectionDescription(internalConnection);
        authenticateAll(internalConnection, connectionDescription);
        return completeConnectionDescriptionInitialization(internalConnection, connectionDescription);
    }

    @Override // com.mongodb.internal.connection.InternalConnectionInitializer
    public void initializeAsync(InternalConnection internalConnection, SingleResultCallback<ConnectionDescription> callback) {
        initializeConnectionDescriptionAsync(internalConnection, createConnectionDescriptionCallback(internalConnection, callback));
    }

    private SingleResultCallback<ConnectionDescription> createConnectionDescriptionCallback(final InternalConnection internalConnection, final SingleResultCallback<ConnectionDescription> callback) {
        return new SingleResultCallback<ConnectionDescription>() { // from class: com.mongodb.internal.connection.InternalStreamConnectionInitializer.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(final ConnectionDescription connectionDescription, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                } else {
                    InternalStreamConnectionInitializer.this.new CompoundAuthenticator(internalConnection, connectionDescription, new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.InternalStreamConnectionInitializer.1.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(Void result, Throwable t2) {
                            if (t2 == null) {
                                InternalStreamConnectionInitializer.this.completeConnectionDescriptionInitializationAsync(internalConnection, connectionDescription, callback);
                            } else {
                                callback.onResult(null, t2);
                            }
                        }
                    }).start();
                }
            }
        };
    }

    private ConnectionDescription initializeConnectionDescription(InternalConnection internalConnection) {
        BsonDocument isMasterCommandDocument = createIsMasterCommand();
        try {
            BsonDocument isMasterResult = CommandHelper.executeCommand("admin", isMasterCommandDocument, internalConnection);
            BsonDocument buildInfoResult = CommandHelper.executeCommand("admin", new BsonDocument("buildinfo", new BsonInt32(1)), internalConnection);
            setFirstAuthenticator(isMasterResult, buildInfoResult);
            return DescriptionHelper.createConnectionDescription(internalConnection.getDescription().getConnectionId(), isMasterResult, buildInfoResult);
        } catch (MongoException e) {
            if (this.checkSaslSupportedMechs && e.getCode() == 11) {
                MongoCredential credential = this.authenticators.get(0).getMongoCredential();
                throw new MongoSecurityException(credential, String.format("Exception authenticating %s", credential), e);
            }
            throw e;
        }
    }

    private BsonDocument createIsMasterCommand() {
        BsonDocument isMasterCommandDocument = new BsonDocument("ismaster", new BsonInt32(1));
        if (this.clientMetadataDocument != null) {
            isMasterCommandDocument.append("client", this.clientMetadataDocument);
        }
        if (!this.requestedCompressors.isEmpty()) {
            BsonArray compressors = new BsonArray();
            for (MongoCompressor cur : this.requestedCompressors) {
                compressors.add((BsonValue) new BsonString(cur.getName()));
            }
            isMasterCommandDocument.append("compression", compressors);
        }
        if (this.checkSaslSupportedMechs) {
            MongoCredential credential = this.authenticators.get(0).getMongoCredential();
            isMasterCommandDocument.append("saslSupportedMechs", new BsonString(credential.getSource() + "." + credential.getUserName()));
        }
        return isMasterCommandDocument;
    }

    private ConnectionDescription completeConnectionDescriptionInitialization(InternalConnection internalConnection, ConnectionDescription connectionDescription) {
        return applyGetLastErrorResult(CommandHelper.executeCommandWithoutCheckingForFailure("admin", new BsonDocument("getlasterror", new BsonInt32(1)), internalConnection), connectionDescription);
    }

    private void authenticateAll(InternalConnection internalConnection, ConnectionDescription connectionDescription) {
        if (connectionDescription.getServerType() != ServerType.REPLICA_SET_ARBITER) {
            for (Authenticator cur : this.authenticators) {
                cur.authenticate(internalConnection, connectionDescription);
            }
        }
    }

    private void initializeConnectionDescriptionAsync(final InternalConnection internalConnection, final SingleResultCallback<ConnectionDescription> callback) {
        CommandHelper.executeCommandAsync("admin", createIsMasterCommand(), internalConnection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.InternalStreamConnectionInitializer.2
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(final BsonDocument isMasterResult, Throwable t) {
                if (t != null) {
                    if (InternalStreamConnectionInitializer.this.checkSaslSupportedMechs && (t instanceof MongoException) && ((MongoException) t).getCode() == 11) {
                        MongoCredential credential = ((Authenticator) InternalStreamConnectionInitializer.this.authenticators.get(0)).getMongoCredential();
                        callback.onResult(null, new MongoSecurityException(credential, String.format("Exception authenticating %s", credential), t));
                        return;
                    } else {
                        callback.onResult(null, t);
                        return;
                    }
                }
                CommandHelper.executeCommandAsync("admin", new BsonDocument("buildinfo", new BsonInt32(1)), internalConnection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.InternalStreamConnectionInitializer.2.1
                    @Override // com.mongodb.async.SingleResultCallback
                    public void onResult(BsonDocument buildInfoResult, Throwable t2) {
                        if (t2 != null) {
                            callback.onResult(null, t2);
                            return;
                        }
                        ConnectionId connectionId = internalConnection.getDescription().getConnectionId();
                        InternalStreamConnectionInitializer.this.setFirstAuthenticator(isMasterResult, buildInfoResult);
                        callback.onResult(DescriptionHelper.createConnectionDescription(connectionId, isMasterResult, buildInfoResult), null);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFirstAuthenticator(BsonDocument isMasterResult, BsonDocument buildInfoResult) {
        if (this.checkSaslSupportedMechs) {
            this.authenticators.set(0, ((DefaultAuthenticator) this.authenticators.get(0)).getAuthenticatorFromIsMasterResult(isMasterResult, DescriptionHelper.getVersion(buildInfoResult)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completeConnectionDescriptionInitializationAsync(InternalConnection internalConnection, final ConnectionDescription connectionDescription, final SingleResultCallback<ConnectionDescription> callback) {
        CommandHelper.executeCommandAsync("admin", new BsonDocument("getlasterror", new BsonInt32(1)), internalConnection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.InternalStreamConnectionInitializer.3
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(BsonDocument result, Throwable t) {
                if (result != null) {
                    callback.onResult(InternalStreamConnectionInitializer.this.applyGetLastErrorResult(result, connectionDescription), null);
                } else {
                    callback.onResult(connectionDescription, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConnectionDescription applyGetLastErrorResult(BsonDocument getLastErrorResult, ConnectionDescription connectionDescription) {
        ConnectionId connectionId;
        if (getLastErrorResult.containsKey("connectionId")) {
            connectionId = connectionDescription.getConnectionId().withServerValue(getLastErrorResult.getNumber("connectionId").intValue());
        } else {
            connectionId = connectionDescription.getConnectionId();
        }
        return connectionDescription.withConnectionId(connectionId);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnectionInitializer$CompoundAuthenticator.class */
    private class CompoundAuthenticator implements SingleResultCallback<Void> {
        private final InternalConnection internalConnection;
        private final ConnectionDescription connectionDescription;
        private final SingleResultCallback<Void> callback;
        private final AtomicInteger currentAuthenticatorIndex = new AtomicInteger(-1);

        CompoundAuthenticator(InternalConnection internalConnection, ConnectionDescription connectionDescription, SingleResultCallback<Void> callback) {
            this.internalConnection = internalConnection;
            this.connectionDescription = connectionDescription;
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(Void result, Throwable t) {
            if (t != null) {
                this.callback.onResult(null, t);
            } else if (completedAuthentication()) {
                this.callback.onResult(null, null);
            } else {
                authenticateNext();
            }
        }

        public void start() {
            if (this.connectionDescription.getServerType() == ServerType.REPLICA_SET_ARBITER || InternalStreamConnectionInitializer.this.authenticators.isEmpty()) {
                this.callback.onResult(null, null);
            } else {
                authenticateNext();
            }
        }

        private boolean completedAuthentication() {
            return this.currentAuthenticatorIndex.get() == InternalStreamConnectionInitializer.this.authenticators.size() - 1;
        }

        private void authenticateNext() {
            ((Authenticator) InternalStreamConnectionInitializer.this.authenticators.get(this.currentAuthenticatorIndex.incrementAndGet())).authenticateAsync(this.internalConnection, this.connectionDescription, this);
        }
    }
}
