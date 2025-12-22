package com.mongodb.internal.connection;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.internal.authentication.NativeAuthenticationHelper;
import org.bson.BsonDocument;
import org.bson.BsonString;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/NativeAuthenticator.class */
class NativeAuthenticator extends Authenticator {
    NativeAuthenticator(MongoCredentialWithCache credential) {
        super(credential);
    }

    @Override // com.mongodb.internal.connection.Authenticator
    public void authenticate(InternalConnection connection, ConnectionDescription connectionDescription) {
        try {
            BsonDocument nonceResponse = CommandHelper.executeCommand(getMongoCredential().getSource(), NativeAuthenticationHelper.getNonceCommand(), connection);
            BsonDocument authCommand = NativeAuthenticationHelper.getAuthCommand(getUserNameNonNull(), getPasswordNonNull(), ((BsonString) nonceResponse.get((Object) "nonce")).getValue());
            CommandHelper.executeCommand(getMongoCredential().getSource(), authCommand, connection);
        } catch (MongoCommandException e) {
            throw new MongoSecurityException(getMongoCredential(), "Exception authenticating", e);
        }
    }

    @Override // com.mongodb.internal.connection.Authenticator
    void authenticateAsync(final InternalConnection connection, ConnectionDescription connectionDescription, final SingleResultCallback<Void> callback) {
        CommandHelper.executeCommandAsync(getMongoCredential().getSource(), NativeAuthenticationHelper.getNonceCommand(), connection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.NativeAuthenticator.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(BsonDocument nonceResult, Throwable t) {
                if (t != null) {
                    callback.onResult(null, NativeAuthenticator.this.translateThrowable(t));
                } else {
                    CommandHelper.executeCommandAsync(NativeAuthenticator.this.getMongoCredential().getSource(), NativeAuthenticationHelper.getAuthCommand(NativeAuthenticator.this.getUserNameNonNull(), NativeAuthenticator.this.getPasswordNonNull(), ((BsonString) nonceResult.get("nonce")).getValue()), connection, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.NativeAuthenticator.1.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(BsonDocument result, Throwable t2) {
                            if (t2 != null) {
                                callback.onResult(null, NativeAuthenticator.this.translateThrowable(t2));
                            } else {
                                callback.onResult(null, null);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Throwable translateThrowable(Throwable t) {
        if (t instanceof MongoCommandException) {
            return new MongoSecurityException(getMongoCredential(), "Exception authenticating", t);
        }
        return t;
    }
}
