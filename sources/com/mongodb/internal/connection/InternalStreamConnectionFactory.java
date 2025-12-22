package com.mongodb.internal.connection;

import com.mongodb.MongoCompressor;
import com.mongodb.MongoDriverInformation;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ServerId;
import com.mongodb.connection.StreamFactory;
import com.mongodb.event.CommandListener;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalStreamConnectionFactory.class */
class InternalStreamConnectionFactory implements InternalConnectionFactory {
    private final StreamFactory streamFactory;
    private final BsonDocument clientMetadataDocument;
    private final List<Authenticator> authenticators;
    private final List<MongoCompressor> compressorList;
    private final CommandListener commandListener;

    InternalStreamConnectionFactory(StreamFactory streamFactory, List<MongoCredentialWithCache> credentialList, String applicationName, MongoDriverInformation mongoDriverInformation, List<MongoCompressor> compressorList, CommandListener commandListener) {
        this.streamFactory = (StreamFactory) Assertions.notNull("streamFactory", streamFactory);
        this.compressorList = (List) Assertions.notNull("compressorList", compressorList);
        this.commandListener = commandListener;
        this.clientMetadataDocument = ClientMetadataHelper.createClientMetadataDocument(applicationName, mongoDriverInformation);
        Assertions.notNull("credentialList", credentialList);
        this.authenticators = new ArrayList(credentialList.size());
        for (MongoCredentialWithCache credential : credentialList) {
            this.authenticators.add(createAuthenticator(credential));
        }
    }

    @Override // com.mongodb.internal.connection.InternalConnectionFactory
    public InternalConnection create(ServerId serverId) {
        return new InternalStreamConnection(serverId, this.streamFactory, this.compressorList, this.commandListener, new InternalStreamConnectionInitializer(this.authenticators, this.clientMetadataDocument, this.compressorList));
    }

    private Authenticator createAuthenticator(MongoCredentialWithCache credential) {
        if (credential.getAuthenticationMechanism() == null) {
            return new DefaultAuthenticator(credential);
        }
        switch (credential.getAuthenticationMechanism()) {
            case GSSAPI:
                return new GSSAPIAuthenticator(credential);
            case PLAIN:
                return new PlainAuthenticator(credential);
            case MONGODB_X509:
                return new X509Authenticator(credential);
            case SCRAM_SHA_1:
            case SCRAM_SHA_256:
                return new ScramShaAuthenticator(credential);
            case MONGODB_CR:
                return new NativeAuthenticator(credential);
            default:
                throw new IllegalArgumentException("Unsupported authentication mechanism " + credential.getAuthenticationMechanism());
        }
    }
}
