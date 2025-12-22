package cn.hutool.core.map.multi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapWrapper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/multi/AbsCollValueMap.class */
public abstract class AbsCollValueMap<K, V, C extends Collection<V>> extends MapWrapper<K, C> {
    private static final long serialVersionUID = 1;
    protected static final int DEFAULT_COLLECTION_INITIAL_CAPACITY = 3;

    protected abstract C createCollection();

    public AbsCollValueMap() {
        this(16);
    }

    public AbsCollValueMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public AbsCollValueMap(Map<? extends K, C> m) {
        this(0.75f, m);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbsCollValueMap(float loadFactor, Map<? extends K, C> map) {
        this(map.size(), loadFactor);
        putAll(map);
    }

    public AbsCollValueMap(int initialCapacity, float loadFactor) {
        super(new HashMap(initialCapacity, loadFactor));
    }

    public void putAllValues(Map<? extends K, ? extends Collection<V>> m) {
        if (null != m) {
            m.forEach((key, valueColl) -> {
                if (null != valueColl) {
                    valueColl.forEach(obj -> {
                        putValue(key, obj);
                    });
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Collection] */
    public void putValue(K key, V value) {
        V vCreateCollection = (Collection) get(key);
        if (null == vCreateCollection) {
            vCreateCollection = createCollection();
            put(key, vCreateCollection);
        }
        vCreateCollection.add(value);
    }

    public V get(K k, int i) {
        return (V) CollUtil.get((Collection) get(k), i);
    }
}
