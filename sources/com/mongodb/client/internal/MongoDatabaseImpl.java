package com.mongodb.client.internal;

import com.mongodb.Function;
import com.mongodb.MongoClientException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;
import com.mongodb.client.model.IndexOptionDefaults;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.model.changestream.ChangeStreamLevel;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.CommandReadOperation;
import com.mongodb.operation.CreateCollectionOperation;
import com.mongodb.operation.CreateViewOperation;
import com.mongodb.operation.DropDatabaseOperation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoDatabaseImpl.class */
public class MongoDatabaseImpl implements MongoDatabase {
    private final String name;
    private final ReadPreference readPreference;
    private final CodecRegistry codecRegistry;
    private final WriteConcern writeConcern;
    private final boolean retryWrites;
    private final ReadConcern readConcern;
    private final OperationExecutor executor;

    public MongoDatabaseImpl(String name, CodecRegistry codecRegistry, ReadPreference readPreference, WriteConcern writeConcern, boolean retryWrites, ReadConcern readConcern, OperationExecutor executor) {
        MongoNamespace.checkDatabaseNameValidity(name);
        this.name = (String) Assertions.notNull("name", name);
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
        this.retryWrites = retryWrites;
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
        this.executor = (OperationExecutor) Assertions.notNull("executor", executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public String getName() {
        return this.name;
    }

    @Override // com.mongodb.client.MongoDatabase
    public CodecRegistry getCodecRegistry() {
        return this.codecRegistry;
    }

    @Override // com.mongodb.client.MongoDatabase
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.client.MongoDatabase
    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    @Override // com.mongodb.client.MongoDatabase
    public ReadConcern getReadConcern() {
        return this.readConcern;
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoDatabase withCodecRegistry(CodecRegistry codecRegistry) {
        return new MongoDatabaseImpl(this.name, codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoDatabase withReadPreference(ReadPreference readPreference) {
        return new MongoDatabaseImpl(this.name, this.codecRegistry, readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoDatabase withWriteConcern(WriteConcern writeConcern) {
        return new MongoDatabaseImpl(this.name, this.codecRegistry, this.readPreference, writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoDatabase withReadConcern(ReadConcern readConcern) {
        return new MongoDatabaseImpl(this.name, this.codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoCollection<Document> getCollection(String collectionName) {
        return getCollection(collectionName, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TDocument> MongoCollection<TDocument> getCollection(String collectionName, Class<TDocument> documentClass) {
        return new MongoCollectionImpl(new MongoNamespace(this.name, collectionName), documentClass, this.codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public Document runCommand(Bson command) {
        return (Document) runCommand(command, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public Document runCommand(Bson command, ReadPreference readPreference) {
        return (Document) runCommand(command, readPreference, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> TResult runCommand(Bson bson, Class<TResult> cls) {
        return (TResult) runCommand(bson, ReadPreference.primary(), cls);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> TResult runCommand(Bson bson, ReadPreference readPreference, Class<TResult> cls) {
        return (TResult) executeCommand(null, bson, readPreference, cls);
    }

    @Override // com.mongodb.client.MongoDatabase
    public Document runCommand(ClientSession clientSession, Bson command) {
        return (Document) runCommand(clientSession, command, ReadPreference.primary(), Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public Document runCommand(ClientSession clientSession, Bson command, ReadPreference readPreference) {
        return (Document) runCommand(clientSession, command, readPreference, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> TResult runCommand(ClientSession clientSession, Bson bson, Class<TResult> cls) {
        return (TResult) runCommand(clientSession, bson, ReadPreference.primary(), cls);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> TResult runCommand(ClientSession clientSession, Bson bson, ReadPreference readPreference, Class<TResult> cls) {
        Assertions.notNull("clientSession", clientSession);
        return (TResult) executeCommand(clientSession, bson, readPreference, cls);
    }

    private <TResult> TResult executeCommand(@Nullable ClientSession clientSession, Bson bson, ReadPreference readPreference, Class<TResult> cls) {
        Assertions.notNull("readPreference", readPreference);
        if (clientSession != null && clientSession.hasActiveTransaction() && !readPreference.equals(ReadPreference.primary())) {
            throw new MongoClientException("Read preference in a transaction must be primary");
        }
        return (TResult) this.executor.execute(new CommandReadOperation(getName(), toBsonDocument(bson), this.codecRegistry.get(cls)), readPreference, this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void drop() {
        executeDrop(null);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void drop(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        executeDrop(clientSession);
    }

    private void executeDrop(@Nullable ClientSession clientSession) {
        this.executor.execute(new DropDatabaseOperation(this.name, getWriteConcern()), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoIterable<String> listCollectionNames() {
        return createListCollectionNamesIterable(null);
    }

    @Override // com.mongodb.client.MongoDatabase
    public MongoIterable<String> listCollectionNames(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        return createListCollectionNamesIterable(clientSession);
    }

    private MongoIterable<String> createListCollectionNamesIterable(@Nullable ClientSession clientSession) {
        return createListCollectionsIterable(clientSession, BsonDocument.class, true).map(new Function<BsonDocument, String>() { // from class: com.mongodb.client.internal.MongoDatabaseImpl.1
            @Override // com.mongodb.Function
            public String apply(BsonDocument result) {
                return result.getString("name").getValue();
            }
        });
    }

    @Override // com.mongodb.client.MongoDatabase
    public ListCollectionsIterable<Document> listCollections() {
        return listCollections(Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ListCollectionsIterable<TResult> listCollections(Class<TResult> resultClass) {
        return createListCollectionsIterable(null, resultClass, false);
    }

    @Override // com.mongodb.client.MongoDatabase
    public ListCollectionsIterable<Document> listCollections(ClientSession clientSession) {
        return listCollections(clientSession, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ListCollectionsIterable<TResult> listCollections(ClientSession clientSession, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createListCollectionsIterable(clientSession, resultClass, false);
    }

    private <TResult> ListCollectionsIterable<TResult> createListCollectionsIterable(@Nullable ClientSession clientSession, Class<TResult> resultClass, boolean collectionNamesOnly) {
        return new ListCollectionsIterableImpl(clientSession, this.name, collectionNamesOnly, resultClass, this.codecRegistry, ReadPreference.primary(), this.executor);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createCollection(String collectionName) {
        createCollection(collectionName, new CreateCollectionOptions());
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createCollection(String collectionName, CreateCollectionOptions createCollectionOptions) {
        executeCreateCollection(null, collectionName, createCollectionOptions);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createCollection(ClientSession clientSession, String collectionName) {
        createCollection(clientSession, collectionName, new CreateCollectionOptions());
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createCollection(ClientSession clientSession, String collectionName, CreateCollectionOptions createCollectionOptions) {
        Assertions.notNull("clientSession", clientSession);
        executeCreateCollection(clientSession, collectionName, createCollectionOptions);
    }

    private void executeCreateCollection(@Nullable ClientSession clientSession, String collectionName, CreateCollectionOptions createCollectionOptions) {
        CreateCollectionOperation operation = new CreateCollectionOperation(this.name, collectionName, this.writeConcern).collation(createCollectionOptions.getCollation()).capped(createCollectionOptions.isCapped()).sizeInBytes(createCollectionOptions.getSizeInBytes()).autoIndex(createCollectionOptions.isAutoIndex()).maxDocuments(createCollectionOptions.getMaxDocuments()).usePowerOf2Sizes(createCollectionOptions.isUsePowerOf2Sizes()).storageEngineOptions(toBsonDocument(createCollectionOptions.getStorageEngineOptions()));
        IndexOptionDefaults indexOptionDefaults = createCollectionOptions.getIndexOptionDefaults();
        Bson storageEngine = indexOptionDefaults.getStorageEngine();
        if (storageEngine != null) {
            operation.indexOptionDefaults(new BsonDocument("storageEngine", toBsonDocument(storageEngine)));
        }
        ValidationOptions validationOptions = createCollectionOptions.getValidationOptions();
        Bson validator = validationOptions.getValidator();
        if (validator != null) {
            operation.validator(toBsonDocument(validator));
        }
        if (validationOptions.getValidationLevel() != null) {
            operation.validationLevel(validationOptions.getValidationLevel());
        }
        if (validationOptions.getValidationAction() != null) {
            operation.validationAction(validationOptions.getValidationAction());
        }
        this.executor.execute(operation, this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createView(String viewName, String viewOn, List<? extends Bson> pipeline) {
        createView(viewName, viewOn, pipeline, new CreateViewOptions());
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createView(String viewName, String viewOn, List<? extends Bson> pipeline, CreateViewOptions createViewOptions) {
        executeCreateView(null, viewName, viewOn, pipeline, createViewOptions);
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createView(ClientSession clientSession, String viewName, String viewOn, List<? extends Bson> pipeline) {
        createView(clientSession, viewName, viewOn, pipeline, new CreateViewOptions());
    }

    @Override // com.mongodb.client.MongoDatabase
    public void createView(ClientSession clientSession, String viewName, String viewOn, List<? extends Bson> pipeline, CreateViewOptions createViewOptions) {
        Assertions.notNull("clientSession", clientSession);
        executeCreateView(clientSession, viewName, viewOn, pipeline, createViewOptions);
    }

    @Override // com.mongodb.client.MongoDatabase
    public ChangeStreamIterable<Document> watch() {
        return watch(Collections.emptyList());
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> resultClass) {
        return watch(Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoDatabase
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        return watch(pipeline, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return createChangeStreamIterable(null, pipeline, resultClass);
    }

    @Override // com.mongodb.client.MongoDatabase
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        return watch(clientSession, Collections.emptyList(), Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> resultClass) {
        return watch(clientSession, Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoDatabase
    public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        return watch(clientSession, pipeline, Document.class);
    }

    @Override // com.mongodb.client.MongoDatabase
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createChangeStreamIterable(clientSession, pipeline, resultClass);
    }

    private <TResult> ChangeStreamIterable<TResult> createChangeStreamIterable(@Nullable ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return new ChangeStreamIterableImpl(clientSession, this.name, this.codecRegistry, this.readPreference, this.readConcern, this.executor, pipeline, resultClass, ChangeStreamLevel.DATABASE);
    }

    private void executeCreateView(@Nullable ClientSession clientSession, String viewName, String viewOn, List<? extends Bson> pipeline, CreateViewOptions createViewOptions) {
        Assertions.notNull("createViewOptions", createViewOptions);
        this.executor.execute(new CreateViewOperation(this.name, viewName, viewOn, createBsonDocumentList(pipeline), this.writeConcern).collation(createViewOptions.getCollation()), this.readConcern, clientSession);
    }

    private List<BsonDocument> createBsonDocumentList(List<? extends Bson> pipeline) {
        Assertions.notNull("pipeline", pipeline);
        List<BsonDocument> bsonDocumentPipeline = new ArrayList<>(pipeline.size());
        for (Bson obj : pipeline) {
            if (obj == null) {
                throw new IllegalArgumentException("pipeline can not contain a null value");
            }
            bsonDocumentPipeline.add(obj.toBsonDocument(BsonDocument.class, this.codecRegistry));
        }
        return bsonDocumentPipeline;
    }

    @Nullable
    private BsonDocument toBsonDocument(@Nullable Bson document) {
        if (document == null) {
            return null;
        }
        return document.toBsonDocument(BsonDocument.class, this.codecRegistry);
    }
}
