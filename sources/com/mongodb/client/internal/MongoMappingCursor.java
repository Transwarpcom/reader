package com.mongodb.client.internal;

import com.mongodb.Function;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.MongoCursor;
import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoMappingCursor.class */
class MongoMappingCursor<T, U> implements MongoCursor<U> {
    private final MongoCursor<T> proxied;
    private final Function<T, U> mapper;

    MongoMappingCursor(MongoCursor<T> proxied, Function<T, U> mapper) {
        this.proxied = (MongoCursor) Assertions.notNull("proxied", proxied);
        this.mapper = (Function) Assertions.notNull("mapper", mapper);
    }

    @Override // com.mongodb.client.MongoCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.proxied.close();
    }

    @Override // com.mongodb.client.MongoCursor, java.util.Iterator
    public boolean hasNext() {
        return this.proxied.hasNext();
    }

    @Override // com.mongodb.client.MongoCursor, java.util.Iterator
    public U next() {
        return (U) this.mapper.apply(this.proxied.next());
    }

    @Override // com.mongodb.client.MongoCursor
    @Nullable
    public U tryNext() {
        T proxiedNext = this.proxied.tryNext();
        if (proxiedNext == null) {
            return null;
        }
        return this.mapper.apply(proxiedNext);
    }

    @Override // java.util.Iterator
    public void remove() {
        this.proxied.remove();
    }

    @Override // com.mongodb.client.MongoCursor
    @Nullable
    public ServerCursor getServerCursor() {
        return this.proxied.getServerCursor();
    }

    @Override // com.mongodb.client.MongoCursor
    public ServerAddress getServerAddress() {
        return this.proxied.getServerAddress();
    }
}
