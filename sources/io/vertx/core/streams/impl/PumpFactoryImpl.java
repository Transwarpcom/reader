package io.vertx.core.streams.impl;

import io.vertx.core.spi.PumpFactory;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/impl/PumpFactoryImpl.class */
public class PumpFactoryImpl implements PumpFactory {
    @Override // io.vertx.core.spi.PumpFactory
    public <T> Pump pump(ReadStream<T> rs, WriteStream<T> ws) {
        Objects.requireNonNull(rs);
        Objects.requireNonNull(ws);
        return new PumpImpl(rs, ws);
    }

    @Override // io.vertx.core.spi.PumpFactory
    public <T> Pump pump(ReadStream<T> rs, WriteStream<T> ws, int writeQueueMaxSize) {
        Objects.requireNonNull(rs);
        Objects.requireNonNull(ws);
        return new PumpImpl(rs, ws, writeQueueMaxSize);
    }
}
