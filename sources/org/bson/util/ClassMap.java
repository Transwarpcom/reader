package org.bson.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/ClassMap.class */
public class ClassMap<T> {
    private final Map<Class<?>, T> map = CopyOnWriteMap.newHashMap();
    private final Map<Class<?>, T> cache = ComputingMap.create(new ComputeFunction());

    public static <T> List<Class<?>> getAncestry(Class<T> clazz) {
        return ClassAncestry.getAncestry(clazz);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/ClassMap$ComputeFunction.class */
    private final class ComputeFunction implements Function<Class<?>, T> {
        private ComputeFunction() {
        }

        @Override // org.bson.util.Function
        public T apply(Class<?> cls) {
            Iterator<Class<?>> it = ClassMap.getAncestry(cls).iterator();
            while (it.hasNext()) {
                T t = (T) ClassMap.this.map.get(it.next());
                if (t != null) {
                    return t;
                }
            }
            return null;
        }
    }

    public T get(Object key) {
        return this.cache.get(key);
    }

    public T put(Class<?> key, T value) {
        try {
            T tPut = this.map.put(key, value);
            this.cache.clear();
            return tPut;
        } catch (Throwable th) {
            this.cache.clear();
            throw th;
        }
    }

    public T remove(Object key) {
        try {
            return this.map.remove(key);
        } finally {
            this.cache.clear();
        }
    }

    public void clear() {
        this.map.clear();
        this.cache.clear();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }
}
