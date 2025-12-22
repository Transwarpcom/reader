package com.mongodb.client;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoDriverInformation;
import com.mongodb.client.internal.MongoClientImpl;
import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoClients.class */
public final class MongoClients {
    public static MongoClient create() {
        return create(new ConnectionString("mongodb://localhost"));
    }

    public static MongoClient create(MongoClientSettings settings) {
        return create(settings, (MongoDriverInformation) null);
    }

    public static MongoClient create(String connectionString) {
        return create(new ConnectionString(connectionString));
    }

    public static MongoClient create(ConnectionString connectionString) {
        return create(connectionString, (MongoDriverInformation) null);
    }

    public static MongoClient create(ConnectionString connectionString, @Nullable MongoDriverInformation mongoDriverInformation) {
        return create(MongoClientSettings.builder().applyConnectionString(connectionString).build(), mongoDriverInformation);
    }

    public static MongoClient create(MongoClientSettings settings, @Nullable MongoDriverInformation mongoDriverInformation) {
        return new MongoClientImpl(settings, mongoDriverInformation);
    }

    private MongoClients() {
    }
}
