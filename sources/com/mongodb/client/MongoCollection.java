package com.mongodb.client;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.bulk.BulkWriteResult;
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
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoCollection.class */
public interface MongoCollection<TDocument> {
    MongoNamespace getNamespace();

    Class<TDocument> getDocumentClass();

    CodecRegistry getCodecRegistry();

    ReadPreference getReadPreference();

    WriteConcern getWriteConcern();

    ReadConcern getReadConcern();

    <NewTDocument> MongoCollection<NewTDocument> withDocumentClass(Class<NewTDocument> cls);

    MongoCollection<TDocument> withCodecRegistry(CodecRegistry codecRegistry);

    MongoCollection<TDocument> withReadPreference(ReadPreference readPreference);

    MongoCollection<TDocument> withWriteConcern(WriteConcern writeConcern);

    MongoCollection<TDocument> withReadConcern(ReadConcern readConcern);

    @Deprecated
    long count();

    @Deprecated
    long count(Bson bson);

    @Deprecated
    long count(Bson bson, CountOptions countOptions);

    @Deprecated
    long count(ClientSession clientSession);

    @Deprecated
    long count(ClientSession clientSession, Bson bson);

    @Deprecated
    long count(ClientSession clientSession, Bson bson, CountOptions countOptions);

    long countDocuments();

    long countDocuments(Bson bson);

    long countDocuments(Bson bson, CountOptions countOptions);

    long countDocuments(ClientSession clientSession);

    long countDocuments(ClientSession clientSession, Bson bson);

    long countDocuments(ClientSession clientSession, Bson bson, CountOptions countOptions);

    long estimatedDocumentCount();

    long estimatedDocumentCount(EstimatedDocumentCountOptions estimatedDocumentCountOptions);

    <TResult> DistinctIterable<TResult> distinct(String str, Class<TResult> cls);

    <TResult> DistinctIterable<TResult> distinct(String str, Bson bson, Class<TResult> cls);

