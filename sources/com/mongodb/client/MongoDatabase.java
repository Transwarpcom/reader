package com.mongodb.client;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoDatabase.class */
public interface MongoDatabase {
    String getName();

    CodecRegistry getCodecRegistry();

    ReadPreference getReadPreference();

    WriteConcern getWriteConcern();

    ReadConcern getReadConcern();

    MongoDatabase withCodecRegistry(CodecRegistry codecRegistry);

    MongoDatabase withReadPreference(ReadPreference readPreference);

    MongoDatabase withWriteConcern(WriteConcern writeConcern);

    MongoDatabase withReadConcern(ReadConcern readConcern);

    MongoCollection<Document> getCollection(String str);

    <TDocument> MongoCollection<TDocument> getCollection(String str, Class<TDocument> cls);

    Document runCommand(Bson bson);

    Document runCommand(Bson bson, ReadPreference readPreference);

    <TResult> TResult runCommand(Bson bson, Class<TResult> cls);

    <TResult> TResult runCommand(Bson bson, ReadPreference readPreference, Class<TResult> cls);

    Document runCommand(ClientSession clientSession, Bson bson);

    Document runCommand(ClientSession clientSession, Bson bson, ReadPreference readPreference);

    <TResult> TResult runCommand(ClientSession clientSession, Bson bson, Class<TResult> cls);

    <TResult> TResult runCommand(ClientSession clientSession, Bson bson, ReadPreference readPreference, Class<TResult> cls);

    void drop();

    void drop(ClientSession clientSession);

    MongoIterable<String> listCollectionNames();

    ListCollectionsIterable<Document> listCollections();

    <TResult> ListCollectionsIterable<TResult> listCollections(Class<TResult> cls);

    MongoIterable<String> listCollectionNames(ClientSession clientSession);

    ListCollectionsIterable<Document> listCollections(ClientSession clientSession);

    <TResult> ListCollectionsIterable<TResult> listCollections(ClientSession clientSession, Class<TResult> cls);

    void createCollection(String str);

    void createCollection(String str, CreateCollectionOptions createCollectionOptions);

    void createCollection(ClientSession clientSession, String str);

    void createCollection(ClientSession clientSession, String str, CreateCollectionOptions createCollectionOptions);

    void createView(String str, String str2, List<? extends Bson> list);

    void createView(String str, String str2, List<? extends Bson> list, CreateViewOptions createViewOptions);

    void createView(ClientSession clientSession, String str, String str2, List<? extends Bson> list);

    void createView(ClientSession clientSession, String str, String str2, List<? extends Bson> list, CreateViewOptions createViewOptions);

    ChangeStreamIterable<Document> watch();

    <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> cls);

    ChangeStreamIterable<Document> watch(List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> list, Class<TResult> cls);

    ChangeStreamIterable<Document> watch(ClientSession clientSession);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> cls);

    ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> list, Class<TResult> cls);
}
