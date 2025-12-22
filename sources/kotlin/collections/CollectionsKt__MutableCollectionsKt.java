package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MutableCollections.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��R\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001f\n��\n\u0002\u0010\u0011\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001d\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n��\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\t\u001a-\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a(\u0010\u0010\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0010\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a(\u0010\u0015\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0015\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a-\u0010\u0016\u001a\u00020\u0001\"\t\b��\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0018\u001a&\u0010\u0016\u001a\u0002H\u0002\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0087\b¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001c\u001a\u00020\u0001\"\t\b��\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u001d\u0010\u001e\u001a\u0002H\u0002\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001f\u0010 \u001a\u0004\u0018\u0001H\u0002\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001d\u0010!\u001a\u0002H\u0002\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001f\u0010\"\u001a\u0004\u0018\u0001H\u0002\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a-\u0010#\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010#\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010#\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b��\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010#\u001a\u00020\u0001\"\t\b��\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010#\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010#\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u0015\u0010$\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002¢\u0006\u0002\b%¨\u0006&"}, d2 = {"addAll", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "", "minusAssign", "", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "kotlin-stdlib"}, xs = "kotlin/collections/CollectionsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/CollectionsKt__MutableCollectionsKt.class */
public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    @InlineOnly
    private static final <T> boolean remove(Collection<? extends T> collection, T t) {
        if (collection == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).remove(t);
    }

    @InlineOnly
    private static final <T> boolean removeAll(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).removeAll(collection2);
    }

    @InlineOnly
    private static final <T> boolean retainAll(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).retainAll(collection2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> plusAssign, T t) {
        Intrinsics.checkNotNullParameter(plusAssign, "$this$plusAssign");
        plusAssign.add(t);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> plusAssign, Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, iterable);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> plusAssign, T[] tArr) {
        Intrinsics.checkNotNullParameter(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, tArr);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> plusAssign, Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, sequence);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> minusAssign, T t) {
        Intrinsics.checkNotNullParameter(minusAssign, "$this$minusAssign");
        minusAssign.remove(t);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> minusAssign, Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, iterable);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> minusAssign, T[] tArr) {
        Intrinsics.checkNotNullParameter(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, tArr);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> minusAssign, Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, sequence);
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(addAll, "$this$addAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return addAll.addAll((Collection) elements);
        }
        boolean result = false;
        for (Object item : elements) {
            if (addAll.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(addAll, "$this$addAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean result = false;
        for (Object item : elements) {
            if (addAll.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(addAll, "$this$addAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll.addAll(ArraysKt.asList(elements));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(removeAll, "$this$removeAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return TypeIntrinsics.asMutableCollection(removeAll).removeAll(CollectionsKt.convertToSetForSetOperationWith(elements, removeAll));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(removeAll, "$this$removeAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        HashSet set = SequencesKt.toHashSet(elements);
        return (!set.isEmpty()) && removeAll.removeAll(set);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(removeAll, "$this$removeAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return (!(elements.length == 0)) && removeAll.removeAll(ArraysKt.toHashSet(elements));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(retainAll, "$this$retainAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return TypeIntrinsics.asMutableCollection(retainAll).retainAll(CollectionsKt.convertToSetForSetOperationWith(elements, retainAll));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(retainAll, "$this$retainAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!(elements.length == 0)) {
            return retainAll.retainAll(ArraysKt.toHashSet(elements));
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(retainAll);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(retainAll, "$this$retainAll");
        Intrinsics.checkNotNullParameter(elements, "elements");
        HashSet set = SequencesKt.toHashSet(elements);
        if (!set.isEmpty()) {
            return retainAll.retainAll(set);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(retainAll);
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(Collection<?> collection) {
        boolean result = !collection.isEmpty();
        collection.clear();
        return result;
    }

    public static final <T> boolean removeAll(@NotNull Iterable<? extends T> removeAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(removeAll, "$this$removeAll");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) removeAll, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull Iterable<? extends T> retainAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(retainAll, "$this$retainAll");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) retainAll, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(Iterable<? extends T> iterable, Function1<? super T, Boolean> function1, boolean z) {
        boolean z2 = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (function1.invoke((Object) it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    @Deprecated(message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(imports = {}, expression = "removeAt(index)"), level = DeprecationLevel.ERROR)
    @InlineOnly
    private static final <T> T remove(List<T> list, int index) {
        return list.remove(index);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final <T> T removeFirst(@NotNull List<T> removeFirst) {
        Intrinsics.checkNotNullParameter(removeFirst, "$this$removeFirst");
        if (removeFirst.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return removeFirst.remove(0);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <T> T removeFirstOrNull(@NotNull List<T> removeFirstOrNull) {
        Intrinsics.checkNotNullParameter(removeFirstOrNull, "$this$removeFirstOrNull");
        if (removeFirstOrNull.isEmpty()) {
            return null;
        }
        return removeFirstOrNull.remove(0);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final <T> T removeLast(@NotNull List<T> removeLast) {
        Intrinsics.checkNotNullParameter(removeLast, "$this$removeLast");
        if (removeLast.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return removeLast.remove(CollectionsKt.getLastIndex(removeLast));
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <T> T removeLastOrNull(@NotNull List<T> removeLastOrNull) {
        Intrinsics.checkNotNullParameter(removeLastOrNull, "$this$removeLastOrNull");
        if (removeLastOrNull.isEmpty()) {
            return null;
        }
        return removeLastOrNull.remove(CollectionsKt.getLastIndex(removeLastOrNull));
    }

    public static final <T> boolean removeAll(@NotNull List<T> removeAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(removeAll, "$this$removeAll");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) removeAll, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull List<T> retainAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(retainAll, "$this$retainAll");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) retainAll, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(List<T> list, Function1<? super T, Boolean> function1, boolean predicateResultToRemove) {
        if (!(list instanceof RandomAccess)) {
            if (list == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
            }
            return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(list), function1, predicateResultToRemove);
        }
        int writeIndex = 0;
        int readIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (0 <= lastIndex) {
            while (true) {
                T t = list.get(readIndex);
                if (function1.invoke(t).booleanValue() != predicateResultToRemove) {
                    if (writeIndex != readIndex) {
                        list.set(writeIndex, t);
                    }
                    writeIndex++;
                }
                if (readIndex == lastIndex) {
                    break;
                }
                readIndex++;
            }
        }
        if (writeIndex < list.size()) {
            int removeIndex = CollectionsKt.getLastIndex(list);
            int i = writeIndex;
            if (removeIndex < i) {
                return true;
            }
            while (true) {
                list.remove(removeIndex);
                if (removeIndex == i) {
                    return true;
                }
                removeIndex--;
            }
        } else {
            return false;
        }
    }
}
