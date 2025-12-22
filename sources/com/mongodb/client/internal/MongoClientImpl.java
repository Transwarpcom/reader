package com.mongodb.client.internal;

import com.mongodb.ClientSessionOptions;
import com.mongodb.Function;
import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoDriverInformation;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.changestream.ChangeStreamLevel;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.DefaultClusterFactory;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SocketStreamFactory;
import com.mongodb.connection.StreamFactory;
import com.mongodb.connection.StreamFactoryFactory;
import com.mongodb.internal.event.EventListenerHelper;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoClientImpl.class */
public final class MongoClientImpl implements MongoClient {
    private final MongoClientSettings settings;
    private final MongoClientDelegate delegate;

    public MongoClientImpl(MongoClientSettings settings, @Nullable MongoDriverInformation mongoDriverInformation) {
        this(createCluster(settings, mongoDriverInformation), settings, null);
    }

    public MongoClientImpl(Cluster cluster, MongoClientSettings settings, @Nullable OperationExecutor operationExecutor) {
        this.settings = (MongoClientSettings) Assertions.notNull("settings", settings);
        this.delegate = new MongoClientDelegate((Cluster) Assertions.notNull("cluster", cluster), Collections.singletonList(settings.getCredential()), this, operationExecutor);
    }

    @Override // com.mongodb.client.MongoClient
    public MongoDatabase getDatabase(String databaseName) {
        return new MongoDatabaseImpl(databaseName, this.settings.getCodecRegistry(), this.settings.getReadPreference(), this.settings.getWriteConcern(), this.settings.getRetryWrites(), this.settings.getReadConcern(), this.delegate.getOperationExecutor());
    }

    @Override // com.mongodb.client.MongoClient
    public MongoIterable<String> listDatabaseNames() {
        return createListDatabaseNamesIterable(null);
    }

    @Override // com.mongodb.client.MongoClient
    public MongoIterable<String> listDatabaseNames(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        return createListDatabaseNamesIterable(clientSession);
    }

    @Override // com.mongodb.client.MongoClient
    public ListDatabasesIterable<Document> listDatabases() {
        return listDatabases(Document.class);
    }

    @Override // com.mongodb.client.MongoClient
    public <T> ListDatabasesIterable<T> listDatabases(Class<T> clazz) {
        return createListDatabasesIterable(null, clazz);
    }

    @Override // com.mongodb.client.MongoClient
    public ListDatabasesIterable<Document> listDatabases(ClientSession clientSession) {
        return listDatabases(clientSession, Document.class);
    }

    @Override // com.mongodb.client.MongoClient
    public <T> ListDatabasesIterable<T> listDatabases(ClientSession clientSession, Class<T> clazz) {
        Assertions.notNull("clientSession", clientSession);
        return createListDatabasesIterable(clientSession, clazz);
    }

    @Override // com.mongodb.client.MongoClient
    public ClientSession startSession() {
        return startSession(ClientSessionOptions.builder().defaultTransactionOptions(TransactionOptions.builder().readConcern(this.settings.getReadConcern()).writeConcern(this.settings.getWriteConcern()).build()).build());
    }

    @Override // com.mongodb.client.MongoClient
    public ClientSession startSession(ClientSessionOptions options) {
        ClientSession clientSession = this.delegate.createClientSession((ClientSessionOptions) Assertions.notNull("options", options), this.settings.getReadConcern(), this.settings.getWriteConcern(), this.settings.getReadPreference());
        if (clientSession == null) {
            throw new MongoClientException("Sessions are not supported by the MongoDB cluster to which this client is connected");
        }
        return clientSession;
    }

    @Override // com.mongodb.client.MongoClient
    public void close() {
        this.delegate.close();
    }

    @Override // com.mongodb.client.MongoClient
    public ChangeStreamIterable<Document> watch() {
        return watch(Collections.emptyList());
    }

    @Override // com.mongodb.client.MongoClient
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> resultClass) {
        return watch(Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoClient
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        return watch(pipeline, Document.class);
    }

    @Override // com.mongodb.client.MongoClient
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return createChangeStreamIterable(null, pipeline, resultClass);
    }

    @Override // com.mongodb.client.MongoClient
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        return watch(clientSession, Collections.emptyList(), Document.class);
    }

    @Override // com.mongodb.client.MongoClient
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> resultClass) {
        return watch(clientSession, Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoClient
    public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        return watch(clientSession, pipeline, Document.class);
    }

    @Override // com.mongodb.client.MongoClient
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createChangeStreamIterable(clientSession, pipeline, resultClass);
    }

    private <TResult> ChangeStreamIterable<TResult> createChangeStreamIterable(@Nullable ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return new ChangeStreamIterableImpl(clientSession, "admin", this.settings.getCodecRegistry(), this.settings.getReadPreference(), this.settings.getReadConcern(), this.delegate.getOperationExecutor(), pipeline, resultClass, ChangeStreamLevel.CLIENT);
    }

    public Cluster getCluster() {
        return this.delegate.getCluster();
    }

    private static Cluster createCluster(MongoClientSettings settings, @Nullable MongoDriverInformation mongoDriverInformation) {
        Assertions.notNull("settings", settings);
        List<MongoCredential> credentialList = settings.getCredential() != null ? Collections.singletonList(settings.getCredential()) : Collections.emptyList();
        return new DefaultClusterFactory().createCluster(settings.getClusterSettings(), settings.getServerSettings(), settings.getConnectionPoolSettings(), getStreamFactory(settings, false), getStreamFactory(settings, true), credentialList, EventListenerHelper.getCommandListener(settings.getCommandListeners()), settings.getApplicationName(), mongoDriverInformation, settings.getCompressorList());
    }

    private static StreamFactory getStreamFactory(MongoClientSettings settings, boolean isHeartbeat) {
        StreamFactoryFactory streamFactoryFactory = settings.getStreamFactoryFactory();
        SocketSettings socketSettings = isHeartbeat ? settings.getHeartbeatSocketSettings() : settings.getSocketSettings();
        if (streamFactoryFactory == null) {
            return new SocketStreamFactory(socketSettings, settings.getSslSettings());
        }
        return streamFactoryFactory.create(socketSettings, settings.getSslSettings());
    }

    private <T> ListDatabasesIterable<T> createListDatabasesIterable(@Nullable ClientSession clientSession, Class<T> clazz) {
        return new ListDatabasesIterableImpl(clientSession, clazz, this.settings.getCodecRegistry(), ReadPreference.primary(), this.delegate.getOperationExecutor());
    }

    private MongoIterable<String> createListDatabaseNamesIterable(@Nullable ClientSession clientSession) {
        return createListDatabasesIterable(clientSession, BsonDocument.class).nameOnly(true).map(new Function<BsonDocument, String>() { // from class: com.mongodb.client.internal.MongoClientImpl.1
            @Override // com.mongodb.Function
            public String apply(BsonDocument result) {
                return result.getString("name").getValue();
            }
        });
    }
}
