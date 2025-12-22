package com.mongodb.internal.connection;

import ch.qos.logback.classic.ClassicConstants;
import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerVersion;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/X509Authenticator.class */
class X509Authenticator extends Authenticator {
    X509Authenticator(MongoCredentialWithCache credential) {
        super(credential);
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticate(InternalConnection connection, ConnectionDescription connectionDescription) {
        try {
            validateUserName(connectionDescription);
            BsonDocument authCommand = getAuthCommand(getMongoCredential().getUserName());
            CommandHelper.executeCommand(getMongoCredential().getSource(), authCommand, connection);
        } catch (MongoCommandException e) {
            throw new MongoSecurityException(getMongoCredential(), "Exception authenticating", e);
        }
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticateAsync(InternalConnection connection, ConnectionDescription connectionDescription, final SingleResultCallback<Void> callback) {
        try {
            validateUserName(connectionDescription);
            CommandHelper.executeCommandAsync(getMongoCredential().getSource(), getAuthCommand(getMongoCredential().getUserName()), connection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.X509Authenticator.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(BsonDocument nonceResult, Throwable t) {
                    if (t != null) {
                        callback.onResult(null, X509Authenticator.this.translateThrowable(t));
                    } else {
                        callback.onResult(null, null);
                    }
                }
            });
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    private BsonDocument getAuthCommand(String userName) {
        BsonDocument cmd = new BsonDocument();
        cmd.put("authenticate", (BsonValue) new BsonInt32(1));
        if (userName != null) {
            cmd.put(ClassicConstants.USER_MDC_KEY, (BsonValue) new BsonString(userName));
        }
        cmd.put("mechanism", (BsonValue) new BsonString(AuthenticationMechanism.MONGODB_X509.getMechanismName()));
        return cmd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Throwable translateThrowable(Throwable t) {
        if (t instanceof MongoCommandException) {
            return new MongoSecurityException(getMongoCredential(), "Exception authenticating", t);
        }
        return t;
    }

    private void validateUserName(ConnectionDescription connectionDescription) {
        if (getMongoCredential().getUserName() == null && connectionDescription.getServerVersion().compareTo(new ServerVersion(3, 4)) < 0) {
            throw new MongoSecurityException(getMongoCredential(), "User name is required for the MONGODB-X509 authentication mechanism on server versions less than 3.4");
        }
    }
}
