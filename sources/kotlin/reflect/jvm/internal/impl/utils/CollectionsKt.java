package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: collections.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/CollectionsKt.class */
public final class CollectionsKt {
    @NotNull
    public static final <K> Map<K, Integer> mapToIndex(@NotNull Iterable<? extends K> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        LinkedHashMap map = new LinkedHashMap();
        Iterator<? extends K> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            int index = i;
            i++;
            map.put(it.next(), Integer.valueOf(index));
        }
        return map;
    }

    public static final <T> void addIfNotNull(@NotNull Collection<T> collection, @Nullable T t) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        if (t != null) {
            collection.add(t);
        }
    }

    @NotNull
    public static final <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(capacity(expectedSize));
    }

    @NotNull
    public static final <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(capacity(expectedSize));
    }

    @NotNull
    public static final <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return new LinkedHashSet<>(capacity(expectedSize));
    }

    private static final int capacity(int expectedSize) {
        if (expectedSize < 3) {
            return 3;
        }
        return expectedSize + (expectedSize / 3) + 1;
    }

    @NotNull
    public static final <T> List<T> compact(@NotNull ArrayList<T> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        switch (arrayList.size()) {
            case 0:
                return kotlin.collections.CollectionsKt.emptyList();
            case 1:
                return kotlin.collections.CollectionsKt.listOf(kotlin.collections.CollectionsKt.first((List) arrayList));
            default:
                arrayList.trimToSize();
                return arrayList;
        }
    }
}
