package com.mongodb.client;

import com.mongodb.ClientSessionOptions;
import com.mongodb.annotations.Immutable;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoClient.class */
public interface MongoClient {
    MongoDatabase getDatabase(String str);

    ClientSession startSession();

    ClientSession startSession(ClientSessionOptions clientSessionOptions);

    void close();

    MongoIterable<String> listDatabaseNames();

    MongoIterable<String> listDatabaseNames(ClientSession clientSession);

    ListDatabasesIterable<Document> listDatabases();

    ListDatabasesIterable<Document> listDatabases(ClientSession clientSession);

    <TResult> ListDatabasesIterable<TResult> listDatabases(Class<TResult> cls);

    <TResult> ListDatabasesIterable<TResult> listDatabases(ClientSession clientSession, Class<TResult> cls);

    ChangeStreamIterable<Document> watch();

    <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> cls);

    ChangeStreamIterable<Document> watch(List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> list, Class<TResult> cls);

    ChangeStreamIterable<Document> watch(ClientSession clientSession);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> cls);

    ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> list);

    <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> list, Class<TResult> cls);
}
