package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/AsyncMap.class */
public interface AsyncMap<K, V> {
    void get(K k, Handler<AsyncResult<V>> handler);

    void put(K k, V v, Handler<AsyncResult<Void>> handler);

    void put(K k, V v, long j, Handler<AsyncResult<Void>> handler);

    void putIfAbsent(K k, V v, Handler<AsyncResult<V>> handler);

    void putIfAbsent(K k, V v, long j, Handler<AsyncResult<V>> handler);

    void remove(K k, Handler<AsyncResult<V>> handler);

    void removeIfPresent(K k, V v, Handler<AsyncResult<Boolean>> handler);

    void replace(K k, V v, Handler<AsyncResult<V>> handler);

    void replaceIfPresent(K k, V v, V v2, Handler<AsyncResult<Boolean>> handler);

    void clear(Handler<AsyncResult<Void>> handler);

    void size(Handler<AsyncResult<Integer>> handler);

    @GenIgnore
    void keys(Handler<AsyncResult<Set<K>>> handler);

    @GenIgnore
    void values(Handler<AsyncResult<List<V>>> handler);

    @GenIgnore
    void entries(Handler<AsyncResult<Map<K, V>>> handler);
}
