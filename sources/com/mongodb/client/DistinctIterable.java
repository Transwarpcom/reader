package com.mongodb.client;

import com.mongodb.client.model.Collation;
import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/DistinctIterable.class */
public interface DistinctIterable<TResult> extends MongoIterable<TResult> {
    DistinctIterable<TResult> filter(@Nullable Bson bson);

    DistinctIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    @Override // com.mongodb.client.MongoIterable
    DistinctIterable<TResult> batchSize(int i);

    DistinctIterable<TResult> collation(@Nullable Collation collation);
}
