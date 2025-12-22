package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.apache.pdfbox.contentstream.operator.OperatorName;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/cache/Cache.class */
public interface Cache<K, V> {
    V getIfPresent(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj);

    V get(K k, Callable<? extends V> callable) throws ExecutionException;

    ImmutableMap<K, V> getAllPresent(Iterable<?> iterable);

    void put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    void invalidate(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj);

    void invalidateAll(Iterable<?> iterable);

    void invalidateAll();

    long size();

    CacheStats stats();

    ConcurrentMap<K, V> asMap();

    void cleanUp();
}
