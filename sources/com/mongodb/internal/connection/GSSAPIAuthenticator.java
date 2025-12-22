package com.mongodb.internal.connection;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoSecurityException;
import com.mongodb.ServerAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/GSSAPIAuthenticator.class */
class GSSAPIAuthenticator extends SaslAuthenticator {
    private static final String GSSAPI_MECHANISM_NAME = "GSSAPI";
    private static final String GSSAPI_OID = "1.2.840.113554.1.2.2";
    private static final String SERVICE_NAME_DEFAULT_VALUE = "mongodb";
    private static final Boolean CANONICALIZE_HOST_NAME_DEFAULT_VALUE = false;

    GSSAPIAuthenticator(MongoCredentialWithCache credential) {
        super(credential);
        if (getMongoCredential().getAuthenticationMechanism() != AuthenticationMechanism.GSSAPI) {
            throw new MongoException("Incorrect mechanism: " + getMongoCredential().getMechanism());
        }
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    public String getMechanismName() {
        return GSSAPI_MECHANISM_NAME;
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    protected SaslClient createSaslClient(ServerAddress serverAddress) {
        MongoCredential credential = getMongoCredential();
        try {
            Map<String, Object> saslClientProperties = (Map) credential.getMechanismProperty(MongoCredential.JAVA_SASL_CLIENT_PROPERTIES_KEY, null);
            if (saslClientProperties == null) {
                saslClientProperties = new HashMap<>();
                saslClientProperties.put("javax.security.sasl.maxbuffer", "0");
                saslClientProperties.put("javax.security.sasl.credentials", getGSSCredential(credential.getUserName()));
            }
            SaslClient saslClient = Sasl.createSaslClient(new String[]{AuthenticationMechanism.GSSAPI.getMechanismName()}, credential.getUserName(), (String) credential.getMechanismProperty(MongoCredential.SERVICE_NAME_KEY, SERVICE_NAME_DEFAULT_VALUE), getHostName(serverAddress), saslClientProperties, (CallbackHandler) null);
            if (saslClient == null) {
                throw new MongoSecurityException(credential, String.format("No platform support for %s mechanism", AuthenticationMechanism.GSSAPI));
            }
            return saslClient;
        } catch (SaslException e) {
            throw new MongoSecurityException(credential, "Exception initializing SASL client", e);
        } catch (UnknownHostException e2) {
            throw new MongoSecurityException(credential, "Unable to canonicalize host name + " + serverAddress);
        } catch (GSSException e3) {
            throw new MongoSecurityException(credential, "Exception initializing GSSAPI credentials", e3);
        }
    }

    private GSSCredential getGSSCredential(String userName) throws GSSException {
        Oid krb5Mechanism = new Oid(GSSAPI_OID);
        GSSManager manager = GSSManager.getInstance();
        GSSName name = manager.createName(userName, GSSName.NT_USER_NAME);
        return manager.createCredential(name, Integer.MAX_VALUE, krb5Mechanism, 1);
    }

    private String getHostName(ServerAddress serverAddress) throws UnknownHostException {
        if (((Boolean) getNonNullMechanismProperty(MongoCredential.CANONICALIZE_HOST_NAME_KEY, CANONICALIZE_HOST_NAME_DEFAULT_VALUE)).booleanValue()) {
            return InetAddress.getByName(serverAddress.getHost()).getCanonicalHostName();
        }
        return serverAddress.getHost();
    }
}
