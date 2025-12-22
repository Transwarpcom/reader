package kotlin.reflect.jvm.internal.pcollections;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/pcollections/IntTree.class */
final class IntTree<V> {
    static final IntTree<Object> EMPTYNODE = new IntTree<>();
    private final long key;
    private final V value;
    private final IntTree<V> left;
    private final IntTree<V> right;
    private final int size;

    private IntTree() {
        this.size = 0;
        this.key = 0L;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    private IntTree(long key, V value, IntTree<V> left, IntTree<V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.size = 1 + left.size + right.size;
    }

    private IntTree<V> withKey(long newKey) {
        return (this.size == 0 || newKey == this.key) ? this : new IntTree<>(newKey, this.value, this.left, this.right);
    }

    V get(long key) {
        if (this.size == 0) {
            return null;
        }
        if (key < this.key) {
            return this.left.get(key - this.key);
        }
        if (key > this.key) {
            return this.right.get(key - this.key);
        }
        return this.value;
    }

    IntTree<V> plus(long key, V value) {
        if (this.size == 0) {
            return new IntTree<>(key, value, this, this);
        }
        if (key < this.key) {
            return rebalanced(this.left.plus(key - this.key, value), this.right);
        }
        if (key > this.key) {
            return rebalanced(this.left, this.right.plus(key - this.key, value));
        }
        if (value == this.value) {
            return this;
        }
        return new IntTree<>(key, value, this.left, this.right);
    }

    IntTree<V> minus(long key) {
        if (this.size == 0) {
            return this;
        }
        if (key < this.key) {
            return rebalanced(this.left.minus(key - this.key), this.right);
        }
        if (key > this.key) {
            return rebalanced(this.left, this.right.minus(key - this.key));
        }
        if (this.left.size == 0) {
            return this.right.withKey(this.right.key + this.key);
        }
        if (this.right.size == 0) {
            return this.left.withKey(this.left.key + this.key);
        }
        long newKey = this.right.minKey() + this.key;
        V newValue = this.right.get(newKey - this.key);
        IntTree<V> newRight = this.right.minus(newKey - this.key);
        IntTree<V> newRight2 = newRight.withKey((newRight.key + this.key) - newKey);
        IntTree<V> newLeft = this.left.withKey((this.left.key + this.key) - newKey);
        return rebalanced(newKey, newValue, newLeft, newRight2);
    }

    private long minKey() {
        if (this.left.size == 0) {
            return this.key;
        }
        return this.left.minKey() + this.key;
    }

    private IntTree<V> rebalanced(IntTree<V> newLeft, IntTree<V> newRight) {
        if (newLeft == this.left && newRight == this.right) {
            return this;
        }
        return rebalanced(this.key, this.value, newLeft, newRight);
    }

    private static <V> IntTree<V> rebalanced(long key, V value, IntTree<V> left, IntTree<V> right) {
        if (((IntTree) left).size + ((IntTree) right).size > 1) {
            if (((IntTree) left).size >= 5 * ((IntTree) right).size) {
                IntTree<V> ll = ((IntTree) left).left;
                IntTree<V> lr = ((IntTree) left).right;
                if (((IntTree) lr).size < 2 * ((IntTree) ll).size) {
                    return new IntTree<>(((IntTree) left).key + key, ((IntTree) left).value, ll, new IntTree(-((IntTree) left).key, value, lr.withKey(((IntTree) lr).key + ((IntTree) left).key), right));
                }
                IntTree<V> lrl = ((IntTree) lr).left;
                IntTree<V> lrr = ((IntTree) lr).right;
                return new IntTree<>(((IntTree) lr).key + ((IntTree) left).key + key, ((IntTree) lr).value, new IntTree(-((IntTree) lr).key, ((IntTree) left).value, ll, lrl.withKey(((IntTree) lrl).key + ((IntTree) lr).key)), new IntTree((-((IntTree) left).key) - ((IntTree) lr).key, value, lrr.withKey(((IntTree) lrr).key + ((IntTree) lr).key + ((IntTree) left).key), right));
            }
            if (((IntTree) right).size >= 5 * ((IntTree) left).size) {
                IntTree<V> rl = ((IntTree) right).left;
                IntTree<V> rr = ((IntTree) right).right;
                if (((IntTree) rl).size < 2 * ((IntTree) rr).size) {
                    return new IntTree<>(((IntTree) right).key + key, ((IntTree) right).value, new IntTree(-((IntTree) right).key, value, left, rl.withKey(((IntTree) rl).key + ((IntTree) right).key)), rr);
                }
                IntTree<V> rll = ((IntTree) rl).left;
                IntTree<V> rlr = ((IntTree) rl).right;
                return new IntTree<>(((IntTree) rl).key + ((IntTree) right).key + key, ((IntTree) rl).value, new IntTree((-((IntTree) right).key) - ((IntTree) rl).key, value, left, rll.withKey(((IntTree) rll).key + ((IntTree) rl).key + ((IntTree) right).key)), new IntTree(-((IntTree) rl).key, ((IntTree) right).value, rlr.withKey(((IntTree) rlr).key + ((IntTree) rl).key), rr));
            }
        }
        return new IntTree<>(key, value, left, right);
    }
}
