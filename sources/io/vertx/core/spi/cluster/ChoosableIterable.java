package io.vertx.core.spi.cluster;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/cluster/ChoosableIterable.class */
public interface ChoosableIterable<T> extends Iterable<T> {
    boolean isEmpty();

    T choose();
}
