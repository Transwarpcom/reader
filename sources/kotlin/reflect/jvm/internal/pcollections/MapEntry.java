package kotlin.reflect.jvm.internal.pcollections;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/pcollections/MapEntry.class */
final class MapEntry<K, V> implements Serializable {
    public final K key;
    public final V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MapEntry)) {
            return false;
        }
        MapEntry<?, ?> e = (MapEntry) o;
        if (this.key != null ? this.key.equals(e.key) : e.key == null) {
            if (this.value != null ? this.value.equals(e.value) : e.value == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    public String toString() {
        return this.key + "=" + this.value;
    }
}