    <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String str, Class<TResult> cls);

    <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String str, Bson bson, Class<TResult> cls);

    FindIterable<TDocument> find();

    <TResult> FindIterable<TResult> find(Class<TResult> cls);

    FindIterable<TDocument> find(Bson bson);

    <TResult> FindIterable<TResult> find(Bson bson, Class<TResult> cls);

    FindIterable<TDocument> find(ClientSession clientSession);

    <TResult> FindIterable<TResult> find(ClientSession clientSession, Class<TResult> cls);

    FindIterable<TDocument> find(ClientSession clientSession, Bson bson);

    <TResult> FindIterable<TResult> find(ClientSession clientSession, Bson bson, Class<TResult> cls);

    AggregateIterable<TDocument> aggregate(List<? extends Bson> list);

    <TResult> AggregateIterable<TResult> aggregate(List<? extends Bson> list, Class<TResult> cls);

    AggregateIterable<TDocument> aggregate(ClientSession clientSession, List<? extends Bson> list);

    <TResult> AggregateIterable<TResult> aggregate(ClientSession clientSession, List<? extends Bson> list, Class<TResult> cls);

    ChangeStreamIterable<TDocument> watch();

    <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> cls);

    ChangeStreamIterable<TDocument> watch(List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> list, Class<TResult> cls);

    ChangeStreamIterable<TDocument> watch(ClientSession clientSession);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> cls);

    ChangeStreamIterable<TDocument> watch(ClientSession clientSession, List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> list, Class<TResult> cls);

    MapReduceIterable<TDocument> mapReduce(String str, String str2);

    <TResult> MapReduceIterable<TResult> mapReduce(String str, String str2, Class<TResult> cls);

    MapReduceIterable<TDocument> mapReduce(ClientSession clientSession, String str, String str2);

    <TResult> MapReduceIterable<TResult> mapReduce(ClientSession clientSession, String str, String str2, Class<TResult> cls);

    BulkWriteResult bulkWrite(List<? extends WriteModel<? extends TDocument>> list);

    BulkWriteResult bulkWrite(List<? extends WriteModel<? extends TDocument>> list, BulkWriteOptions bulkWriteOptions);

    BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> list);

    BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends TDocument>> list, BulkWriteOptions bulkWriteOptions);

    void insertOne(TDocument tdocument);

    void insertOne(TDocument tdocument, InsertOneOptions insertOneOptions);

    void insertOne(ClientSession clientSession, TDocument tdocument);

    void insertOne(ClientSession clientSession, TDocument tdocument, InsertOneOptions insertOneOptions);

    void insertMany(List<? extends TDocument> list);

    void insertMany(List<? extends TDocument> list, InsertManyOptions insertManyOptions);

    void insertMany(ClientSession clientSession, List<? extends TDocument> list);

    void insertMany(ClientSession clientSession, List<? extends TDocument> list, InsertManyOptions insertManyOptions);

    DeleteResult deleteOne(Bson bson);

    DeleteResult deleteOne(Bson bson, DeleteOptions deleteOptions);

    DeleteResult deleteOne(ClientSession clientSession, Bson bson);

    DeleteResult deleteOne(ClientSession clientSession, Bson bson, DeleteOptions deleteOptions);

    DeleteResult deleteMany(Bson bson);

    DeleteResult deleteMany(Bson bson, DeleteOptions deleteOptions);

    DeleteResult deleteMany(ClientSession clientSession, Bson bson);

    DeleteResult deleteMany(ClientSession clientSession, Bson bson, DeleteOptions deleteOptions);

    UpdateResult replaceOne(Bson bson, TDocument tdocument);

    @Deprecated
    UpdateResult replaceOne(Bson bson, TDocument tdocument, UpdateOptions updateOptions);

    UpdateResult replaceOne(Bson bson, TDocument tdocument, ReplaceOptions replaceOptions);

    UpdateResult replaceOne(ClientSession clientSession, Bson bson, TDocument tdocument);

    @Deprecated
    UpdateResult replaceOne(ClientSession clientSession, Bson bson, TDocument tdocument, UpdateOptions updateOptions);

    UpdateResult replaceOne(ClientSession clientSession, Bson bson, TDocument tdocument, ReplaceOptions replaceOptions);

    UpdateResult updateOne(Bson bson, Bson bson2);

    UpdateResult updateOne(Bson bson, Bson bson2, UpdateOptions updateOptions);

    UpdateResult updateOne(ClientSession clientSession, Bson bson, Bson bson2);

    UpdateResult updateOne(ClientSession clientSession, Bson bson, Bson bson2, UpdateOptions updateOptions);

    UpdateResult updateMany(Bson bson, Bson bson2);

    UpdateResult updateMany(Bson bson, Bson bson2, UpdateOptions updateOptions);

    UpdateResult updateMany(ClientSession clientSession, Bson bson, Bson bson2);

    UpdateResult updateMany(ClientSession clientSession, Bson bson, Bson bson2, UpdateOptions updateOptions);

    @Nullable
    TDocument findOneAndDelete(Bson bson);

    @Nullable
    TDocument findOneAndDelete(Bson bson, FindOneAndDeleteOptions findOneAndDeleteOptions);

    @Nullable
    TDocument findOneAndDelete(ClientSession clientSession, Bson bson);

    @Nullable
    TDocument findOneAndDelete(ClientSession clientSession, Bson bson, FindOneAndDeleteOptions findOneAndDeleteOptions);

    @Nullable
    TDocument findOneAndReplace(Bson bson, TDocument tdocument);

    @Nullable
    TDocument findOneAndReplace(Bson bson, TDocument tdocument, FindOneAndReplaceOptions findOneAndReplaceOptions);

    @Nullable
    TDocument findOneAndReplace(ClientSession clientSession, Bson bson, TDocument tdocument);

    @Nullable
    TDocument findOneAndReplace(ClientSession clientSession, Bson bson, TDocument tdocument, FindOneAndReplaceOptions findOneAndReplaceOptions);

    @Nullable
    TDocument findOneAndUpdate(Bson bson, Bson bson2);

    @Nullable
    TDocument findOneAndUpdate(Bson bson, Bson bson2, FindOneAndUpdateOptions findOneAndUpdateOptions);

    @Nullable
    TDocument findOneAndUpdate(ClientSession clientSession, Bson bson, Bson bson2);

    @Nullable
    TDocument findOneAndUpdate(ClientSession clientSession, Bson bson, Bson bson2, FindOneAndUpdateOptions findOneAndUpdateOptions);

    void drop();

    void drop(ClientSession clientSession);

    String createIndex(Bson bson);

    String createIndex(Bson bson, IndexOptions indexOptions);

    String createIndex(ClientSession clientSession, Bson bson);

    String createIndex(ClientSession clientSession, Bson bson, IndexOptions indexOptions);

    List<String> createIndexes(List<IndexModel> list);

    List<String> createIndexes(List<IndexModel> list, CreateIndexOptions createIndexOptions);

    List<String> createIndexes(ClientSession clientSession, List<IndexModel> list);

    List<String> createIndexes(ClientSession clientSession, List<IndexModel> list, CreateIndexOptions createIndexOptions);

    ListIndexesIterable<Document> listIndexes();

    <TResult> ListIndexesIterable<TResult> listIndexes(Class<TResult> cls);

    ListIndexesIterable<Document> listIndexes(ClientSession clientSession);

    <TResult> ListIndexesIterable<TResult> listIndexes(ClientSession clientSession, Class<TResult> cls);

    void dropIndex(String str);

    void dropIndex(String str, DropIndexOptions dropIndexOptions);

    void dropIndex(Bson bson);

    void dropIndex(Bson bson, DropIndexOptions dropIndexOptions);

    void dropIndex(ClientSession clientSession, String str);

    void dropIndex(ClientSession clientSession, Bson bson);

    void dropIndex(ClientSession clientSession, String str, DropIndexOptions dropIndexOptions);

    void dropIndex(ClientSession clientSession, Bson bson, DropIndexOptions dropIndexOptions);

    void dropIndexes();

    void dropIndexes(ClientSession clientSession);

    void dropIndexes(DropIndexOptions dropIndexOptions);

    void dropIndexes(ClientSession clientSession, DropIndexOptions dropIndexOptions);

    void renameCollection(MongoNamespace mongoNamespace);

    void renameCollection(MongoNamespace mongoNamespace, RenameCollectionOptions renameCollectionOptions);

    void renameCollection(ClientSession clientSession, MongoNamespace mongoNamespace);

    void renameCollection(ClientSession clientSession, MongoNamespace mongoNamespace, RenameCollectionOptions renameCollectionOptions);
}
