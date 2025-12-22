package com.mongodb.client;

import com.mongodb.client.model.Collation;
import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/AggregateIterable.class */
public interface AggregateIterable<TResult> extends MongoIterable<TResult> {
    void toCollection();

    AggregateIterable<TResult> allowDiskUse(@Nullable Boolean bool);

    @Override // com.mongodb.client.MongoIterable
    AggregateIterable<TResult> batchSize(int i);

    AggregateIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    @Deprecated
    AggregateIterable<TResult> useCursor(@Nullable Boolean bool);

    AggregateIterable<TResult> maxAwaitTime(long j, TimeUnit timeUnit);

    AggregateIterable<TResult> bypassDocumentValidation(@Nullable Boolean bool);

    AggregateIterable<TResult> collation(@Nullable Collation collation);

    AggregateIterable<TResult> comment(@Nullable String str);

    AggregateIterable<TResult> hint(@Nullable Bson bson);
}
