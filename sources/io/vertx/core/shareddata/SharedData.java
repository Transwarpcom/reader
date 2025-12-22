package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/SharedData.class */
public interface SharedData {
    <K, V> void getClusterWideMap(String str, Handler<AsyncResult<AsyncMap<K, V>>> handler);

    <K, V> void getAsyncMap(String str, Handler<AsyncResult<AsyncMap<K, V>>> handler);

    <K, V> void getLocalAsyncMap(String str, Handler<AsyncResult<AsyncMap<K, V>>> handler);

    void getLock(String str, Handler<AsyncResult<Lock>> handler);

    void getLockWithTimeout(String str, long j, Handler<AsyncResult<Lock>> handler);

    void getLocalLock(String str, Handler<AsyncResult<Lock>> handler);

    void getLocalLockWithTimeout(String str, long j, Handler<AsyncResult<Lock>> handler);

    void getCounter(String str, Handler<AsyncResult<Counter>> handler);

    void getLocalCounter(String str, Handler<AsyncResult<Counter>> handler);

    <K, V> LocalMap<K, V> getLocalMap(String str);
}
