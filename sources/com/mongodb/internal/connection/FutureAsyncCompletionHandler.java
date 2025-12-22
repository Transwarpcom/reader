package com.mongodb.internal.connection;

import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.connection.AsyncCompletionHandler;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/FutureAsyncCompletionHandler.class */
class FutureAsyncCompletionHandler<T> implements AsyncCompletionHandler<T> {
    private final CountDownLatch latch = new CountDownLatch(1);
    private volatile T result;
    private volatile Throwable error;

    FutureAsyncCompletionHandler() {
    }

    @Override // com.mongodb.connection.AsyncCompletionHandler
    public void completed(T result) {
        this.result = result;
        this.latch.countDown();
    }

    @Override // com.mongodb.connection.AsyncCompletionHandler
    public void failed(Throwable t) {
        this.error = t;
        this.latch.countDown();
    }

    public void getOpen() throws InterruptedException, IOException {
        get("Opening");
    }

    public void getWrite() throws InterruptedException, IOException {
        get("Writing to");
    }

    public T getRead() throws IOException {
        return get("Reading from");
    }

    private T get(String prefix) throws InterruptedException, IOException {
        try {
            this.latch.await();
            if (this.error != null) {
                if (this.error instanceof IOException) {
                    throw ((IOException) this.error);
                }
                if (this.error instanceof MongoException) {
                    throw ((MongoException) this.error);
                }
                throw new MongoInternalException(prefix + " the AsynchronousSocketChannelStream failed", this.error);
            }
            return this.result;
        } catch (InterruptedException e) {
            throw new MongoInterruptedException(prefix + " the AsynchronousSocketChannelStream failed", e);
        }
    }
}
