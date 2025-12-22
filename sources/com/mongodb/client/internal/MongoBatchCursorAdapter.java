package com.mongodb.client.internal;

import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.client.MongoCursor;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MongoBatchCursorAdapter.class */
public class MongoBatchCursorAdapter<T> implements MongoCursor<T> {
    private final BatchCursor<T> batchCursor;
    private List<T> curBatch;
    private int curPos;

    public MongoBatchCursorAdapter(BatchCursor<T> batchCursor) {
        this.batchCursor = batchCursor;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cursors do not support removal");
    }

    @Override // com.mongodb.client.MongoCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.batchCursor.close();
    }

    @Override // com.mongodb.client.MongoCursor, java.util.Iterator
    public boolean hasNext() {
        return this.curBatch != null || this.batchCursor.hasNext();
    }

    @Override // com.mongodb.client.MongoCursor, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.curBatch == null) {
            this.curBatch = this.batchCursor.next();
        }
        return getNextInBatch();
    }

    @Override // com.mongodb.client.MongoCursor
    @Nullable
    public T tryNext() {
        if (this.curBatch == null) {
            this.curBatch = this.batchCursor.tryNext();
        }
        if (this.curBatch == null) {
            return null;
        }
        return getNextInBatch();
    }

    @Override // com.mongodb.client.MongoCursor
    @Nullable
    public ServerCursor getServerCursor() {
        return this.batchCursor.getServerCursor();
    }

    @Override // com.mongodb.client.MongoCursor
    public ServerAddress getServerAddress() {
        return this.batchCursor.getServerAddress();
    }

    private T getNextInBatch() {
        T nextInBatch = this.curBatch.get(this.curPos);
        if (this.curPos < this.curBatch.size() - 1) {
            this.curPos++;
        } else {
            this.curBatch = null;
            this.curPos = 0;
        }
        return nextInBatch;
    }
}
