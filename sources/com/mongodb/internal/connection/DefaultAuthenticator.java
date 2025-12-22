package com.mongodb.internal.connection;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoException;
import com.mongodb.MongoSecurityException;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerVersion;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultAuthenticator.class */
class DefaultAuthenticator extends Authenticator {
    static final int USER_NOT_FOUND_CODE = 11;
    private static final ServerVersion FOUR_ZERO = new ServerVersion(4, 0);
    private static final ServerVersion THREE_ZERO = new ServerVersion(3, 0);
    private static final BsonString DEFAULT_MECHANISM_NAME = new BsonString(AuthenticationMechanism.SCRAM_SHA_256.getMechanismName());

    DefaultAuthenticator(MongoCredentialWithCache credential) {
        super(credential);
        Assertions.isTrueArgument("unspecified authentication mechanism", credential.getAuthenticationMechanism() == null);
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticate(InternalConnection connection, ConnectionDescription connectionDescription) {
        if (connectionDescription.getServerVersion().compareTo(FOUR_ZERO) < 0) {
            getLegacyDefaultAuthenticator(connectionDescription.getServerVersion()).authenticate(connection, connectionDescription);
            return;
        }
        try {
            BsonDocument isMasterResult = CommandHelper.executeCommand("admin", createIsMasterCommand(), connection);
            getAuthenticatorFromIsMasterResult(isMasterResult, connectionDescription.getServerVersion()).authenticate(connection, connectionDescription);
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticateAsync(final InternalConnection connection, final ConnectionDescription connectionDescription, final SingleResultCallback<Void> callback) {
        if (connectionDescription.getServerVersion().compareTo(FOUR_ZERO) < 0) {
            getLegacyDefaultAuthenticator(connectionDescription.getServerVersion()).authenticateAsync(connection, connectionDescription, callback);
        } else {
            CommandHelper.executeCommandAsync("admin", createIsMasterCommand(), connection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.DefaultAuthenticator.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(BsonDocument result, Throwable t) {
                    if (t != null) {
                        callback.onResult(null, DefaultAuthenticator.this.wrapException(t));
                    } else {
                        DefaultAuthenticator.this.getAuthenticatorFromIsMasterResult(result, connectionDescription.getServerVersion()).authenticateAsync(connection, connectionDescription, callback);
                    }
                }
            });
        }
    }

    Authenticator getAuthenticatorFromIsMasterResult(BsonDocument isMasterResult, ServerVersion serverVersion) {
        if (isMasterResult.containsKey("saslSupportedMechs")) {
            BsonArray saslSupportedMechs = isMasterResult.getArray("saslSupportedMechs");
            AuthenticationMechanism mechanism = saslSupportedMechs.contains(DEFAULT_MECHANISM_NAME) ? AuthenticationMechanism.SCRAM_SHA_256 : AuthenticationMechanism.SCRAM_SHA_1;
            return new ScramShaAuthenticator(getMongoCredentialWithCache().withMechanism(mechanism));
        }
        return getLegacyDefaultAuthenticator(serverVersion);
    }

    private Authenticator getLegacyDefaultAuthenticator(ServerVersion serverVersion) {
        if (serverVersion.compareTo(THREE_ZERO) >= 0) {
            return new ScramShaAuthenticator(getMongoCredentialWithCache().withMechanism(AuthenticationMechanism.SCRAM_SHA_1));
        }
        return new NativeAuthenticator(getMongoCredentialWithCache());
    }

    private BsonDocument createIsMasterCommand() {
        BsonDocument isMasterCommandDocument = new BsonDocument("ismaster", new BsonInt32(1));
        isMasterCommandDocument.append("saslSupportedMechs", new BsonString(String.format("%s.%s", getMongoCredential().getSource(), getMongoCredential().getUserName())));
        return isMasterCommandDocument;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoException wrapException(Throwable t) {
        if (t instanceof MongoSecurityException) {
            return (MongoSecurityException) t;
        }
        if ((t instanceof MongoException) && ((MongoException) t).getCode() == 11) {
            return new MongoSecurityException(getMongoCredential(), String.format("Exception authenticating %s", getMongoCredential()), t);
        }
        return MongoException.fromThrowable(t);
    }
}
