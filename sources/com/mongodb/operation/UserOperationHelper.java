package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoCredential;
import com.mongodb.MongoInternalException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerVersion;
import com.mongodb.internal.authentication.NativeAuthenticationHelper;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.lang.NonNull;
import java.util.Collections;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/UserOperationHelper.class */
final class UserOperationHelper {
    private static final ServerVersion FOUR_ZERO = new ServerVersion(4, 0);

    static BsonDocument asCommandDocument(MongoCredential credential, ConnectionDescription connectionDescription, boolean readOnly, String commandName) {
        boolean serverDigestPassword = connectionDescription.getServerVersion().compareTo(FOUR_ZERO) >= 0;
        BsonDocument document = new BsonDocument();
        document.put(commandName, (BsonValue) new BsonString(getUserNameNonNull(credential)));
        if (serverDigestPassword) {
            document.put("pwd", (BsonValue) new BsonString(new String(getPasswordNonNull(credential))));
        } else {
            document.put("pwd", (BsonValue) new BsonString(NativeAuthenticationHelper.createAuthenticationHash(getUserNameNonNull(credential), getPasswordNonNull(credential))));
        }
        document.put("digestPassword", (BsonValue) BsonBoolean.valueOf(serverDigestPassword));
        document.put("roles", (BsonValue) new BsonArray(Collections.singletonList(new BsonString(getRoleName(credential, readOnly)))));
        return document;
    }

    private static String getRoleName(MongoCredential credential, boolean readOnly) {
        return credential.getSource().equals("admin") ? readOnly ? "readAnyDatabase" : "root" : readOnly ? "read" : "dbOwner";
    }

    static void translateUserCommandException(MongoCommandException e) {
        if (e.getErrorCode() == 100 && WriteConcernHelper.hasWriteConcernError(e.getResponse())) {
            throw WriteConcernHelper.createWriteConcernException(e.getResponse(), e.getServerAddress());
        }
        throw e;
    }

    static SingleResultCallback<Void> userCommandCallback(final SingleResultCallback<Void> wrappedCallback) {
        return new SingleResultCallback<Void>() { // from class: com.mongodb.operation.UserOperationHelper.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Void result, Throwable t) {
                if (t != null) {
                    if ((t instanceof MongoCommandException) && WriteConcernHelper.hasWriteConcernError(((MongoCommandException) t).getResponse())) {
                        wrappedCallback.onResult(null, WriteConcernHelper.createWriteConcernException(((MongoCommandException) t).getResponse(), ((MongoCommandException) t).getServerAddress()));
                        return;
                    } else {
                        wrappedCallback.onResult(null, t);
                        return;
                    }
                }
                wrappedCallback.onResult(null, null);
            }
        };
    }

    @NonNull
    private static String getUserNameNonNull(MongoCredential credential) {
        String userName = credential.getUserName();
        if (userName == null) {
            throw new MongoInternalException("User name can not be null");
        }
        return userName;
    }

    @NonNull
    private static char[] getPasswordNonNull(MongoCredential credential) {
        char[] password = credential.getPassword();
        if (password == null) {
            throw new MongoInternalException("Password can not be null");
        }
        return password;
    }

    private UserOperationHelper() {
    }
}
