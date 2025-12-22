package kotlinx.coroutines.debug.internal;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: ConcurrentWeakMap.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b��\u0018��*\b\b��\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010*:\u0003&'(B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ\u001a\u0010\u0011\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028��H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0014\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028��2\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028��2\b\u0010\u0013\u001a\u0004\u0018\u00018\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0015J\u0019\u0010\u0017\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028��H\u0016¢\u0006\u0004\b\u0017\u0010\u0012J\r\u0010\u0018\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u000eR&\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u001a0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028��0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010#\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001c\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%¨\u0006)"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "", OperatorName.STROKING_COLOR_CMYK, "V", "", "weakRefQueue", Constants.CONSTRUCTOR_NAME, "(Z)V", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", OperatorName.SET_LINE_WIDTH, "", "cleanWeakRef", "(Lkotlinx/coroutines/debug/internal/HashedWeakRef;)V", "clear", "()V", "decrementSize", "key", BeanUtil.PREFIX_GETTER_GET, "(Ljava/lang/Object;)Ljava/lang/Object;", "value", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putSynchronized", "remove", "runWeakRefQueueCleaningLoopUntilInterrupted", "", "", "getEntries", "()Ljava/util/Set;", "entries", "getKeys", "keys", "", "getSize", "()I", "size", "Ljava/lang/ref/ReferenceQueue;", "Ljava/lang/ref/ReferenceQueue;", "Core", "Entry", "KeyValueSet", "kotlinx-coroutines-core", "Lkotlin/collections/AbstractMutableMap;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMap.class */
public final class ConcurrentWeakMap<K, V> extends AbstractMutableMap<K, V> {

    @NotNull
    private volatile /* synthetic */ int _size;
    private static final /* synthetic */ AtomicIntegerFieldUpdater _size$FU = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");

    @NotNull
    volatile /* synthetic */ Object core;

    @Nullable
    private final ReferenceQueue<K> weakRefQueue;

    public ConcurrentWeakMap() {
        this(false, 1, null);
    }

