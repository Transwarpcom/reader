package cn.hutool.core.map;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/CaseInsensitiveTreeMap.class */
public class CaseInsensitiveTreeMap<K, V> extends CaseInsensitiveMap<K, V> {
    private static final long serialVersionUID = 4043263744224569870L;

    public CaseInsensitiveTreeMap() {
        this((Comparator) null);
    }

    public CaseInsensitiveTreeMap(Map<? extends K, ? extends V> m) {
        this();
        putAll(m);
    }

    public CaseInsensitiveTreeMap(SortedMap<? extends K, ? extends V> m) {
        super(new TreeMap((Map) m));
    }

    public CaseInsensitiveTreeMap(Comparator<? super K> comparator) {
        super(new TreeMap(comparator));
    }
}
