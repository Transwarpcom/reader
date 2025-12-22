package com.mongodb.client;

import com.mongodb.client.model.Collation;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/ChangeStreamIterable.class */
public interface ChangeStreamIterable<TResult> extends MongoIterable<ChangeStreamDocument<TResult>> {
    ChangeStreamIterable<TResult> fullDocument(FullDocument fullDocument);

    ChangeStreamIterable<TResult> resumeAfter(BsonDocument bsonDocument);

    @Override // com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    ChangeStreamIterable<TResult> batchSize2(int i);

    ChangeStreamIterable<TResult> maxAwaitTime(long j, TimeUnit timeUnit);

    ChangeStreamIterable<TResult> collation(@Nullable Collation collation);

    <TDocument> MongoIterable<TDocument> withDocumentClass(Class<TDocument> cls);

    ChangeStreamIterable<TResult> startAtOperationTime(BsonTimestamp bsonTimestamp);
}
