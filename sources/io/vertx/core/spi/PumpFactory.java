package io.vertx.core.spi;

import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/PumpFactory.class */
public interface PumpFactory {
    <T> Pump pump(ReadStream<T> readStream, WriteStream<T> writeStream);

    <T> Pump pump(ReadStream<T> readStream, WriteStream<T> writeStream, int i);
}
