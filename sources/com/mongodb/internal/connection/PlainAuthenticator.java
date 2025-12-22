package com.mongodb.internal.connection;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCredential;
import com.mongodb.MongoSecurityException;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import java.io.IOException;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/PlainAuthenticator.class */
class PlainAuthenticator extends SaslAuthenticator {
    private static final String DEFAULT_PROTOCOL = "mongodb";

    PlainAuthenticator(MongoCredentialWithCache credential) {
        super(credential);
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    public String getMechanismName() {
        return AuthenticationMechanism.PLAIN.getMechanismName();
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    protected SaslClient createSaslClient(ServerAddress serverAddress) {
        final MongoCredential credential = getMongoCredential();
        Assertions.isTrue("mechanism is PLAIN", credential.getAuthenticationMechanism() == AuthenticationMechanism.PLAIN);
        try {
            return Sasl.createSaslClient(new String[]{AuthenticationMechanism.PLAIN.getMechanismName()}, credential.getUserName(), DEFAULT_PROTOCOL, serverAddress.getHost(), (Map) null, new CallbackHandler() { // from class: com.mongodb.internal.connection.PlainAuthenticator.1
                @Override // javax.security.auth.callback.CallbackHandler
                public void handle(Callback[] callbacks) throws UnsupportedCallbackException, IOException {
                    for (Callback callback : callbacks) {
                        if (callback instanceof PasswordCallback) {
                            ((PasswordCallback) callback).setPassword(credential.getPassword());
                        } else if (callback instanceof NameCallback) {
                            ((NameCallback) callback).setName(credential.getUserName());
                        }
                    }
                }
            });
        } catch (SaslException e) {
            throw new MongoSecurityException(credential, "Exception initializing SASL client", e);
        }
    }
}
