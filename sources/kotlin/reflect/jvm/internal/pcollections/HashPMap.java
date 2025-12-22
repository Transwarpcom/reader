package kotlin.reflect.jvm.internal.pcollections;

import org.jetbrains.annotations.NotNull;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/pcollections/HashPMap.class */
public final class HashPMap<K, V> {
    private static final HashPMap<Object, Object> EMPTY = new HashPMap<>(IntTreePMap.empty(), 0);
    private final IntTreePMap<ConsPStack<MapEntry<K, V>>> intMap;
    private final int size;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "kotlin/reflect/jvm/internal/pcollections/HashPMap";
        switch (i) {
            case 0:
            default:
                objArr[1] = "empty";
                break;
            case 1:
                objArr[1] = "minus";
                break;
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
    }

    @NotNull
    public static <K, V> HashPMap<K, V> empty() {
        HashPMap<K, V> hashPMap = (HashPMap<K, V>) EMPTY;
        if (hashPMap == null) {
            $$$reportNull$$$0(0);
        }
        return hashPMap;
    }

    private HashPMap(IntTreePMap<ConsPStack<MapEntry<K, V>>> intMap, int size) {
        this.intMap = intMap;
        this.size = size;
    }

    public int size() {
        return this.size;
    }

    public boolean containsKey(Object key) {
        return keyIndexIn(getEntries(key.hashCode()), key) != -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V get(Object key) {
        ConsPStack entries = getEntries(key.hashCode());
        while (true) {
            ConsPStack consPStack = entries;
            if (consPStack != null && consPStack.size() > 0) {
                MapEntry<K, V> entry = (MapEntry) consPStack.first;
                if (entry.key.equals(key)) {
                    return entry.value;
                }
                entries = consPStack.rest;
            } else {
                return null;
            }
        }
    }

    @NotNull
    public HashPMap<K, V> plus(K key, V value) {
        ConsPStack<MapEntry<K, V>> entries = getEntries(key.hashCode());
        int size0 = entries.size();
        int i = keyIndexIn(entries, key);
        if (i != -1) {
            entries = entries.minus(i);
        }
        ConsPStack<MapEntry<K, V>> entries2 = entries.plus(new MapEntry<>(key, value));
        return new HashPMap<>(this.intMap.plus(key.hashCode(), entries2), (this.size - size0) + entries2.size());
    }

    @NotNull
    public HashPMap<K, V> minus(Object key) {
        ConsPStack<MapEntry<K, V>> entries = getEntries(key.hashCode());
        int i = keyIndexIn(entries, key);
        if (i == -1) {
            if (this == null) {
                $$$reportNull$$$0(1);
            }
            return this;
        }
        ConsPStack<MapEntry<K, V>> entries2 = entries.minus(i);
        if (entries2.size() == 0) {
            return new HashPMap<>(this.intMap.minus(key.hashCode()), this.size - 1);
        }
        return new HashPMap<>(this.intMap.plus(key.hashCode(), entries2), this.size - 1);
    }

    private ConsPStack<MapEntry<K, V>> getEntries(int hash) {
        ConsPStack<MapEntry<K, V>> entries = this.intMap.get(hash);
        return entries == null ? ConsPStack.empty() : entries;
    }

    private static <K, V> int keyIndexIn(ConsPStack<MapEntry<K, V>> entries, Object key) {
        int i = 0;
        while (entries != null && entries.size() > 0) {
            MapEntry<K, V> entry = entries.first;
            if (entry.key.equals(key)) {
                return i;
            }
            entries = entries.rest;
            i++;
        }
        return -1;
    }
}
