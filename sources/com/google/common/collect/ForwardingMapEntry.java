package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/ForwardingMapEntry.class */
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Map.Entry<K, V> delegate();

    protected ForwardingMapEntry() {
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return delegate().getKey();
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return delegate().getValue();
    }

    public V setValue(V value) {
        return delegate().setValue(value);
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object object) {
        return delegate().equals(object);
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return delegate().hashCode();
    }

    protected boolean standardEquals(Object object) {
        if (object instanceof Map.Entry) {
            Map.Entry<?, ?> that = (Map.Entry) object;
            return Objects.equal(getKey(), that.getKey()) && Objects.equal(getValue(), that.getValue());
        }
        return false;
    }

    protected int standardHashCode() {
        K k = getKey();
        V v = getValue();
        return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
    }

    @Beta
    protected String standardToString() {
        return getKey() + "=" + getValue();
    }
}
