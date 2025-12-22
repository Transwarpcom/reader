package io.vertx.core.streams.impl;

import io.vertx.core.Handler;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/impl/PumpImpl.class */
public class PumpImpl<T> implements Pump {
    private final ReadStream<T> readStream;
    private final WriteStream<T> writeStream;
    private final Handler<T> dataHandler;
    private final Handler<Void> drainHandler;
    private int pumped;

    PumpImpl(ReadStream<T> rs, WriteStream<T> ws, int maxWriteQueueSize) {
        this(rs, ws);
        this.writeStream.setWriteQueueMaxSize2(maxWriteQueueSize);
    }

    PumpImpl(ReadStream<T> rs, WriteStream<T> ws) {
        this.readStream = rs;
        this.writeStream = ws;
        this.drainHandler = v -> {
            this.readStream.resume2();
        };
        this.dataHandler = data -> {
            this.writeStream.write(data);
            incPumped();
            if (this.writeStream.writeQueueFull()) {
                this.readStream.pause2();
                this.writeStream.drainHandler(this.drainHandler);
            }
        };
    }

    @Override // io.vertx.core.streams.Pump
    public PumpImpl setWriteQueueMaxSize(int maxSize) {
        this.writeStream.setWriteQueueMaxSize2(maxSize);
        return this;
    }

    @Override // io.vertx.core.streams.Pump
    public PumpImpl start() {
        this.readStream.handler2(this.dataHandler);
        return this;
    }

    @Override // io.vertx.core.streams.Pump
    public PumpImpl stop() {
        this.writeStream.drainHandler(null);
        this.readStream.handler2(null);
        return this;
    }

    @Override // io.vertx.core.streams.Pump
    public synchronized int numberPumped() {
        return this.pumped;
    }

    private synchronized void incPumped() {
        this.pumped++;
    }
}
