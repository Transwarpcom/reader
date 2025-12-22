package com.mongodb.client.gridfs;

import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Collation;
import com.mongodb.lang.Nullable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/gridfs/GridFSFindIterableImpl.class */
class GridFSFindIterableImpl implements GridFSFindIterable {
    private final FindIterable<GridFSFile> underlying;

    GridFSFindIterableImpl(FindIterable<GridFSFile> underlying) {
        this.underlying = underlying;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable sort(@Nullable Bson sort) {
        this.underlying.sort(sort);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable skip(int skip) {
        this.underlying.skip(skip);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable limit(int limit) {
        this.underlying.limit(limit);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable filter(@Nullable Bson filter) {
        this.underlying.filter(filter);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable maxTime(long maxTime, TimeUnit timeUnit) {
        this.underlying.maxTime(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.MongoIterable
    /* renamed from: batchSize, reason: merged with bridge method [inline-methods] */
    public MongoIterable<GridFSFile> batchSize2(int batchSize) {
        this.underlying.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable collation(@Nullable Collation collation) {
        this.underlying.collation(collation);
        return this;
    }

    @Override // com.mongodb.client.gridfs.GridFSFindIterable
    public GridFSFindIterable noCursorTimeout(boolean noCursorTimeout) {
        this.underlying.noCursorTimeout(noCursorTimeout);
        return this;
    }

    @Override // java.lang.Iterable
    public MongoCursor<GridFSFile> iterator() {
        return this.underlying.iterator();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.client.MongoIterable
    @Nullable
    public GridFSFile first() {
        return this.underlying.first();
    }

    @Override // com.mongodb.client.MongoIterable
    public <U> MongoIterable<U> map(Function<GridFSFile, U> mapper) {
        return this.underlying.map(mapper);
    }

    @Override // com.mongodb.client.MongoIterable
    public void forEach(Block<? super GridFSFile> block) {
        this.underlying.forEach(block);
    }

    @Override // com.mongodb.client.MongoIterable
    public <A extends Collection<? super GridFSFile>> A into(A a) {
        return (A) this.underlying.into(a);
    }
}
