package io.vertx.core.shareddata.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncMapImpl.class */
public class LocalAsyncMapImpl<K, V> implements AsyncMap<K, V> {
    private final Vertx vertx;
    private final ConcurrentMap<K, Holder<V>> map = new ConcurrentHashMap();

    public LocalAsyncMapImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void get(K k, Handler<AsyncResult<V>> resultHandler) {
        Holder<V> h = this.map.get(k);
        if (h != null && h.hasNotExpired()) {
            resultHandler.handle(Future.succeededFuture(h.value));
        } else {
            resultHandler.handle(Future.succeededFuture());
        }
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void put(K k, V v, Handler<AsyncResult<Void>> resultHandler) {
        Holder<V> previous = this.map.put(k, new Holder<>(v));
        if (previous != null && previous.expires()) {
            this.vertx.cancelTimer(previous.timerId);
        }
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void putIfAbsent(K k, V v, Handler<AsyncResult<V>> resultHandler) {
        Holder<V> h = this.map.putIfAbsent(k, new Holder<>(v));
        resultHandler.handle(Future.succeededFuture(h == null ? null : h.value));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void put(K k, V v, long timeout, Handler<AsyncResult<Void>> completionHandler) {
        long timestamp = System.nanoTime();
        long timerId = this.vertx.setTimer(timeout, l -> {
            removeIfExpired(k);
        });
        Holder<V> previous = this.map.put(k, new Holder<>(v, timerId, timeout, timestamp));
        if (previous != null && previous.expires()) {
            this.vertx.cancelTimer(previous.timerId);
        }
        completionHandler.handle(Future.succeededFuture());
    }

    private void removeIfExpired(K k) {
        this.map.computeIfPresent(k, (key, holder) -> {
            if (holder.hasNotExpired()) {
                return holder;
            }
            return null;
        });
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void putIfAbsent(K k, V v, long timeout, Handler<AsyncResult<V>> completionHandler) {
        long timestamp = System.nanoTime();
        long timerId = this.vertx.setTimer(timeout, l -> {
            removeIfExpired(k);
        });
        Holder<V> existing = this.map.putIfAbsent(k, new Holder<>(v, timerId, timeout, timestamp));
        if (existing != null) {
            if (existing.expires()) {
                this.vertx.cancelTimer(timerId);
            }
            completionHandler.handle(Future.succeededFuture(existing.value));
            return;
        }
        completionHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void removeIfPresent(K k, V v, Handler<AsyncResult<Boolean>> resultHandler) {
        AtomicBoolean result = new AtomicBoolean();
        this.map.computeIfPresent(k, (key, holder) -> {
            if (holder.value.equals(v)) {
                result.compareAndSet(false, true);
                if (holder.expires()) {
                    this.vertx.cancelTimer(holder.timerId);
                    return null;
                }
                return null;
            }
            return holder;
        });
        resultHandler.handle(Future.succeededFuture(Boolean.valueOf(result.get())));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void replace(K k, V v, Handler<AsyncResult<V>> resultHandler) {
        Holder<V> previous = this.map.replace(k, new Holder<>(v));
        if (previous != null) {
            if (previous.expires()) {
                this.vertx.cancelTimer(previous.timerId);
            }
            resultHandler.handle(Future.succeededFuture(previous.value));
            return;
        }
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void replaceIfPresent(K k, V oldValue, V newValue, Handler<AsyncResult<Boolean>> resultHandler) {
        Holder<V> h = new Holder<>(newValue);
        Holder<V> result = this.map.computeIfPresent(k, (key, holder) -> {
            if (holder.value.equals(oldValue)) {
                if (holder.expires()) {
                    this.vertx.cancelTimer(holder.timerId);
                }
                return h;
            }
            return holder;
        });
        resultHandler.handle(Future.succeededFuture(Boolean.valueOf(h == result)));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void clear(Handler<AsyncResult<Void>> resultHandler) {
        this.map.clear();
        resultHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void size(Handler<AsyncResult<Integer>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(Integer.valueOf(this.map.size())));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void keys(Handler<AsyncResult<Set<K>>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(new HashSet(this.map.keySet())));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void values(Handler<AsyncResult<List<V>>> asyncResultHandler) {
        List<V> result = (List) this.map.values().stream().filter((v0) -> {
            return v0.hasNotExpired();
        }).map(h -> {
            return h.value;
        }).collect(Collectors.toList());
        asyncResultHandler.handle(Future.succeededFuture(result));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void entries(Handler<AsyncResult<Map<K, V>>> asyncResultHandler) {
        Map<K, V> result = new HashMap<>(this.map.size());
        this.map.forEach((key, holder) -> {
            if (holder.hasNotExpired()) {
                result.put(key, holder.value);
            }
        });
        asyncResultHandler.handle(Future.succeededFuture(result));
    }

    @Override // io.vertx.core.shareddata.AsyncMap
    public void remove(K k, Handler<AsyncResult<V>> resultHandler) {
        Holder<V> previous = this.map.remove(k);
        if (previous != null) {
            if (previous.expires()) {
                this.vertx.cancelTimer(previous.timerId);
            }
            resultHandler.handle(Future.succeededFuture(previous.value));
            return;
        }
        resultHandler.handle(Future.succeededFuture());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalAsyncMapImpl$Holder.class */
    private static class Holder<V> {
        final V value;
        final long timerId;
        final long ttl;
        final long timestamp;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [io.vertx.core.shareddata.impl.LocalAsyncMapImpl$Holder] */
        Holder(V value) {
            Objects.requireNonNull(value);
            this.value = value;
            ?? r3 = 0;
            this.timerId = 0L;
            this.ttl = 0L;
            r3.timestamp = this;
        }

        Holder(V value, long timerId, long ttl, long timestamp) {
            Objects.requireNonNull(value);
            if (ttl < 1) {
                throw new IllegalArgumentException("ttl must be positive: " + ttl);
            }
            this.value = value;
            this.timerId = timerId;
            this.ttl = ttl;
            this.timestamp = timestamp;
        }

        boolean expires() {
            return this.ttl > 0;
        }

        boolean hasNotExpired() {
            return !expires() || TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.timestamp, TimeUnit.NANOSECONDS) < this.ttl;
        }

        public String toString() {
            return "Holder{value=" + this.value + ", timerId=" + this.timerId + ", ttl=" + this.ttl + ", timestamp=" + this.timestamp + '}';
        }
    }
}
