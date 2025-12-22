package kotlin.reflect.jvm.internal.pcollections;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/pcollections/IntTreePMap.class */
final class IntTreePMap<V> {
    private static final IntTreePMap<Object> EMPTY = new IntTreePMap<>(IntTree.EMPTYNODE);
    private final IntTree<V> root;

    public static <V> IntTreePMap<V> empty() {
        return (IntTreePMap<V>) EMPTY;
    }

    private IntTreePMap(IntTree<V> root) {
        this.root = root;
    }

    private IntTreePMap<V> withRoot(IntTree<V> root) {
        return root == this.root ? this : new IntTreePMap<>(root);
    }

    public V get(int key) {
        return this.root.get(key);
    }

    public IntTreePMap<V> plus(int key, V value) {
        return withRoot(this.root.plus(key, value));
    }

    public IntTreePMap<V> minus(int key) {
        return withRoot(this.root.minus(key));
    }
}
