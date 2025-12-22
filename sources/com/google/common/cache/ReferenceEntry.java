package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.cache.LocalCache;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/cache/ReferenceEntry.class */
interface ReferenceEntry<K, V> {
    LocalCache.ValueReference<K, V> getValueReference();

    void setValueReference(LocalCache.ValueReference<K, V> valueReference);

    ReferenceEntry<K, V> getNext();

    int getHash();

    K getKey();

    long getAccessTime();

    void setAccessTime(long j);

    ReferenceEntry<K, V> getNextInAccessQueue();

    void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

    ReferenceEntry<K, V> getPreviousInAccessQueue();

    void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

    long getWriteTime();

    void setWriteTime(long j);

    ReferenceEntry<K, V> getNextInWriteQueue();

    void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

    ReferenceEntry<K, V> getPreviousInWriteQueue();

    void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);
}
