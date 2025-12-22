package com.mongodb.client;

import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/ListDatabasesIterable.class */
public interface ListDatabasesIterable<TResult> extends MongoIterable<TResult> {
    ListDatabasesIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    @Override // com.mongodb.client.MongoIterable
    ListDatabasesIterable<TResult> batchSize(int i);

    ListDatabasesIterable<TResult> filter(@Nullable Bson bson);

    ListDatabasesIterable<TResult> nameOnly(@Nullable Boolean bool);
}
