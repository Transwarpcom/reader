package com.mongodb.client;

import com.mongodb.CursorType;
import com.mongodb.client.model.Collation;
import com.mongodb.lang.Nullable;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/FindIterable.class */
public interface FindIterable<TResult> extends MongoIterable<TResult> {
    FindIterable<TResult> filter(@Nullable Bson bson);

    FindIterable<TResult> limit(int i);

    FindIterable<TResult> skip(int i);

    FindIterable<TResult> maxTime(long j, TimeUnit timeUnit);

    FindIterable<TResult> maxAwaitTime(long j, TimeUnit timeUnit);

    @Deprecated
    FindIterable<TResult> modifiers(@Nullable Bson bson);

    FindIterable<TResult> projection(@Nullable Bson bson);

    FindIterable<TResult> sort(@Nullable Bson bson);

    FindIterable<TResult> noCursorTimeout(boolean z);

    FindIterable<TResult> oplogReplay(boolean z);

    FindIterable<TResult> partial(boolean z);

    FindIterable<TResult> cursorType(CursorType cursorType);

    @Override // com.mongodb.client.MongoIterable
    FindIterable<TResult> batchSize(int i);

    FindIterable<TResult> collation(@Nullable Collation collation);

    FindIterable<TResult> comment(@Nullable String str);

    FindIterable<TResult> hint(@Nullable Bson bson);

    FindIterable<TResult> max(@Nullable Bson bson);

    FindIterable<TResult> min(@Nullable Bson bson);

    @Deprecated
    FindIterable<TResult> maxScan(long j);

    FindIterable<TResult> returnKey(boolean z);

    FindIterable<TResult> showRecordId(boolean z);

    @Deprecated
    FindIterable<TResult> snapshot(boolean z);
}
