package com.mongodb.client;

import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/ListIndexesIterable.class */
public interface ListIndexesIterable<TResult> extends MongoIterable<TResult> {
    ListIndexesIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    @Override // com.mongodb.client.MongoIterable
    ListIndexesIterable<TResult> batchSize(int i);
}
