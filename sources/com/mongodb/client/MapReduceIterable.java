package com.mongodb.client;

import com.mongodb.client.model.Collation;
import com.mongodb.client.model.MapReduceAction;
import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MapReduceIterable.class */
public interface MapReduceIterable<TResult> extends MongoIterable<TResult> {
    void toCollection();

    MapReduceIterable<TResult> collectionName(String str);

    MapReduceIterable<TResult> finalizeFunction(@Nullable String str);

    MapReduceIterable<TResult> scope(@Nullable Bson bson);

    MapReduceIterable<TResult> sort(@Nullable Bson bson);

    MapReduceIterable<TResult> filter(@Nullable Bson bson);

    MapReduceIterable<TResult> limit(int i);

    MapReduceIterable<TResult> jsMode(boolean z);

    MapReduceIterable<TResult> verbose(boolean z);

    MapReduceIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    MapReduceIterable<TResult> action(MapReduceAction mapReduceAction);

    MapReduceIterable<TResult> databaseName(@Nullable String str);

    MapReduceIterable<TResult> sharded(boolean z);

    MapReduceIterable<TResult> nonAtomic(boolean z);

    @Override // com.mongodb.client.MongoIterable
    MapReduceIterable<TResult> batchSize(int i);

    MapReduceIterable<TResult> bypassDocumentValidation(@Nullable Boolean bool);

    MapReduceIterable<TResult> collation(@Nullable Collation collation);
}
