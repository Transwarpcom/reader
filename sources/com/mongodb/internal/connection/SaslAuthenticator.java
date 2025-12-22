package com.mongodb.internal.connection;

import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoSecurityException;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.lang.Nullable;
import java.security.PrivilegedAction;
import javax.security.auth.Subject;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.bson.BsonBinary;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SaslAuthenticator.class */
abstract class SaslAuthenticator extends Authenticator {
    public abstract String getMechanismName();

    protected abstract SaslClient createSaslClient(ServerAddress serverAddress);

    SaslAuthenticator(MongoCredentialWithCache credential) {
        super(credential);
    }

    @Override // com.mongodb.internal.connection.Authenticator
    public void authenticate(final InternalConnection connection, ConnectionDescription connectionDescription) {
        doAsSubject(new PrivilegedAction<Void>() { // from class: com.mongodb.internal.connection.SaslAuthenticator.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Void run() {
                SaslClient saslClient = SaslAuthenticator.this.createSaslClient(connection.getDescription().getServerAddress());
                SaslAuthenticator.this.throwIfSaslClientIsNull(saslClient);
                try {
                    try {
                        BsonDocument res = SaslAuthenticator.this.sendSaslStart(saslClient.hasInitialResponse() ? saslClient.evaluateChallenge(new byte[0]) : null, connection);
                        BsonInt32 conversationId = res.getInt32("conversationId");
                        while (!res.getBoolean(ES6Iterator.DONE_PROPERTY).getValue()) {
                            byte[] response = saslClient.evaluateChallenge(res.getBinary("payload").getData());
                            if (response != null) {
                                res = SaslAuthenticator.this.sendSaslContinue(conversationId, response, connection);
                            } else {
                                throw new MongoSecurityException(SaslAuthenticator.this.getMongoCredential(), "SASL protocol error: no client response to challenge for credential " + SaslAuthenticator.this.getMongoCredential());
                            }
                        }
                        return null;
                    } catch (Exception e) {
                        throw SaslAuthenticator.this.wrapException(e);
                    }
                } finally {
                    SaslAuthenticator.this.disposeOfSaslClient(saslClient);
                }
            }
        });
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticateAsync(final InternalConnection connection, ConnectionDescription connectionDescription, final SingleResultCallback<Void> callback) {
        try {
            doAsSubject(new PrivilegedAction<Void>() { // from class: com.mongodb.internal.connection.SaslAuthenticator.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                public Void run() {
                    final SaslClient saslClient = SaslAuthenticator.this.createSaslClient(connection.getDescription().getServerAddress());
                    SaslAuthenticator.this.throwIfSaslClientIsNull(saslClient);
                    try {
                        byte[] response = saslClient.hasInitialResponse() ? saslClient.evaluateChallenge(new byte[0]) : null;
                        SaslAuthenticator.this.sendSaslStartAsync(response, connection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.SaslAuthenticator.2.1
                            @Override // com.mongodb.async.SingleResultCallback
                            public void onResult(BsonDocument result, Throwable t) {
                                if (t != null) {
                                    callback.onResult(null, SaslAuthenticator.this.wrapException(t));
                                } else if (result.getBoolean(ES6Iterator.DONE_PROPERTY).getValue()) {
                                    callback.onResult(null, null);
                                } else {
                                    SaslAuthenticator.this.new Continuator(saslClient, result, connection, callback).start();
                                }
                            }
                        });
                        return null;
                    } catch (SaslException e) {
                        throw SaslAuthenticator.this.wrapException(e);
                    }
                }
            });
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void throwIfSaslClientIsNull(SaslClient saslClient) {
        if (saslClient == null) {
            throw new MongoSecurityException(getMongoCredential(), String.format("This JDK does not support the %s SASL mechanism", getMechanismName()));
        }
    }

