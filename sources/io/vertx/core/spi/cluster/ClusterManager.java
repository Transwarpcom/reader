package io.vertx.core.spi.cluster;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.Lock;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/cluster/ClusterManager.class */
public interface ClusterManager {
    void setVertx(Vertx vertx);

    <K, V> void getAsyncMultiMap(String str, Handler<AsyncResult<AsyncMultiMap<K, V>>> handler);

    <K, V> void getAsyncMap(String str, Handler<AsyncResult<AsyncMap<K, V>>> handler);

    <K, V> Map<K, V> getSyncMap(String str);

    void getLockWithTimeout(String str, long j, Handler<AsyncResult<Lock>> handler);

    void getCounter(String str, Handler<AsyncResult<Counter>> handler);

    String getNodeID();

    List<String> getNodes();

    void nodeListener(NodeListener nodeListener);

    void join(Handler<AsyncResult<Void>> handler);

    void leave(Handler<AsyncResult<Void>> handler);

    boolean isActive();
}
