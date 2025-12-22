package io.vertx.core.spi.cluster;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import java.util.function.Predicate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/cluster/AsyncMultiMap.class */
public interface AsyncMultiMap<K, V> {
    void add(K k, V v, Handler<AsyncResult<Void>> handler);

    void get(K k, Handler<AsyncResult<ChoosableIterable<V>>> handler);

    void remove(K k, V v, Handler<AsyncResult<Boolean>> handler);

    void removeAllForValue(V v, Handler<AsyncResult<Void>> handler);

    void removeAllMatching(Predicate<V> predicate, Handler<AsyncResult<Void>> handler);
}