    public /* synthetic */ ConcurrentWeakMap(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public ConcurrentWeakMap(boolean weakRefQueue) {
        this._size = 0;
        this.core = new Core(16);
        this.weakRefQueue = weakRefQueue ? new ReferenceQueue<>() : null;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return this._size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void decrementSize() {
        _size$FU.decrementAndGet(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        return (V) ((Core) this.core).getImpl(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
    @Nullable
    public V put(@NotNull K k, @NotNull V v) {
        V vPutImpl$default = Core.putImpl$default((Core) this.core, k, v, null, 4, null);
        if (vPutImpl$default == ConcurrentWeakMapKt.REHASH) {
            vPutImpl$default = putSynchronized(k, v);
        }
        if (vPutImpl$default == null) {
            _size$FU.incrementAndGet(this);
        }
        return vPutImpl$default;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V remove(@Nullable Object obj) {
        if (obj == 0) {
            return null;
        }
        V vPutImpl$default = Core.putImpl$default((Core) this.core, obj, null, null, 4, null);
        if (vPutImpl$default == ConcurrentWeakMapKt.REHASH) {
            vPutImpl$default = putSynchronized(obj, null);
        }
        if (vPutImpl$default != null) {
            _size$FU.decrementAndGet(this);
        }
        return vPutImpl$default;
    }

    private final synchronized V putSynchronized(K k, V v) {
        Core coreRehash = (Core) this.core;
        while (true) {
            V v2 = (V) Core.putImpl$default(coreRehash, k, v, null, 4, null);
            if (v2 != ConcurrentWeakMapKt.REHASH) {
                return v2;
            }
            coreRehash = coreRehash.rehash();
            this.core = coreRehash;
        }
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<K> getKeys() {
        return new KeyValueSet(new Function2<K, V, K>() { // from class: kotlinx.coroutines.debug.internal.ConcurrentWeakMap$keys$1
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final K invoke(@NotNull K k, @NotNull V v) {
                return k;
            }
        });
    }

    @Override // kotlin.collections.AbstractMutableMap
    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return new KeyValueSet(new Function2<K, V, Map.Entry<K, V>>() { // from class: kotlinx.coroutines.debug.internal.ConcurrentWeakMap$entries$1
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Map.Entry<K, V> invoke(@NotNull K k, @NotNull V v) {
                return new ConcurrentWeakMap.Entry(k, v);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ConcurrentWeakMap$entries$1<K, V>) obj, obj2);
            }
        });
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Object k : keySet()) {
            remove(k);
        }
    }

    public final void runWeakRefQueueCleaningLoopUntilInterrupted() throws InterruptedException {
        if (!(this.weakRefQueue != null)) {
            throw new IllegalStateException("Must be created with weakRefQueue = true".toString());
        }
        while (true) {
            try {
                Reference<? extends K> referenceRemove = this.weakRefQueue.remove();
                if (referenceRemove == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                }
                cleanWeakRef((HashedWeakRef) referenceRemove);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private final void cleanWeakRef(HashedWeakRef<?> hashedWeakRef) {
        ((Core) this.core).cleanWeakRef(hashedWeakRef);
    }

    /* compiled from: ConcurrentWeakMap.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0004\u0018��2\u00020\u0018:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028��¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ3\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00020\u0013\"\u0004\b\u0002\u0010\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J3\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\n\u001a\u00028��2\b\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001c\u001a\u00120��R\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010 R\u0014\u0010!\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010 ¨\u0006$"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "", "allocated", Constants.CONSTRUCTOR_NAME, "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;I)V", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "weakRef", "", "cleanWeakRef", "(Lkotlinx/coroutines/debug/internal/HashedWeakRef;)V", "key", "getImpl", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "index", "(I)I", "E", "Lkotlin/Function2;", "factory", "", "keyValueIterator", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "value", "weakKey0", "", "putImpl", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/debug/internal/HashedWeakRef;)Ljava/lang/Object;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "rehash", "()Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "removeCleanedAt", "(I)V", "I", "shift", "threshold", "KeyValueIterator", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core.class */
    private final class Core {
        private final int allocated;
        private final int shift;
        private final int threshold;

        @NotNull
        private volatile /* synthetic */ int load = 0;
        private static final /* synthetic */ AtomicIntegerFieldUpdater load$FU = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load");

        @NotNull
        /* synthetic */ AtomicReferenceArray keys;

        @NotNull
        /* synthetic */ AtomicReferenceArray values;

        public Core(int allocated) {
            this.allocated = allocated;
            this.shift = Integer.numberOfLeadingZeros(this.allocated) + 1;
            this.threshold = (2 * this.allocated) / 3;
            this.keys = new AtomicReferenceArray(this.allocated);
            this.values = new AtomicReferenceArray(this.allocated);
        }

        private final int index(int hash) {
            return (hash * (-1640531527)) >>> this.shift;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Nullable
        public final V getImpl(@NotNull K k) {
            int iIndex = index(k.hashCode());
            while (true) {
                int i = iIndex;
                HashedWeakRef hashedWeakRef = (HashedWeakRef) this.keys.get(i);
                if (hashedWeakRef == null) {
                    return null;
                }
                Object obj = hashedWeakRef.get();
                if (Intrinsics.areEqual(k, obj)) {
                    V v = (V) this.values.get(i);
                    return v instanceof Marked ? (V) ((Marked) v).ref : v;
                }
                if (obj == null) {
                    removeCleanedAt(i);
                }
                if (i == 0) {
                    i = this.allocated;
                }
                iIndex = i - 1;
            }
        }

        private final void removeCleanedAt(int index) {
            Object oldValue;
            do {
                oldValue = this.values.get(index);
                if (oldValue == null || (oldValue instanceof Marked)) {
                    return;
                }
            } while (!this.values.compareAndSet(index, oldValue, null));
            ConcurrentWeakMap.this.decrementSize();
        }

        public static /* synthetic */ Object putImpl$default(Core core, Object obj, Object obj2, HashedWeakRef hashedWeakRef, int i, Object obj3) {
            if ((i & 4) != 0) {
                hashedWeakRef = null;
            }
            return core.putImpl(obj, obj2, hashedWeakRef);
        }

        @Nullable
        public final Object putImpl(@NotNull K k, @Nullable V v, @Nullable HashedWeakRef<K> hashedWeakRef) {
            int cur$iv;
            int upd$iv;
            Object oldValue;
            int index = index(k.hashCode());
            boolean loadIncremented = false;
            HashedWeakRef weakKey = hashedWeakRef;
            while (true) {
                HashedWeakRef w = (HashedWeakRef) this.keys.get(index);
                if (w == null) {
                    if (v == null) {
                        return null;
                    }
                    if (!loadIncremented) {
                        do {
                            cur$iv = this.load;
                            if (cur$iv >= this.threshold) {
                                return ConcurrentWeakMapKt.REHASH;
                            }
                            upd$iv = cur$iv + 1;
                        } while (!load$FU.compareAndSet(this, cur$iv, upd$iv));
                        loadIncremented = true;
                    }
                    if (weakKey == null) {
                        weakKey = new HashedWeakRef(k, ((ConcurrentWeakMap) ConcurrentWeakMap.this).weakRefQueue);
                    }
                    if (this.keys.compareAndSet(index, null, weakKey)) {
                        break;
                    }
                } else {
                    Object k2 = w.get();
                    if (Intrinsics.areEqual(k, k2)) {
                        if (loadIncremented) {
                            load$FU.decrementAndGet(this);
                        }
                    } else {
                        if (k2 == null) {
                            removeCleanedAt(index);
                        }
                        if (index == 0) {
                            index = this.allocated;
                        }
                        index--;
                    }
                }
            }
            do {
                oldValue = this.values.get(index);
                if (oldValue instanceof Marked) {
                    return ConcurrentWeakMapKt.REHASH;
                }
            } while (!this.values.compareAndSet(index, oldValue, v));
            return oldValue;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r13v3 */
        /* JADX WARN: Type inference failed for: r13v4 */
        @NotNull
        public final ConcurrentWeakMap<K, V>.Core rehash() {
            ConcurrentWeakMap<K, V>.Core core;
            boolean z;
            loop0: while (true) {
                core = ConcurrentWeakMap.this.new Core(Integer.highestOneBit(RangesKt.coerceAtLeast(ConcurrentWeakMap.this.size(), 4)) * 4);
                int i = 0;
                int i2 = this.allocated;
                if (0 >= i2) {
                    break;
                }
                do {
                    int i3 = i;
                    i++;
                    HashedWeakRef<K> hashedWeakRef = (HashedWeakRef) this.keys.get(i3);
                    K k = hashedWeakRef == null ? null : hashedWeakRef.get();
                    if (hashedWeakRef != null && k == null) {
                        removeCleanedAt(i3);
                    }
                    while (true) {
                        z = this.values.get(i3);
                        if (!(z instanceof Marked)) {
                            if (this.values.compareAndSet(i3, z, ConcurrentWeakMapKt.mark(z))) {
                                break;
                            }
                        } else {
                            z = ((Marked) z).ref;
                            break;
                        }
                    }
                    if (k != null && z) {
                        Object objPutImpl = core.putImpl(k, z, hashedWeakRef);
                        if (objPutImpl == ConcurrentWeakMapKt.REHASH) {
                            break;
                        }
                        boolean z2 = objPutImpl == null;
                        if (_Assertions.ENABLED && !z2) {
                            throw new AssertionError("Assertion failed");
                        }
                    }
                } while (i < i2);
            }
            return core;
        }

        public final void cleanWeakRef(@NotNull HashedWeakRef<?> hashedWeakRef) {
            int iIndex = index(hashedWeakRef.hash);
            while (true) {
                int index = iIndex;
                HashedWeakRef w = (HashedWeakRef) this.keys.get(index);
                if (w == null) {
                    return;
                }
                if (w == hashedWeakRef) {
                    removeCleanedAt(index);
                    return;
                } else {
                    if (index == 0) {
                        index = this.allocated;
                    }
                    iIndex = index - 1;
                }
            }
        }

        @NotNull
        public final <E> Iterator<E> keyValueIterator(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            return new KeyValueIterator(function2);
        }

        /* compiled from: ConcurrentWeakMap.kt */
        @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0010)\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n��\b\u0082\u0004\u0018��*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\b\u001a\u00028��X\u0082.¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u00028\u0001X\u0082.¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator;", "E", "", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;Lkotlin/jvm/functions/Function2;)V", "index", "", "key", "Ljava/lang/Object;", "value", "findNext", "", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "kotlinx-coroutines-core"})
        /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator.class */
        private final class KeyValueIterator<E> implements Iterator<E>, KMutableIterator {

            @NotNull
            private final Function2<K, V, E> factory;
            private int index = -1;
            private K key;
            private V value;

            /* JADX WARN: Multi-variable type inference failed */
            public KeyValueIterator(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
                this.factory = function2;
                findNext();
            }

            private final void findNext() {
                while (true) {
                    this.index++;
                    if (this.index >= ((Core) Core.this).allocated) {
                        return;
                    }
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) Core.this.keys.get(this.index);
                    K k = (K) (hashedWeakRef == null ? null : hashedWeakRef.get());
                    if (k != null) {
                        this.key = k;
                        Object obj = Core.this.values.get(this.index);
                        if (obj instanceof Marked) {
                            obj = ((Marked) obj).ref;
                        }
                        if (obj != null) {
                            this.value = (V) obj;
                            return;
                        }
                    }
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < ((Core) Core.this).allocated;
            }

            @Override // java.util.Iterator
            public E next() {
                Object obj;
                Object obj2;
                if (this.index >= ((Core) Core.this).allocated) {
                    throw new NoSuchElementException();
                }
                Function2<K, V, E> function2 = this.factory;
                Object obj3 = this.key;
                if (obj3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("key");
                    obj = Unit.INSTANCE;
                } else {
                    obj = obj3;
                }
                Object obj4 = this.value;
                if (obj4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("value");
                    obj2 = Unit.INSTANCE;
                } else {
                    obj2 = obj4;
                }
                E e = (E) function2.invoke(obj, obj2);
                findNext();
                return e;
            }

            @Override // java.util.Iterator
            @NotNull
            public Void remove() {
                ConcurrentWeakMapKt.noImpl();
                throw new KotlinNothingValueException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ConcurrentWeakMap.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0002\b\u000b\b\u0002\u0018��*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000b\u001a\u00028\u00032\u0006\u0010\f\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\rR\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001a\u00028\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Entry;", OperatorName.STROKING_COLOR_CMYK, "V", "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMap$Entry.class */
    static final class Entry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        private final K key;
        private final V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: ConcurrentWeakMap.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010)\n��\b\u0082\u0004\u0018��*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\rJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u000fH\u0096\u0002R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$KeyValueSet;", "E", "Lkotlin/collections/AbstractMutableSet;", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;Lkotlin/jvm/functions/Function2;)V", "size", "", "getSize", "()I", BeanUtil.PREFIX_ADDER, "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMap$KeyValueSet.class */
    private final class KeyValueSet<E> extends AbstractMutableSet<E> {

        @NotNull
        private final Function2<K, V, E> factory;

        /* JADX WARN: Multi-variable type inference failed */
        public KeyValueSet(@NotNull Function2<? super K, ? super V, ? extends E> function2) {
            this.factory = function2;
        }

        @Override // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ConcurrentWeakMap.this.size();
        }

        @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(E e) {
            ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        @NotNull
        public Iterator<E> iterator() {
            return ((Core) ConcurrentWeakMap.this.core).keyValueIterator(this.factory);
        }
    }
}
