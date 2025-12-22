package com.mongodb.internal.connection;

import com.mongodb.MongoCredential;
import com.mongodb.MongoInternalException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Authenticator.class */
public abstract class Authenticator {
    private final MongoCredentialWithCache credential;

    abstract void authenticate(InternalConnection internalConnection, ConnectionDescription connectionDescription);

    abstract void authenticateAsync(InternalConnection internalConnection, ConnectionDescription connectionDescription, SingleResultCallback<Void> singleResultCallback);

    Authenticator(@NonNull MongoCredentialWithCache credential) {
        this.credential = credential;
    }

    @NonNull
    MongoCredentialWithCache getMongoCredentialWithCache() {
        return this.credential;
    }

    @NonNull
    MongoCredential getMongoCredential() {
        return this.credential.getCredential();
    }

    @NonNull
    String getUserNameNonNull() {
        String userName = this.credential.getCredential().getUserName();
        if (userName == null) {
            throw new MongoInternalException("User name can not be null");
        }
        return userName;
    }

    @NonNull
    char[] getPasswordNonNull() {
        char[] password = this.credential.getCredential().getPassword();
        if (password == null) {
            throw new MongoInternalException("Password can not be null");
        }
        return password;
    }

    @NonNull
    <T> T getNonNullMechanismProperty(String str, @Nullable T t) {
        T t2 = (T) this.credential.getCredential().getMechanismProperty(str, t);
        if (t2 == null) {
            throw new MongoInternalException("Mechanism property can not be null");
        }
        return t2;
    }
}
