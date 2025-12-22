package io.netty.channel.pool;

import io.netty.channel.pool.ChannelPool;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReadOnlyIterator;
import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/pool/AbstractChannelPoolMap.class */
public abstract class AbstractChannelPoolMap<K, P extends ChannelPool> implements ChannelPoolMap<K, P>, Iterable<Map.Entry<K, P>>, Closeable {
    private final ConcurrentMap<K, P> map = PlatformDependent.newConcurrentHashMap();

    protected abstract P newPool(K k);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [io.netty.channel.pool.ChannelPool] */
    @Override // io.netty.channel.pool.ChannelPoolMap
    public final P get(K key) {
        P pool = this.map.get(ObjectUtil.checkNotNull(key, "key"));
        if (pool == null) {
            pool = newPool(key);
            P old = this.map.putIfAbsent(key, pool);
            if (old != null) {
                pool.close();
                pool = old;
            }
        }
        return pool;
    }

    public final boolean remove(K key) {
        P pool = this.map.remove(ObjectUtil.checkNotNull(key, "key"));
        if (pool != null) {
            pool.close();
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator<Map.Entry<K, P>> iterator() {
        return new ReadOnlyIterator(this.map.entrySet().iterator());
    }

    public final int size() {
        return this.map.size();
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // io.netty.channel.pool.ChannelPoolMap
    public final boolean contains(K key) {
        return this.map.containsKey(ObjectUtil.checkNotNull(key, "key"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        for (K key : this.map.keySet()) {
            remove(key);
        }
    }
}
