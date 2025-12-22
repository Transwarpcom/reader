package com.mongodb.client;

import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.lang.Nullable;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoIterable.class */
public interface MongoIterable<TResult> extends Iterable<TResult> {
    @Override // java.lang.Iterable
    MongoCursor<TResult> iterator();

    @Nullable
    TResult first();

    <U> MongoIterable<U> map(Function<TResult, U> function);

    void forEach(Block<? super TResult> block);

    <A extends Collection<? super TResult>> A into(A a);

    MongoIterable<TResult> batchSize(int i);
}
