package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.ServiceHelper;
import io.vertx.core.spi.PumpFactory;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/Pump.class */
public interface Pump {

    @GenIgnore
    public static final PumpFactory factory = (PumpFactory) ServiceHelper.loadFactory(PumpFactory.class);

    @Fluent
    Pump setWriteQueueMaxSize(int i);

    @Fluent
    Pump start();

    @Fluent
    Pump stop();

    int numberPumped();

    static <T> Pump pump(ReadStream<T> rs, WriteStream<T> ws) {
        return factory.pump(rs, ws);
    }

    static <T> Pump pump(ReadStream<T> rs, WriteStream<T> ws, int writeQueueMaxSize) {
        return factory.pump(rs, ws, writeQueueMaxSize);
    }
}
