package cn.hutool.core.map;

import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/CaseInsensitiveMap.class */
public class CaseInsensitiveMap<K, V> extends FuncKeyMap<K, V> {
    private static final long serialVersionUID = 4043263744224569870L;

    public CaseInsensitiveMap() {
        this(16);
    }

    public CaseInsensitiveMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public CaseInsensitiveMap(Map<? extends K, ? extends V> m) {
        this(0.75f, m);
    }

    public CaseInsensitiveMap(float loadFactor, Map<? extends K, ? extends V> m) {
        this(m.size(), loadFactor);
        putAll(m);
    }

    public CaseInsensitiveMap(int initialCapacity, float loadFactor) {
        this(MapBuilder.create(new HashMap(initialCapacity, loadFactor)));
    }

    CaseInsensitiveMap(MapBuilder<K, V> emptyMapBuilder) {
        super(emptyMapBuilder.build(), key -> {
            if (key instanceof CharSequence) {
                key = key.toString().toLowerCase();
            }
            return key;
        });
    }
}
