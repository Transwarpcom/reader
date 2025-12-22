package com.mongodb.client.internal;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoNamespace;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteConcernResult;
import com.mongodb.WriteError;
import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.CreateIndexOptions;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.DropIndexOptions;
import com.mongodb.client.model.EstimatedDocumentCountOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.RenameCollectionOptions;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.model.changestream.ChangeStreamLevel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.client.model.CountOptionsHelper;
import com.mongodb.internal.client.model.CountStrategy;
import com.mongodb.internal.operation.IndexHelper;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.RenameCollectionOperation;
import com.mongodb.operation.WriteOperation;
import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoCollectionImpl.class */
class MongoCollectionImpl<TDocument> implements MongoCollection<TDocument> {
    private final MongoNamespace namespace;
    private final Class<TDocument> documentClass;
    private final ReadPreference readPreference;
    private final CodecRegistry codecRegistry;
    private final WriteConcern writeConcern;
    private final boolean retryWrites;
    private final ReadConcern readConcern;
    private final SyncOperations<TDocument> operations;
    private final OperationExecutor executor;

    MongoCollectionImpl(MongoNamespace namespace, Class<TDocument> documentClass, CodecRegistry codecRegistry, ReadPreference readPreference, WriteConcern writeConcern, boolean retryWrites, ReadConcern readConcern, OperationExecutor executor) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.documentClass = (Class) Assertions.notNull("documentClass", documentClass);
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
        this.retryWrites = retryWrites;
        this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
        this.executor = (OperationExecutor) Assertions.notNull("executor", executor);
        this.operations = new SyncOperations<>(namespace, documentClass, readPreference, codecRegistry, writeConcern, retryWrites);
    }

    @Override // com.mongodb.client.MongoCollection
    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    @Override // com.mongodb.client.MongoCollection
    public Class<TDocument> getDocumentClass() {
        return this.documentClass;
    }

    @Override // com.mongodb.client.MongoCollection
    public CodecRegistry getCodecRegistry() {
        return this.codecRegistry;
    }

    @Override // com.mongodb.client.MongoCollection
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.client.MongoCollection
    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    @Override // com.mongodb.client.MongoCollection
    public ReadConcern getReadConcern() {
        return this.readConcern;
    }

    @Override // com.mongodb.client.MongoCollection
    public <NewTDocument> MongoCollection<NewTDocument> withDocumentClass(Class<NewTDocument> clazz) {
        return new MongoCollectionImpl(this.namespace, clazz, this.codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    public MongoCollection<TDocument> withCodecRegistry(CodecRegistry codecRegistry) {
        return new MongoCollectionImpl(this.namespace, this.documentClass, codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    public MongoCollection<TDocument> withReadPreference(ReadPreference readPreference) {
        return new MongoCollectionImpl(this.namespace, this.documentClass, this.codecRegistry, readPreference, this.writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    public MongoCollection<TDocument> withWriteConcern(WriteConcern writeConcern) {
        return new MongoCollectionImpl(this.namespace, this.documentClass, this.codecRegistry, this.readPreference, writeConcern, this.retryWrites, this.readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    public MongoCollection<TDocument> withReadConcern(ReadConcern readConcern) {
        return new MongoCollectionImpl(this.namespace, this.documentClass, this.codecRegistry, this.readPreference, this.writeConcern, this.retryWrites, readConcern, this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count() {
        return count(new BsonDocument(), new CountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count(Bson filter) {
        return count(filter, new CountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count(Bson filter, CountOptions options) {
        return executeCount(null, filter, options, CountStrategy.COMMAND);
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count(ClientSession clientSession) {
        return count(clientSession, new BsonDocument());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count(ClientSession clientSession, Bson filter) {
        return count(clientSession, filter, new CountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public long count(ClientSession clientSession, Bson filter, CountOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeCount(clientSession, filter, options, CountStrategy.COMMAND);
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments() {
        return countDocuments(new BsonDocument());
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments(Bson filter) {
        return countDocuments(filter, new CountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments(Bson filter, CountOptions options) {
        return executeCount(null, filter, options, CountStrategy.AGGREGATE);
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments(ClientSession clientSession) {
        return countDocuments(clientSession, new BsonDocument());
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments(ClientSession clientSession, Bson filter) {
        return countDocuments(clientSession, filter, new CountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public long countDocuments(ClientSession clientSession, Bson filter, CountOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeCount(clientSession, filter, options, CountStrategy.AGGREGATE);
    }

    @Override // com.mongodb.client.MongoCollection
    public long estimatedDocumentCount() {
        return estimatedDocumentCount(new EstimatedDocumentCountOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public long estimatedDocumentCount(EstimatedDocumentCountOptions options) {
        return executeCount(null, new BsonDocument(), CountOptionsHelper.fromEstimatedDocumentCountOptions(options), CountStrategy.COMMAND);
    }

    private long executeCount(@Nullable ClientSession clientSession, Bson filter, CountOptions options, CountStrategy countStrategy) {
        return ((Long) this.executor.execute(this.operations.count(filter, options, countStrategy), this.readPreference, this.readConcern, clientSession)).longValue();
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> DistinctIterable<TResult> distinct(String fieldName, Class<TResult> resultClass) {
        return distinct(fieldName, new BsonDocument(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> DistinctIterable<TResult> distinct(String fieldName, Bson filter, Class<TResult> resultClass) {
        return createDistinctIterable(null, fieldName, filter, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName, Class<TResult> resultClass) {
        return distinct(clientSession, fieldName, new BsonDocument(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName, Bson filter, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createDistinctIterable(clientSession, fieldName, filter, resultClass);
    }

    private <TResult> DistinctIterable<TResult> createDistinctIterable(@Nullable ClientSession clientSession, String fieldName, Bson filter, Class<TResult> resultClass) {
        return new DistinctIterableImpl(clientSession, this.namespace, this.documentClass, resultClass, this.codecRegistry, this.readPreference, this.readConcern, this.executor, fieldName, filter);
    }

    @Override // com.mongodb.client.MongoCollection
    public FindIterable<TDocument> find() {
        return (FindIterable<TDocument>) find(new BsonDocument(), this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> FindIterable<TResult> find(Class<TResult> resultClass) {
        return find(new BsonDocument(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public FindIterable<TDocument> find(Bson bson) {
        return (FindIterable<TDocument>) find(bson, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> FindIterable<TResult> find(Bson filter, Class<TResult> resultClass) {
        return createFindIterable(null, filter, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public FindIterable<TDocument> find(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        return (FindIterable<TDocument>) find(clientSession, new BsonDocument(), this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> FindIterable<TResult> find(ClientSession clientSession, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return find(clientSession, new BsonDocument(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public FindIterable<TDocument> find(ClientSession clientSession, Bson bson) {
        Assertions.notNull("clientSession", clientSession);
        return (FindIterable<TDocument>) find(clientSession, bson, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> FindIterable<TResult> find(ClientSession clientSession, Bson filter, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createFindIterable(clientSession, filter, resultClass);
    }

    private <TResult> FindIterable<TResult> createFindIterable(@Nullable ClientSession clientSession, Bson filter, Class<TResult> resultClass) {
        return new FindIterableImpl(clientSession, this.namespace, this.documentClass, resultClass, this.codecRegistry, this.readPreference, this.readConcern, this.executor, filter);
    }

    @Override // com.mongodb.client.MongoCollection
    public AggregateIterable<TDocument> aggregate(List<? extends Bson> list) {
        return (AggregateIterable<TDocument>) aggregate(list, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> AggregateIterable<TResult> aggregate(List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return createAggregateIterable(null, pipeline, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public AggregateIterable<TDocument> aggregate(ClientSession clientSession, List<? extends Bson> list) {
        return (AggregateIterable<TDocument>) aggregate(clientSession, list, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> AggregateIterable<TResult> aggregate(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createAggregateIterable(clientSession, pipeline, resultClass);
    }

    private <TResult> AggregateIterable<TResult> createAggregateIterable(@Nullable ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return new AggregateIterableImpl(clientSession, this.namespace, this.documentClass, resultClass, this.codecRegistry, this.readPreference, this.readConcern, this.writeConcern, this.executor, pipeline);
    }

    @Override // com.mongodb.client.MongoCollection
    public ChangeStreamIterable<TDocument> watch() {
        return watch(Collections.emptyList());
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> resultClass) {
        return watch(Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public ChangeStreamIterable<TDocument> watch(List<? extends Bson> list) {
        return (ChangeStreamIterable<TDocument>) watch(list, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return createChangeStreamIterable(null, pipeline, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public ChangeStreamIterable<TDocument> watch(ClientSession clientSession) {
        return (ChangeStreamIterable<TDocument>) watch(clientSession, Collections.emptyList(), this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> resultClass) {
        return watch(clientSession, Collections.emptyList(), resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public ChangeStreamIterable<TDocument> watch(ClientSession clientSession, List<? extends Bson> list) {
        return (ChangeStreamIterable<TDocument>) watch(clientSession, list, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createChangeStreamIterable(clientSession, pipeline, resultClass);
    }

    private <TResult> ChangeStreamIterable<TResult> createChangeStreamIterable(@Nullable ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> resultClass) {
        return new ChangeStreamIterableImpl(clientSession, this.namespace, this.codecRegistry, this.readPreference, this.readConcern, this.executor, pipeline, resultClass, ChangeStreamLevel.COLLECTION);
    }

    @Override // com.mongodb.client.MongoCollection
    public MapReduceIterable<TDocument> mapReduce(String str, String str2) {
        return (MapReduceIterable<TDocument>) mapReduce(str, str2, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> MapReduceIterable<TResult> mapReduce(String mapFunction, String reduceFunction, Class<TResult> resultClass) {
        return createMapReduceIterable(null, mapFunction, reduceFunction, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public MapReduceIterable<TDocument> mapReduce(ClientSession clientSession, String str, String str2) {
        return (MapReduceIterable<TDocument>) mapReduce(clientSession, str, str2, this.documentClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> MapReduceIterable<TResult> mapReduce(ClientSession clientSession, String mapFunction, String reduceFunction, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createMapReduceIterable(clientSession, mapFunction, reduceFunction, resultClass);
    }

    private <TResult> MapReduceIterable<TResult> createMapReduceIterable(@Nullable ClientSession clientSession, String mapFunction, String reduceFunction, Class<TResult> resultClass) {
        return new MapReduceIterableImpl(clientSession, this.namespace, this.documentClass, resultClass, this.codecRegistry, this.readPreference, this.readConcern, this.writeConcern, this.executor, mapFunction, reduceFunction);
    }

    @Override // com.mongodb.client.MongoCollection
    public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends TDocument>> requests) {
        return bulkWrite(requests, new BulkWriteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends TDocument>> requests, BulkWriteOptions options) {
        return executeBulkWrite(null, requests, options);
    }

    @Override // com.mongodb.client.MongoCollection
    public BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> requests) {
        return bulkWrite(clientSession, requests, new BulkWriteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> requests, BulkWriteOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeBulkWrite(clientSession, requests, options);
    }

    private BulkWriteResult executeBulkWrite(@Nullable ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> requests, BulkWriteOptions options) {
        Assertions.notNull("requests", requests);
        return (BulkWriteResult) this.executor.execute(this.operations.bulkWrite(requests, options), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertOne(TDocument document) {
        insertOne((MongoCollectionImpl<TDocument>) document, new InsertOneOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertOne(TDocument document, InsertOneOptions options) {
        Assertions.notNull("document", document);
        executeInsertOne(null, document, options);
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertOne(ClientSession clientSession, TDocument document) {
        insertOne(clientSession, document, new InsertOneOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertOne(ClientSession clientSession, TDocument document, InsertOneOptions options) {
        Assertions.notNull("clientSession", clientSession);
        Assertions.notNull("document", document);
        executeInsertOne(clientSession, document, options);
    }

    private void executeInsertOne(@Nullable ClientSession clientSession, TDocument document, InsertOneOptions options) {
        executeSingleWriteRequest(clientSession, this.operations.insertOne(document, options), WriteRequest.Type.INSERT);
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertMany(List<? extends TDocument> documents) {
        insertMany(documents, new InsertManyOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertMany(List<? extends TDocument> documents, InsertManyOptions options) {
        executeInsertMany(null, documents, options);
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertMany(ClientSession clientSession, List<? extends TDocument> documents) {
        insertMany(clientSession, documents, new InsertManyOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void insertMany(ClientSession clientSession, List<? extends TDocument> documents, InsertManyOptions options) {
        Assertions.notNull("clientSession", clientSession);
        executeInsertMany(clientSession, documents, options);
    }

    private void executeInsertMany(@Nullable ClientSession clientSession, List<? extends TDocument> documents, InsertManyOptions options) {
        this.executor.execute(this.operations.insertMany(documents, options), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteOne(Bson filter) {
        return deleteOne(filter, new DeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteOne(Bson filter, DeleteOptions options) {
        return executeDelete(null, filter, options, false);
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteOne(ClientSession clientSession, Bson filter) {
        return deleteOne(clientSession, filter, new DeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteOne(ClientSession clientSession, Bson filter, DeleteOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeDelete(clientSession, filter, options, false);
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteMany(Bson filter) {
        return deleteMany(filter, new DeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteMany(Bson filter, DeleteOptions options) {
        return executeDelete(null, filter, options, true);
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteMany(ClientSession clientSession, Bson filter) {
        return deleteMany(clientSession, filter, new DeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public DeleteResult deleteMany(ClientSession clientSession, Bson filter, DeleteOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeDelete(clientSession, filter, options, true);
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult replaceOne(Bson filter, TDocument replacement) {
        return replaceOne(filter, (Bson) replacement, new ReplaceOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public UpdateResult replaceOne(Bson filter, TDocument replacement, UpdateOptions updateOptions) {
        return replaceOne(filter, (Bson) replacement, ReplaceOptions.createReplaceOptions(updateOptions));
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult replaceOne(Bson filter, TDocument replacement, ReplaceOptions replaceOptions) {
        return executeReplaceOne(null, filter, replacement, replaceOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, TDocument replacement) {
        return replaceOne(clientSession, filter, (Bson) replacement, new ReplaceOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Deprecated
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, TDocument replacement, UpdateOptions updateOptions) {
        return replaceOne(clientSession, filter, (Bson) replacement, ReplaceOptions.createReplaceOptions(updateOptions));
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, TDocument replacement, ReplaceOptions replaceOptions) {
        Assertions.notNull("clientSession", clientSession);
        return executeReplaceOne(clientSession, filter, replacement, replaceOptions);
    }

    private UpdateResult executeReplaceOne(@Nullable ClientSession clientSession, Bson filter, TDocument replacement, ReplaceOptions replaceOptions) {
        return toUpdateResult(executeSingleWriteRequest(clientSession, this.operations.replaceOne(filter, replacement, replaceOptions), WriteRequest.Type.REPLACE));
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateOne(Bson filter, Bson update) {
        return updateOne(filter, update, new UpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateOne(Bson filter, Bson update, UpdateOptions updateOptions) {
        return executeUpdate(null, filter, update, updateOptions, false);
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update) {
        return updateOne(clientSession, filter, update, new UpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
        Assertions.notNull("clientSession", clientSession);
        return executeUpdate(clientSession, filter, update, updateOptions, false);
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateMany(Bson filter, Bson update) {
        return updateMany(filter, update, new UpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateMany(Bson filter, Bson update, UpdateOptions updateOptions) {
        return executeUpdate(null, filter, update, updateOptions, true);
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update) {
        return updateMany(clientSession, filter, update, new UpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
        Assertions.notNull("clientSession", clientSession);
        return executeUpdate(clientSession, filter, update, updateOptions, true);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndDelete(Bson filter) {
        return findOneAndDelete(filter, new FindOneAndDeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndDelete(Bson filter, FindOneAndDeleteOptions options) {
        return executeFindOneAndDelete(null, filter, options);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndDelete(ClientSession clientSession, Bson filter) {
        return findOneAndDelete(clientSession, filter, new FindOneAndDeleteOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndDelete(ClientSession clientSession, Bson filter, FindOneAndDeleteOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeFindOneAndDelete(clientSession, filter, options);
    }

    @Nullable
    private TDocument executeFindOneAndDelete(@Nullable ClientSession clientSession, Bson bson, FindOneAndDeleteOptions findOneAndDeleteOptions) {
        return (TDocument) this.executor.execute(this.operations.findOneAndDelete(bson, findOneAndDeleteOptions), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndReplace(Bson filter, TDocument replacement) {
        return findOneAndReplace(filter, (Bson) replacement, new FindOneAndReplaceOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndReplace(Bson filter, TDocument replacement, FindOneAndReplaceOptions options) {
        return executeFindOneAndReplace(null, filter, replacement, options);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndReplace(ClientSession clientSession, Bson filter, TDocument replacement) {
        return findOneAndReplace(clientSession, filter, replacement, new FindOneAndReplaceOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndReplace(ClientSession clientSession, Bson filter, TDocument replacement, FindOneAndReplaceOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeFindOneAndReplace(clientSession, filter, replacement, options);
    }

    @Nullable
    private TDocument executeFindOneAndReplace(@Nullable ClientSession clientSession, Bson bson, TDocument tdocument, FindOneAndReplaceOptions findOneAndReplaceOptions) {
        return (TDocument) this.executor.execute(this.operations.findOneAndReplace(bson, tdocument, findOneAndReplaceOptions), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndUpdate(Bson filter, Bson update) {
        return findOneAndUpdate(filter, update, new FindOneAndUpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndUpdate(Bson filter, Bson update, FindOneAndUpdateOptions options) {
        return executeFindOneAndUpdate(null, filter, update, options);
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update) {
        return findOneAndUpdate(clientSession, filter, update, new FindOneAndUpdateOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    @Nullable
    public TDocument findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update, FindOneAndUpdateOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return executeFindOneAndUpdate(clientSession, filter, update, options);
    }

    @Nullable
    private TDocument executeFindOneAndUpdate(@Nullable ClientSession clientSession, Bson bson, Bson bson2, FindOneAndUpdateOptions findOneAndUpdateOptions) {
        return (TDocument) this.executor.execute(this.operations.findOneAndUpdate(bson, bson2, findOneAndUpdateOptions), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    public void drop() {
        executeDrop(null);
    }

    @Override // com.mongodb.client.MongoCollection
    public void drop(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        executeDrop(clientSession);
    }

    private void executeDrop(@Nullable ClientSession clientSession) {
        this.executor.execute(this.operations.dropCollection(), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    public String createIndex(Bson keys) {
        return createIndex(keys, new IndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public String createIndex(Bson keys, IndexOptions indexOptions) {
        return createIndexes(Collections.singletonList(new IndexModel(keys, indexOptions))).get(0);
    }

    @Override // com.mongodb.client.MongoCollection
    public String createIndex(ClientSession clientSession, Bson keys) {
        return createIndex(clientSession, keys, new IndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public String createIndex(ClientSession clientSession, Bson keys, IndexOptions indexOptions) {
        return createIndexes(clientSession, Collections.singletonList(new IndexModel(keys, indexOptions))).get(0);
    }

    @Override // com.mongodb.client.MongoCollection
    public List<String> createIndexes(List<IndexModel> indexes) {
        return createIndexes(indexes, new CreateIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public List<String> createIndexes(List<IndexModel> indexes, CreateIndexOptions createIndexOptions) {
        return executeCreateIndexes(null, indexes, createIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes) {
        return createIndexes(clientSession, indexes, new CreateIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes, CreateIndexOptions createIndexOptions) {
        Assertions.notNull("clientSession", clientSession);
        return executeCreateIndexes(clientSession, indexes, createIndexOptions);
    }

    private List<String> executeCreateIndexes(@Nullable ClientSession clientSession, List<IndexModel> indexes, CreateIndexOptions createIndexOptions) {
        this.executor.execute(this.operations.createIndexes(indexes, createIndexOptions), this.readConcern, clientSession);
        return IndexHelper.getIndexNames(indexes, this.codecRegistry);
    }

    @Override // com.mongodb.client.MongoCollection
    public ListIndexesIterable<Document> listIndexes() {
        return listIndexes(Document.class);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ListIndexesIterable<TResult> listIndexes(Class<TResult> resultClass) {
        return createListIndexesIterable(null, resultClass);
    }

    @Override // com.mongodb.client.MongoCollection
    public ListIndexesIterable<Document> listIndexes(ClientSession clientSession) {
        return listIndexes(clientSession, Document.class);
    }

    @Override // com.mongodb.client.MongoCollection
    public <TResult> ListIndexesIterable<TResult> listIndexes(ClientSession clientSession, Class<TResult> resultClass) {
        Assertions.notNull("clientSession", clientSession);
        return createListIndexesIterable(clientSession, resultClass);
    }

    private <TResult> ListIndexesIterable<TResult> createListIndexesIterable(@Nullable ClientSession clientSession, Class<TResult> resultClass) {
        return new ListIndexesIterableImpl(clientSession, getNamespace(), resultClass, this.codecRegistry, ReadPreference.primary(), this.executor);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(String indexName) {
        dropIndex(indexName, new DropIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(String indexName, DropIndexOptions dropIndexOptions) {
        executeDropIndex((ClientSession) null, indexName, dropIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(Bson keys) {
        dropIndex(keys, new DropIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(Bson keys, DropIndexOptions dropIndexOptions) {
        executeDropIndex((ClientSession) null, keys, dropIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(ClientSession clientSession, String indexName) {
        dropIndex(clientSession, indexName, new DropIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(ClientSession clientSession, Bson keys) {
        dropIndex(clientSession, keys, new DropIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(ClientSession clientSession, String indexName, DropIndexOptions dropIndexOptions) {
        Assertions.notNull("clientSession", clientSession);
        executeDropIndex(clientSession, indexName, dropIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndex(ClientSession clientSession, Bson keys, DropIndexOptions dropIndexOptions) {
        Assertions.notNull("clientSession", clientSession);
        executeDropIndex(clientSession, keys, dropIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndexes() {
        dropIndex("*");
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndexes(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        executeDropIndex(clientSession, "*", new DropIndexOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndexes(DropIndexOptions dropIndexOptions) {
        dropIndex("*", dropIndexOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void dropIndexes(ClientSession clientSession, DropIndexOptions dropIndexOptions) {
        dropIndex(clientSession, "*", dropIndexOptions);
    }

    private void executeDropIndex(@Nullable ClientSession clientSession, String indexName, DropIndexOptions dropIndexOptions) {
        Assertions.notNull("dropIndexOptions", dropIndexOptions);
        this.executor.execute(this.operations.dropIndex(indexName, dropIndexOptions), this.readConcern, clientSession);
    }

    private void executeDropIndex(@Nullable ClientSession clientSession, Bson keys, DropIndexOptions dropIndexOptions) {
        this.executor.execute(this.operations.dropIndex(keys, dropIndexOptions), this.readConcern, clientSession);
    }

    @Override // com.mongodb.client.MongoCollection
    public void renameCollection(MongoNamespace newCollectionNamespace) {
        renameCollection(newCollectionNamespace, new RenameCollectionOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void renameCollection(MongoNamespace newCollectionNamespace, RenameCollectionOptions renameCollectionOptions) {
        executeRenameCollection(null, newCollectionNamespace, renameCollectionOptions);
    }

    @Override // com.mongodb.client.MongoCollection
    public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace) {
        renameCollection(clientSession, newCollectionNamespace, new RenameCollectionOptions());
    }

    @Override // com.mongodb.client.MongoCollection
    public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace, RenameCollectionOptions renameCollectionOptions) {
        Assertions.notNull("clientSession", clientSession);
        executeRenameCollection(clientSession, newCollectionNamespace, renameCollectionOptions);
    }

    private void executeRenameCollection(@Nullable ClientSession clientSession, MongoNamespace newCollectionNamespace, RenameCollectionOptions renameCollectionOptions) {
        this.executor.execute(new RenameCollectionOperation(getNamespace(), newCollectionNamespace, this.writeConcern).dropTarget(renameCollectionOptions.isDropTarget()), this.readConcern, clientSession);
    }

    private DeleteResult executeDelete(@Nullable ClientSession clientSession, Bson filter, DeleteOptions deleteOptions, boolean multi) {
        BulkWriteResult result = executeSingleWriteRequest(clientSession, multi ? this.operations.deleteMany(filter, deleteOptions) : this.operations.deleteOne(filter, deleteOptions), WriteRequest.Type.DELETE);
        if (result.wasAcknowledged()) {
            return DeleteResult.acknowledged(result.getDeletedCount());
        }
        return DeleteResult.unacknowledged();
    }

    private UpdateResult executeUpdate(@Nullable ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions, boolean multi) {
        return toUpdateResult(executeSingleWriteRequest(clientSession, multi ? this.operations.updateMany(filter, update, updateOptions) : this.operations.updateOne(filter, update, updateOptions), WriteRequest.Type.UPDATE));
    }

    private BulkWriteResult executeSingleWriteRequest(@Nullable ClientSession clientSession, WriteOperation<BulkWriteResult> writeOperation, WriteRequest.Type type) {
        try {
            return (BulkWriteResult) this.executor.execute(writeOperation, this.readConcern, clientSession);
        } catch (MongoBulkWriteException e) {
            if (e.getWriteErrors().isEmpty()) {
                throw new MongoWriteConcernException(e.getWriteConcernError(), translateBulkWriteResult(type, e.getWriteResult()), e.getServerAddress());
            }
            throw new MongoWriteException(new WriteError(e.getWriteErrors().get(0)), e.getServerAddress());
        }
    }

    private WriteConcernResult translateBulkWriteResult(WriteRequest.Type type, BulkWriteResult writeResult) {
        switch (type) {
            case INSERT:
                return WriteConcernResult.acknowledged(writeResult.getInsertedCount(), false, null);
            case DELETE:
                return WriteConcernResult.acknowledged(writeResult.getDeletedCount(), false, null);
            case UPDATE:
            case REPLACE:
                return WriteConcernResult.acknowledged(writeResult.getMatchedCount() + writeResult.getUpserts().size(), writeResult.getMatchedCount() > 0, writeResult.getUpserts().isEmpty() ? null : writeResult.getUpserts().get(0).getId());
            default:
                throw new MongoInternalException("Unhandled write request type: " + type);
        }
    }

    private UpdateResult toUpdateResult(BulkWriteResult result) {
        if (result.wasAcknowledged()) {
            Long modifiedCount = result.isModifiedCountAvailable() ? Long.valueOf(result.getModifiedCount()) : null;
            BsonValue upsertedId = result.getUpserts().isEmpty() ? null : result.getUpserts().get(0).getId();
            return UpdateResult.acknowledged(result.getMatchedCount(), modifiedCount, upsertedId);
        }
        return UpdateResult.unacknowledged();
    }
}
