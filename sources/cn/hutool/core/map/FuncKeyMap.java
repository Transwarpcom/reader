package cn.hutool.core.map;

import java.util.Map;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/FuncKeyMap.class */
public class FuncKeyMap<K, V> extends CustomKeyMap<K, V> {
    private static final long serialVersionUID = 1;
    private final Function<Object, K> keyFunc;

    public FuncKeyMap(Map<K, V> emptyMap, Function<Object, K> keyFunc) {
        super(emptyMap);
        this.keyFunc = keyFunc;
    }

    @Override // cn.hutool.core.map.CustomKeyMap
    protected Object customKey(Object key) {
        if (null != this.keyFunc) {
            return this.keyFunc.apply(key);
        }
        return key;
    }
}