    @Nullable
    private Subject getSubject() {
        return (Subject) getMongoCredential().getMechanismProperty(MongoCredential.JAVA_SUBJECT_KEY, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument sendSaslStart(byte[] outToken, InternalConnection connection) {
        return CommandHelper.executeCommand(getMongoCredential().getSource(), createSaslStartCommandDocument(outToken), connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument sendSaslContinue(BsonInt32 conversationId, byte[] outToken, InternalConnection connection) {
        return CommandHelper.executeCommand(getMongoCredential().getSource(), createSaslContinueDocument(conversationId, outToken), connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSaslStartAsync(byte[] outToken, InternalConnection connection, SingleResultCallback<BsonDocument> callback) {
        CommandHelper.executeCommandAsync(getMongoCredential().getSource(), createSaslStartCommandDocument(outToken), connection, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSaslContinueAsync(BsonInt32 conversationId, byte[] outToken, InternalConnection connection, SingleResultCallback<BsonDocument> callback) {
        CommandHelper.executeCommandAsync(getMongoCredential().getSource(), createSaslContinueDocument(conversationId, outToken), connection, callback);
    }

    private BsonDocument createSaslStartCommandDocument(byte[] outToken) {
        return new BsonDocument("saslStart", new BsonInt32(1)).append("mechanism", new BsonString(getMechanismName())).append("payload", new BsonBinary(outToken != null ? outToken : new byte[0]));
    }

    private BsonDocument createSaslContinueDocument(BsonInt32 conversationId, byte[] outToken) {
        return new BsonDocument("saslContinue", new BsonInt32(1)).append("conversationId", conversationId).append("payload", new BsonBinary(outToken));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disposeOfSaslClient(SaslClient saslClient) {
        try {
            saslClient.dispose();
        } catch (SaslException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoException wrapException(Throwable t) {
        if (t instanceof MongoInterruptedException) {
            return (MongoInterruptedException) t;
        }
        if (t instanceof MongoSecurityException) {
            return (MongoSecurityException) t;
        }
        return new MongoSecurityException(getMongoCredential(), "Exception authenticating " + getMongoCredential(), t);
    }

    void doAsSubject(PrivilegedAction<Void> action) {
        if (getSubject() == null) {
            action.run();
        } else {
            Subject.doAs(getSubject(), action);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SaslAuthenticator$Continuator.class */
    private final class Continuator implements SingleResultCallback<BsonDocument> {
        private final SaslClient saslClient;
        private final BsonDocument saslStartDocument;
        private final InternalConnection connection;
        private final SingleResultCallback<Void> callback;

        Continuator(SaslClient saslClient, BsonDocument saslStartDocument, InternalConnection connection, SingleResultCallback<Void> callback) {
            this.saslClient = saslClient;
            this.saslStartDocument = saslStartDocument;
            this.connection = connection;
            this.callback = callback;
        }

        @Override // com.mongodb.async.SingleResultCallback
        public void onResult(BsonDocument result, Throwable t) {
            if (t != null) {
                this.callback.onResult(null, SaslAuthenticator.this.wrapException(t));
                SaslAuthenticator.this.disposeOfSaslClient(this.saslClient);
            } else if (result.getBoolean(ES6Iterator.DONE_PROPERTY).getValue()) {
                this.callback.onResult(null, null);
                SaslAuthenticator.this.disposeOfSaslClient(this.saslClient);
            } else {
                continueConversation(result);
            }
        }

        public void start() {
            continueConversation(this.saslStartDocument);
        }

        private void continueConversation(final BsonDocument result) {
            try {
                SaslAuthenticator.this.doAsSubject(new PrivilegedAction<Void>() { // from class: com.mongodb.internal.connection.SaslAuthenticator.Continuator.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        try {
                            SaslAuthenticator.this.sendSaslContinueAsync(Continuator.this.saslStartDocument.getInt32("conversationId"), Continuator.this.saslClient.evaluateChallenge(result.getBinary("payload").getData()), Continuator.this.connection, Continuator.this);
                            return null;
                        } catch (SaslException e) {
                            throw SaslAuthenticator.this.wrapException(e);
                        }
                    }
                });
            } catch (Throwable t) {
                this.callback.onResult(null, t);
                SaslAuthenticator.this.disposeOfSaslClient(this.saslClient);
            }
        }
    }
}
