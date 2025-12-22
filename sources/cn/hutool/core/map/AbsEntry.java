package cn.hutool.core.map;

import cn.hutool.core.util.ObjectUtil;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/AbsEntry.class */
public abstract class AbsEntry<K, V> implements Map.Entry<K, V> {
    @Override // java.util.Map.Entry
    public V setValue(V value) {
        throw new UnsupportedOperationException("Entry is read only.");
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object object) {
        if (object instanceof Map.Entry) {
            Map.Entry<?, ?> that = (Map.Entry) object;
            return ObjectUtil.equals(getKey(), that.getKey()) && ObjectUtil.equals(getValue(), that.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        K k = getKey();
        V v = getValue();
        return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
