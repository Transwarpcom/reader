package io.vertx.core.shareddata.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.cluster.ClusterManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/SharedDataImpl.class */
public class SharedDataImpl implements SharedData {
    private static final long DEFAULT_LOCK_TIMEOUT = 10000;
    private final VertxInternal vertx;
    private final ClusterManager clusterManager;
    private final ConcurrentMap<String, LocalAsyncMapImpl<?, ?>> localAsyncMaps = new ConcurrentHashMap();
    private final ConcurrentMap<String, Counter> localCounters = new ConcurrentHashMap();
    private final ConcurrentMap<String, LocalMap<?, ?>> localMaps = new ConcurrentHashMap();
    private final LocalAsyncLocks localAsyncLocks = new LocalAsyncLocks();

    public SharedDataImpl(VertxInternal vertx, ClusterManager clusterManager) {
        this.vertx = vertx;
        this.clusterManager = clusterManager;
    }

    @Override // io.vertx.core.shareddata.SharedData
    public <K, V> void getClusterWideMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        if (this.clusterManager == null) {
            throw new IllegalStateException("Can't get cluster wide map if not clustered");
        }
        this.clusterManager.getAsyncMap(name, ar -> {
            if (ar.succeeded()) {
                resultHandler.handle(Future.succeededFuture(new WrappedAsyncMap((AsyncMap) ar.result())));
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    @Override // io.vertx.core.shareddata.SharedData
    public <K, V> void getAsyncMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        if (this.clusterManager == null) {
            getLocalAsyncMap(name, resultHandler);
        } else {
            this.clusterManager.getAsyncMap(name, ar -> {
                if (ar.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(new WrappedAsyncMap((AsyncMap) ar.result())));
                } else {
                    resultHandler.handle(Future.failedFuture(ar.cause()));
                }
            });
        }
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getLock(String name, Handler<AsyncResult<Lock>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        getLockWithTimeout(name, 10000L, resultHandler);
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getLockWithTimeout(String name, long timeout, Handler<AsyncResult<Lock>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        Arguments.require(timeout >= 0, "timeout must be >= 0");
        if (this.clusterManager == null) {
            getLocalLockWithTimeout(name, timeout, resultHandler);
        } else {
            this.clusterManager.getLockWithTimeout(name, timeout, resultHandler);
        }
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getLocalLock(String name, Handler<AsyncResult<Lock>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        getLocalLockWithTimeout(name, 10000L, resultHandler);
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getLocalLockWithTimeout(String name, long timeout, Handler<AsyncResult<Lock>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        Arguments.require(timeout >= 0, "timeout must be >= 0");
        this.localAsyncLocks.acquire(this.vertx.getOrCreateContext(), name, timeout, resultHandler);
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getCounter(String name, Handler<AsyncResult<Counter>> resultHandler) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(resultHandler, "resultHandler");
        if (this.clusterManager == null) {
            getLocalCounter(name, resultHandler);
        } else {
            this.clusterManager.getCounter(name, resultHandler);
        }
    }

    @Override // io.vertx.core.shareddata.SharedData
    public <K, V> LocalMap<K, V> getLocalMap(String name) {
        return (LocalMap) this.localMaps.computeIfAbsent(name, n -> {
            return new LocalMapImpl(n, this.localMaps);
        });
    }

    @Override // io.vertx.core.shareddata.SharedData
    public <K, V> void getLocalAsyncMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(new WrappedAsyncMap(this.localAsyncMaps.computeIfAbsent(name, n -> {
            return new LocalAsyncMapImpl(this.vertx);
        }))));
    }

    @Override // io.vertx.core.shareddata.SharedData
    public void getLocalCounter(String name, Handler<AsyncResult<Counter>> resultHandler) {
        Counter counter = this.localCounters.computeIfAbsent(name, n -> {
            return new AsynchronousCounter(this.vertx);
        });
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(counter));
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkType(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot put null in key or value of async map");
        }
        Class<?> clazz = obj.getClass();
        if (clazz == Integer.class || clazz == Integer.TYPE || clazz == Long.class || clazz == Long.TYPE || clazz == Short.class || clazz == Short.TYPE || clazz == Float.class || clazz == Float.TYPE || clazz == Double.class || clazz == Double.TYPE || clazz == Boolean.class || clazz == Boolean.TYPE || clazz == Byte.class || clazz == Byte.TYPE || clazz == String.class || clazz == byte[].class || (obj instanceof ClusterSerializable) || (obj instanceof Serializable)) {
        } else {
            throw new IllegalArgumentException("Invalid type: " + clazz + " to put in async map");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/SharedDataImpl$WrappedAsyncMap.class */
    public static final class WrappedAsyncMap<K, V> implements AsyncMap<K, V> {
        private final AsyncMap<K, V> delegate;

        WrappedAsyncMap(AsyncMap<K, V> other) {
            this.delegate = other;
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void get(K k, Handler<AsyncResult<V>> asyncResultHandler) {
            this.delegate.get(k, asyncResultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void put(K k, V v, Handler<AsyncResult<Void>> completionHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(v);
            this.delegate.put(k, v, completionHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void put(K k, V v, long timeout, Handler<AsyncResult<Void>> completionHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(v);
            this.delegate.put(k, v, timeout, completionHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void putIfAbsent(K k, V v, Handler<AsyncResult<V>> completionHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(v);
            this.delegate.putIfAbsent(k, v, completionHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void putIfAbsent(K k, V v, long timeout, Handler<AsyncResult<V>> completionHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(v);
            this.delegate.putIfAbsent(k, v, timeout, completionHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void remove(K k, Handler<AsyncResult<V>> resultHandler) {
            this.delegate.remove(k, resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void removeIfPresent(K k, V v, Handler<AsyncResult<Boolean>> resultHandler) {
            this.delegate.removeIfPresent(k, v, resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void replace(K k, V v, Handler<AsyncResult<V>> resultHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(v);
            this.delegate.replace(k, v, resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void replaceIfPresent(K k, V oldValue, V newValue, Handler<AsyncResult<Boolean>> resultHandler) {
            SharedDataImpl.checkType(k);
            SharedDataImpl.checkType(oldValue);
            SharedDataImpl.checkType(newValue);
            this.delegate.replaceIfPresent(k, oldValue, newValue, resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void clear(Handler<AsyncResult<Void>> resultHandler) {
            this.delegate.clear(resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void size(Handler<AsyncResult<Integer>> resultHandler) {
            this.delegate.size(resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void keys(Handler<AsyncResult<Set<K>>> resultHandler) {
            this.delegate.keys(resultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void values(Handler<AsyncResult<List<V>>> asyncResultHandler) {
            this.delegate.values(asyncResultHandler);
        }

        @Override // io.vertx.core.shareddata.AsyncMap
        public void entries(Handler<AsyncResult<Map<K, V>>> asyncResultHandler) {
            this.delegate.entries(asyncResultHandler);
        }

        public AsyncMap<K, V> getDelegate() {
            return this.delegate;
        }
    }
}
